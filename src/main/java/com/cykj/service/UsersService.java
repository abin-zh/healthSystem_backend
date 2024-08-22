package com.cykj.service;

import com.cykj.model.dto.ResponseDTO;
import com.cykj.model.pojo.User;
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
     * @return
     */
    ResponseDTO login(String username, String password);

    /**
     * 发送邮箱验证码
     * @param username 用户名
     * @param httpSession 当前会话
     * @param response http响应
     * @return
     */
    ResponseDTO sendEmailCode(String username, HttpSession httpSession, HttpServletResponse response);

    /**
     * 用户注册
     * @param user 用户注册信息
     * @return
     */
    ResponseDTO register(User user);

    /**
     * 获取用户分页列表(全部、模糊)
     * @param pageVO
     * @return
     */
    ResponseDTO getUsers(PageVO<User> pageVO);

    /**
     * 编辑用户信息
     * @param user 需要更新的用户信息
     * @return
     */
    ResponseDTO editUser(User user);

    /**
     * 删除用户
     * @param ids 用户id列表
     * @return
     */
    ResponseDTO deleteUser(List<Integer> ids);

    /**
     * 添加用户
     * @param users 用户信息列表
     * @return
     */
    ResponseDTO addUsers(List<User> users);
}
