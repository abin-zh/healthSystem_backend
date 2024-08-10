package com.cykj.service.impl;

import com.cykj.model.dto.ResponseDTO;
import com.cykj.model.pojo.Department;
import com.cykj.model.pojo.User;
import com.cykj.model.vo.PageVO;
import com.cykj.util.CommonUtil;
import com.cykj.util.JWTUtils;
import com.cykj.util.MD5Utils;
import com.cykj.util.StrUtils;
import com.cykj.util.tree.CaptchaUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import org.springframework.beans.factory.annotation.Autowired;

import com.cykj.mapper.UsersMapper;
import com.cykj.service.UsersService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;

/**
 * 用户(体检人员)业务实现类
 * @author abin
 * @date 2024/8/8 10:47
*/
@Service
public class UsersServiceImpl implements UsersService{

    private final UsersMapper usersMapper;

    @Autowired
    public UsersServiceImpl(UsersMapper usersMapper) {
        this.usersMapper = usersMapper;
    }

    @Override
    public ResponseDTO login(String username, String password) {

        //查询用户是否存在
        User user = CommonUtil.userLogin(username, usersMapper);

        ResponseDTO dto = CommonUtil.passwordCheck(user, password, user.getUserPassword());
        if(dto != null){
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
        if(StrUtils.isEmpty(username)){
            return ResponseDTO.fail("发送失败，账号为空");
        } else if(username.length() != 11 && username.length() != 18){
            //未使用邮箱或手机号
            return ResponseDTO.fail("登录失败，账号错误");
        }

        //查询用户是否存在
        User user = CommonUtil.userLogin(username, usersMapper);

        //邮箱为空则发送失败
        if(user == null || StrUtils.isEmpty(user.getUserEmail())){
            return ResponseDTO.fail("发送失败，邮箱不存在");
        }

        return  CaptchaUtils.sendSmtp(user.getUserEmail(), httpSession, response) ? ResponseDTO.success("发送成功") : ResponseDTO.fail("发送失败");
    }

    @Override
    public ResponseDTO register(User user) {

        String username = user.getUserName();
        String password = user.getUserPassword();
        String idCard = user.getUserIdCard();
        String phone = user.getUserPhone();
        String email = user.getUserEmail();

        //判空
        if(StrUtils.hasEmpty(username, password, idCard, phone, email)){
            ResponseDTO.fail("注册失败，有信息为空");
        } else if (!user.getUserPassword().equals(user.getUserCPassword())) {
            ResponseDTO.fail("注册失败，两次输入的密码不一致");
        }

        User getUser = usersMapper.findOneByUserPhoneOrUserIdCard(user.getUserPhone(), user.getUserIdCard());
        if(getUser != null){
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
}
