package com.cykj.service.impl;

import com.cykj.exception.AddException;
import com.cykj.exception.CurdException;
import com.cykj.exception.DeleteException;
import com.cykj.model.dto.ResponseDTO;
import com.cykj.model.pojo.User;
import com.cykj.model.vo.PageVO;
import com.cykj.util.CommonUtils;
import com.cykj.util.JWTUtils;
import com.cykj.util.MD5Utils;
import com.cykj.util.StrUtils;
import com.cykj.util.CaptchaUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import org.springframework.beans.factory.annotation.Autowired;

import com.cykj.mapper.UsersMapper;
import com.cykj.service.UsersService;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;

/**
 * 用户(体检人员)业务实现类
 *
 * @author abin
 * @date 2024/8/8 10:47
 */
@Service
public class UsersServiceImpl implements UsersService {

    private final UsersMapper usersMapper;

    @Autowired
    public UsersServiceImpl(UsersMapper usersMapper) {
        this.usersMapper = usersMapper;
    }

    @Override
    public ResponseDTO login(String username, String password) {

        //查询用户是否存在
        User user = CommonUtils.userLogin(username, usersMapper);

        ResponseDTO dto = CommonUtils.passwordCheck(user, password, user.getUserPassword());
        if (dto != null) {
            return dto;
        }

        user.setUserPassword(null);

        //生成token
        HashMap<String, Object> data = new HashMap<>();
        data.put("user", user);
        String token = JWTUtils.generateToken(data);
        return ResponseDTO.success("登录成功", 0, token);
    }

    @Override
    public ResponseDTO sendEmailCode(String username, HttpSession httpSession, HttpServletResponse response) {
        //判空
        if (StrUtils.isEmpty(username)) {
            return ResponseDTO.fail("发送失败，账号为空");
        } else if (username.length() != 11 && username.length() != 18) {
            //未使用邮箱或手机号
            return ResponseDTO.fail("登录失败，账号错误");
        }

        //查询用户是否存在
        User user = CommonUtils.userLogin(username, usersMapper);

        //邮箱为空则发送失败
        if (user == null || StrUtils.isEmpty(user.getUserEmail())) {
            return ResponseDTO.fail("发送失败，用户或邮箱不存在");
        }

        return CaptchaUtils.sendSmtp(user.getUserEmail(), httpSession, response) ? ResponseDTO.success("发送成功") : ResponseDTO.fail("发送失败");
    }

    @Override
    public ResponseDTO register(User user) {

        //判空
        if (StrUtils.hasEmpty(
                user.getUserName(), user.getUserPhone(), user.getUserIdCard(),
                user.getUserEmail(), user.getUserPassword(), user.getUserCPassword()) && user.getUserBirthday() == null) {
            ResponseDTO.fail("注册失败，有信息为空");
        } else if (!user.getUserPassword().equals(user.getUserCPassword())) {
            ResponseDTO.fail("注册失败，两次输入的密码不一致");
        }

        User getUser = usersMapper.findOneByUserPhoneOrUserIdCard(user.getUserPhone(), user.getUserIdCard());
        if (getUser != null) {
            return ResponseDTO.fail("注册失败，该账号已被注册");
        }

        //MD5加密密码
        user.setUserPassword(MD5Utils.encrypt(user.getUserPassword()));

        //添加用户
        int res = usersMapper.addOneUser(user);
        return res >= 1 ? ResponseDTO.success("注册成功") : ResponseDTO.fail("注册失败");
    }

    @Override
    public ResponseDTO getUsers(PageVO<User> pageVO) {
        Page<Object> page = PageHelper.startPage(pageVO.getPageNo(), pageVO.getPageSize());
        List<User> allWithPage = usersMapper.findByAllWithPage(pageVO.getData());
        PageInfo<Object> pageInfo = new PageInfo<>(allWithPage);
        return ResponseDTO.success((int) pageInfo.getTotal(), allWithPage);
    }

    @Override
    public ResponseDTO editUser(User user) {
        /**
         * 1. 查看手机号位数是否正确
         * 2. 查看手机号是否已经存在
         * 3. 执行更新
         */
        if (user.getUserPhone() != null) {
            if (user.getUserPhone().length() != 11) {
                return ResponseDTO.fail("更新失败，请输入正确的手机号");
            }
            User hasUser = usersMapper.findOneByUserPhoneOrUserIdCard(user.getUserPhone(), null);
            if(hasUser != null){
                return ResponseDTO.fail("更新失败，该手机号已被其他用户使用");
            }
        }

        int res = usersMapper.updateByUserId(user, user.getUserId());
        return res >= 1 ? ResponseDTO.success("更新成功") : ResponseDTO.fail("更新失败");
    }

    @Override
    public ResponseDTO deleteUser(List<Integer> ids) throws DeleteException{

        int count = 0;
        for (Integer id : ids) {

            try {
                int res = usersMapper.deleteUser(id);
                if (res >= 1) {
                    count++;
                }
            } catch (Exception e) {
                throw new DeleteException("删除时异常", id);
            }

        }

        return count == ids.size() ? ResponseDTO.success("删除成功") : ResponseDTO.fail("删除失败");
    }

    @Override
    @Transactional
    public ResponseDTO addUsers(List<User> users) throws RuntimeException {
        /**
         * 1. 检查数据(是否为空，手机号、身份证号位数是否正确)
         * 2. 查看手机号和身份证号是否已被使用
         * 3. 设置初始密码（MD5处理）
         * 4. 插入数据，记录插入结果
         */
        int count = 0;
        for (User user : users) {
            if (StrUtils.hasEmpty(
                    user.getUserName(), user.getUserPhone(), user.getUserIdCard(),
                    user.getUserEmail()) && user.getUserGender() == null && user.getUserBirthday() == null){
                throw new AddException("有数据为空", user.getUserId());
            } else if(user.getUserPhone().length() != 11 && user.getUserIdCard().length() != 18){
                throw new AddException(user.getUserName() + "的手机号或身份证号格式不正确", user.getUserId());
            }

            User one = usersMapper.findOneByUserPhoneOrUserIdCard(user.getUserPhone(), user.getUserIdCard());
            if(one != null){
                throw new AddException(user.getUserName() + "的手机号或身份证号已被使用", user.getUserId());
            }

            user.setUserPassword(MD5Utils.encrypt("123456"));
            int res = usersMapper.addOneUser(user);
            if(res >= 1){
                count++;
            }
        }
        return count == users.size() ? ResponseDTO.success("批量插入成功") : ResponseDTO.fail("批量插入失败");
    }
}
