package com.cykj.service.impl;

import com.cykj.exception.AddException;
import com.cykj.exception.DeleteException;
import com.cykj.exception.UpdateException;
import com.cykj.mapper.*;
import com.cykj.model.dto.InfoDTO;
import com.cykj.model.dto.ResponseDTO;
import com.cykj.model.pojo.*;
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

import com.cykj.service.UsersService;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * 用户(体检人员)业务实现类
 *
 * @author abin
 * @date 2024/8/8 10:47
 */
@Service
public class UsersServiceImpl implements UsersService {

    private UsersMapper usersMapper;

    private MedicalProjectMapper medicalProjectMapper;

    private MedicalPackageMapper medicalPackageMapper;

    private BalanceflowMapper balanceflowMapper;

    private OrdersMapper ordersMapper;


    @Autowired
    public UsersServiceImpl(UsersMapper usersMapper, MedicalProjectMapper medicalProjectMapper, MedicalPackageMapper medicalPackageMapper, BalanceflowMapper balanceflowMapper, OrdersMapper ordersMapper) {
        this.usersMapper = usersMapper;
        this.medicalProjectMapper = medicalProjectMapper;
        this.medicalPackageMapper = medicalPackageMapper;
        this.balanceflowMapper = balanceflowMapper;
        this.ordersMapper = ordersMapper;
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
        HashMap<String, String> map = new HashMap<>();
        map.put("token", token);
        return ResponseDTO.success("登录成功", 0, map);
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
                user.getUserEmail(), user.getUserPassword(), user.getUserCPassword()) || user.getUserBirthday() == null) {
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

    /**
     * 编辑用户信息
     * 1. 查看手机号位数是否正确
     * 2. 查看手机号是否已经存在
     * 3. 执行更新
     */
    @Override
    public ResponseDTO editUser(User user) {
        if (user.getUserPhone() != null) {
            if (user.getUserPhone().length() != 11) {
                return ResponseDTO.fail("更新失败，请输入正确的手机号");
            }
            User hasUser = usersMapper.findOneByUserPhoneOrUserIdCard(user.getUserPhone(), null);
            if (hasUser != null) {
                return ResponseDTO.fail("更新失败，该手机号已被其他用户使用");
            }
        }

        int res = usersMapper.updateByUserId(user, user.getUserId());
        return res >= 1 ? ResponseDTO.success("更新成功") : ResponseDTO.fail("更新失败");
    }

    @Override
    public ResponseDTO deleteUser(List<Integer> ids) throws DeleteException {

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

    /**
     * 添加用户
     * 1. 检查数据(是否为空，手机号、身份证号位数是否正确)
     * 2. 查看手机号和身份证号是否已被使用
     * 3. 设置初始密码（MD5处理）
     * 4. 插入数据，记录插入结果
     */
    @Override
    @Transactional
    public ResponseDTO addUsers(List<User> users) throws RuntimeException {
        int count = 0;
        for (User user : users) {
            if (StrUtils.hasEmpty(
                    user.getUserName(), user.getUserPhone(), user.getUserIdCard(),
                    user.getUserEmail()) && user.getUserGender() == null && user.getUserBirthday() == null) {
                throw new AddException("有数据为空", user.getUserId());
            } else if (user.getUserPhone().length() != 11 && user.getUserIdCard().length() != 18) {
                throw new AddException(user.getUserName() + "的手机号或身份证号格式不正确", user.getUserId());
            }

            User one = usersMapper.findOneByUserPhoneOrUserIdCard(user.getUserPhone(), user.getUserIdCard());
            if (one != null) {
                throw new AddException(user.getUserName() + "的手机号或身份证号已被使用", user.getUserId());
            }

            user.setUserPassword(MD5Utils.encrypt("123456"));
            int res = usersMapper.addOneUser(user);
            if (res >= 1) {
                count++;
            }
        }
        return count == users.size() ? ResponseDTO.success("批量插入成功") : ResponseDTO.fail("批量插入失败");
    }

    @Override
    public ResponseDTO getUserInfo(Integer userId) {
        User user = usersMapper.findOneByUserId(userId);
        return user != null ? ResponseDTO.success("获取成功", new InfoDTO(user.getUserName(), user.getUserAvatar(), null)) : ResponseDTO.fail("获取失败");
    }

    @Override
    public ResponseDTO preTopUp(Balanceflow balanceflow, String body) {
        int res = balanceflowMapper.addOneFlow(balanceflow);
        return res >= 1 ? ResponseDTO.success("获取成功", body) : ResponseDTO.fail("获取失败");
    }


    /**
     * 支付宝支付成功回调(充值)
     * 1.更新用户余额
     * 2.更新交易流水
     */
    @Override
    @Transactional
    public ResponseDTO userPay(Pay pay) throws UpdateException {
        //基础判空
        if (StrUtils.isEmpty(pay.getOutTradeNo()) || pay.getTotalAmount() == null || pay.getBuyerPayAmount() == null
                || pay.getTotalAmount().compareTo(pay.getBuyerPayAmount()) != 0) {
            return null;
        }

        Balanceflow flow = balanceflowMapper.findOneByFlowOrderNumber(pay.getOutTradeNo());
        if (flow == null) {
            return null;
        }
        User user = usersMapper.findOneByUserId(flow.getFlowUserId());
        if (user == null) {
            return null;
        }
        user.setUserBalance(user.getUserBalance().add(pay.getBuyerPayAmount()));
        try {
            int res = usersMapper.updateUserBalance(user.getUserId(), user.getUserBalance());
            if (res <= 0) {
                throw new UpdateException("更新时错误", user.getUserId());
            }
            res = balanceflowMapper.updateByFlowOrderNumber(new Balanceflow(pay.getGmtPayment(), user.getUserPhone() + "充值了" + pay.getBuyerPayAmount() + "元", 1), pay.getOutTradeNo());
        } catch (Exception e) {
            if (e instanceof AddException) {
                throw e;
            }
            throw new UpdateException("更新时异常", user.getUserId());
        }

        return ResponseDTO.success("充值成功");
    }

    @Override
    public ResponseDTO getUserBalance(int userId) {
        User user = usersMapper.findOneByUserId(userId);
        if (user == null) {
            ResponseDTO.fail("用户不存在");
        }
        Map<String, Object> map = new HashMap<>();
        map.put("userBalance", user.getUserBalance());
        return ResponseDTO.success("获取成功", map);
    }

    /**
     * 用户支付体检单(订单)
     * 1.查询订单，获取支付金额
     * 2.查询用户信息
     * 3.扣款，更新用户余额
     * 4.扣款成功，更新订单状态
     * 5.添加交易流水
     */
    @Override
    @Transactional
    public ResponseDTO userPayCheckUp(int userId, String orderNumber) throws UpdateException {
        // 1.查询订单，获取支付金额
        Order order = ordersMapper.findOneByOrderNumber(orderNumber);
        if (order == null || order.getOrderTotalAmount() == null) {
            return ResponseDTO.fail("支付失败，未找到该订单信息");
        }

        //2.查询用户信息
        User user = usersMapper.findOneByUserId(userId);
        if (user == null) {
            return ResponseDTO.fail("支付失败，无法找到该用户");
        } else if (!Objects.equals(order.getOrderUserId(), user.getUserId())) {
            return ResponseDTO.fail("支付失败，您无法支付他人的订单");
        }

        //3.扣款，更新用户余额
        BigDecimal afeterBalance = user.getUserBalance().subtract(order.getOrderTotalAmount());
        if (afeterBalance.compareTo(BigDecimal.ZERO) < 0) {
            return ResponseDTO.fail("支付失败，账户余额不足，请充值后再试");
        }

        try {
            int res = usersMapper.updateUserBalance(userId, afeterBalance);
            if (res <= 0) {
                return ResponseDTO.fail("支付失败");
            }

            res = ordersMapper.updateOrderStatusByOrderNumber(1, orderNumber);
            if (res <= 0) {
                throw new UpdateException("支付时错误", order.getOrderId());
            }

            balanceflowMapper.addOneBalanceFlow(new Balanceflow(null, orderNumber, userId, null, 0, order.getOrderTotalAmount(),
                    user.getUserPhone() + "支付了" + orderNumber + "订单" + order.getOrderTotalAmount() + "元", 1));

        } catch (Exception e) {
            if (e instanceof UpdateException) {
                throw e;
            }
            throw new UpdateException("支付时异常", order.getOrderId());
        }

        return ResponseDTO.success("充值成功");
    }

}
