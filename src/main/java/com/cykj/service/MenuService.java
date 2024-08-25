package com.cykj.service;

import com.cykj.model.dto.ResponseDTO;
import com.cykj.model.pojo.Menu;
import com.cykj.model.vo.PageVO;

/**
 * 菜单业务
 * @author abin
 * @date 2024/8/8 10:47
*/
public interface MenuService{

    /**
     * 获取角色的菜单
     * @param roleId 角色id
     * @return 提示信息
     */
    ResponseDTO getRoleMenu(int roleId);

    /**
     * 获取菜单列表(分页、模糊)
     * @param vo 菜单信息及分页信息
     * @return 提示信息
     */
    ResponseDTO getAllMenus(PageVO<Menu> vo);

    /**
     * 添加菜单
     * @param menu 菜单信息
     * @return 提示信息
     */
    ResponseDTO addOneMenu(Menu menu);

    /**
     * 编辑菜单
     * @param menu 菜单信息
     * @return 提示信息
     */
    ResponseDTO editMenu(Menu menu);
}
