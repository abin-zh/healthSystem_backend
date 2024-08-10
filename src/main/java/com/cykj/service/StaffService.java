package com.cykj.service;

import com.cykj.model.dto.ResponseDTO;
import com.cykj.model.pojo.Staff;
import com.cykj.model.vo.PageVO;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 管理员(工作人员)业务层
 * @author abin
 * @date 2024/8/8 10:47
*/
public interface StaffService{

    /**
     * 管理员登录
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
     * 获取管理员分页列表(全部、模糊)
     * @param pageVO 分页信息
     * @return
     */
    ResponseDTO getStaffs(PageVO<Staff> pageVO);

    /**
     * 根据获取管理(工作人员)的信息，包括对应角色的菜单
     * @param staffId 工作人员id
     * @param roleId 角色id
     * @return
     */
    ResponseDTO getStaffInfo(int staffId,int roleId);
}