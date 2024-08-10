package com.cykj.service.impl;

import com.cykj.model.dto.ResponseDTO;
import com.cykj.model.pojo.Menu;
import com.cykj.util.TreeUtils;
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
}
