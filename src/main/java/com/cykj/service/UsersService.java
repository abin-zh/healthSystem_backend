package com.cykj.service;

import com.cykj.model.dto.ResponseDTO;
import com.cykj.model.pojo.*;
import com.cykj.model.vo.PageVO;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * 用户(体检人员)业务
 * @author abin
 * @date 2024/8/8 10:47
*/
public interface UsersService{

    /**
     * 用户登录
     * @param username 用户名
     * @param password 密码
     * @return 提示信息
     */
    ResponseDTO login(String username, String password);

    /**
     * 发送邮箱验证码
     * @param username 用户名
     * @param httpSession 当前会话
     * @param response http响应
     * @return 提示信息
     */
    ResponseDTO sendEmailCode(String username, HttpSession httpSession, HttpServletResponse response);

    /**
     * 用户注册
     * @param user 用户注册信息
     * @return 提示信息
     */
    ResponseDTO register(User user);

    /**
     * 获取用户分页列表(全部、模糊)
     * @param pageVO 用户信息及分页信息
     * @return 提示信息
     */
    ResponseDTO getUsers(PageVO<User> pageVO);

    /**
     * 编辑用户信息
     * @param user 需要更新的用户信息
     * @return 提示信息
     */
    ResponseDTO editUser(User user);

    /**
     * 删除用户
     * @param ids 用户id列表
     * @return 提示信息
     */
    ResponseDTO deleteUser(List<Integer> ids);

    /**
     * 添加用户
     * @param users 用户信息列表
     * @return 提示信息
     */
    ResponseDTO addUsers(List<User> users);

    /**
     * 获取用户信息
     * @param userId 用户id
     * @return 提示信息
     */
    ResponseDTO getUserInfo(Integer userId);

    /**
     * 用户交易(目前是充值)
     * @param pay 交易信息
     * @return 提示信息
     */
    ResponseDTO userPay(Pay pay);

    /**
     * 用户充值(创建流水，调取支付界面)
     * @param balanceflow 流水
     * @param body 支付界面
     * @return 提示信息
     */
    ResponseDTO preTopUp(Balanceflow balanceflow,String body);

    /**
     * 获取用户余额
     * @param userId 用户id
     * @return 提示信息
     */
    ResponseDTO getUserBalance(int userId);

    /**
     * 用户主动支付订单(体检单)
     * @param orderNumber 订单号
     * @return 提示信息
     */
    ResponseDTO userPayCheckUp(int userId,String orderNumber);
}
