package com.cykj.service;

import com.cykj.model.dto.ResponseDTO;
import com.cykj.model.pojo.Role;
import com.cykj.model.vo.PageVO;

import java.util.Map;

/**
 * @author abin
 * @date 2024/8/8 10:47
*/
public interface RoleService{

    /**
     * 获取角色集合
     * @return
     */
    Map<String, Role> getRoleMap();

    /**
     * 查询角色列表(分页、模糊)
     * @param vo 分页及角色信息
     * @return
     */
    ResponseDTO getRoles(PageVO<Role> vo);

    /**
     * 获取所有菜单
     * @return
     */
    ResponseDTO getAllMenus();

    /**
     * 获取角色的所有菜单
     * @return
     */
    ResponseDTO getRoleMenus(int roleId);

    /**
     * 添加一个角色
     * @param role 角色信息
     * @return
     */
    ResponseDTO addOneRole(Role role);

    /**
     * 编辑角色
     * @param role 角色信息
     * @return
     */
    ResponseDTO editRole(Role role);
}
