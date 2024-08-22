package com.cykj.service.impl;

import com.cykj.model.dto.ResponseDTO;
import com.cykj.model.pojo.Menu;
import com.cykj.model.vo.PageVO;
import com.cykj.util.StrUtils;
import com.cykj.util.TreeUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import org.springframework.beans.factory.annotation.Autowired;

import com.cykj.mapper.MenuMapper;
import com.cykj.service.MenuService;

import java.util.List;

/**
 * @author abin
 * @date 2024/8/8 10:47
*/
@Service
public class MenuServiceImpl implements MenuService{

    private final MenuMapper menuMapper;

    @Autowired
    public MenuServiceImpl(MenuMapper menuMapper) {
        this.menuMapper = menuMapper;
    }

    @Override
    public ResponseDTO getRoleMenu(int roleId) {
        List<Menu> menus = menuMapper.findAllByRoleId(roleId);
        List<Menu> menuTree = TreeUtils.buildUseMap(menus);
        return ResponseDTO.success("获取成功", menuTree);
    }

    @Override
    public ResponseDTO getAllMenus(PageVO<Menu> vo) {
        Page<Object> page = PageHelper.startPage(vo.getPageNo(), vo.getPageSize());
        List<Menu> menus = menuMapper.findAll(vo.getData());
        PageInfo<Object> pageInfo = new PageInfo(menus);
        return ResponseDTO.success((int) pageInfo.getTotal(), menus);
    }

    @Override
    public ResponseDTO addOneMenu(Menu menu) {
        if(StrUtils.hasEmpty(menu.getName(), menu.getTitle(), menu.getPath()) || menu.getParentId() == null){
            return ResponseDTO.fail("添加失败，未提供需求信息");
        }
        int res = menuMapper.addOneMenu(menu);
        return res >= 1 ? ResponseDTO.success("添加成功") : ResponseDTO.fail("添加失败");
    }

    @Override
    public ResponseDTO editMenu(Menu menu) {
        /**
         * 1.若删除，检查其下是否还有未删除的子菜单，若有则提示用户，阻止本次编辑
         * 2.执行更新
         */
        if(menu.getMenuIsDeleted() != null && menu.getMenuIsDeleted() == 1){
            List<Menu> menus = menuMapper.findAllByParentIdAndMenuIsDeleted(menu.getId(), 0);
            if(!menus.isEmpty()){
                return ResponseDTO.fail("删除失败，该菜单下还存在有子菜单");
            }
        }

        int res = menuMapper.updateById(menu, menu.getId());
        return res >= 1 ? ResponseDTO.success("更新成功") : ResponseDTO.fail("更新失败");
    }
}
