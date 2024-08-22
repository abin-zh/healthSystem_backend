package com.cykj.service;

import com.cykj.model.dto.ResponseDTO;
import com.cykj.model.pojo.Menu;
import com.cykj.model.vo.PageVO;

/**
 * @author abin
 * @date 2024/8/8 10:47
*/
public interface MenuService{

    /**
     * 获取角色的菜单
     * @param roleId
     * @return
     */
    ResponseDTO getRoleMenu(int roleId);

    /**
     * 获取菜单列表(分页、模糊)
     * @return
     */
    ResponseDTO getAllMenus(PageVO<Menu> vo);

    /**
     * 添加菜单
     * @param menu
     * @return
     */
    ResponseDTO addOneMenu(Menu menu);

    /**
     * 编辑菜单
     * @param menu
     * @return
     */
    ResponseDTO editMenu(Menu menu);
}
