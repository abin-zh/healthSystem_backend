package com.cykj.service.impl;

import com.cykj.exception.AddException;
import com.cykj.exception.CurdException;
import com.cykj.exception.UpdateException;
import com.cykj.mapper.MenuMapper;
import com.cykj.mapper.PermissionMapper;
import com.cykj.model.dto.EditNeedDTO;
import com.cykj.model.dto.ResponseDTO;
import com.cykj.model.pojo.Menu;
import com.cykj.model.pojo.Role;
import com.cykj.model.vo.PageVO;
import com.cykj.util.StrUtils;
import com.cykj.util.TreeUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import org.springframework.beans.factory.annotation.Autowired;

import com.cykj.mapper.RoleMapper;
import com.cykj.service.RoleService;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 角色业务实现层
 * @author abin
 * @date 2024/8/8 10:47
*/
@Service
public class RoleServiceImpl implements RoleService{

    private RoleMapper roleMapper;

    private MenuMapper menuMapper;

    private PermissionMapper permissionMapper;

    @Autowired
    public RoleServiceImpl(RoleMapper roleMapper, MenuMapper menuMapper, PermissionMapper permissionMapper) {
        this.roleMapper = roleMapper;
        this.menuMapper = menuMapper;
        this.permissionMapper = permissionMapper;
    }

    @Override
    public Map<String, Role> getRoleMap() {
        List<Role> roles = roleMapper.findAll();
        HashMap<String, Role> roleMap = new HashMap<>();
        for (Role role : roles) {
            roleMap.put(role.getRoleName(), role);
        }
        return roleMap;
    }

    @Override
    public ResponseDTO getRoles(PageVO<Role> vo) {
        Page<Object> page = PageHelper.startPage(vo.getPageNo(), vo.getPageSize());
        List<Role> roles = roleMapper.findAllWithPage(vo.getData());
        PageInfo<Role> rolePageInfo = new PageInfo<>(roles);
        return ResponseDTO.success((int) rolePageInfo.getTotal(), roles);
    }

    @Override
    public ResponseDTO getAllMenus() {
        List<Menu> menus = menuMapper.findAllByMenuIsDeleted(0);
        List<Menu> menuTree = TreeUtils.buildUseMap(menus);
        return ResponseDTO.success("获取成功", new EditNeedDTO<>(null, null, menuTree, null, null));
    }

    @Override
    public ResponseDTO getRoleMenus(int roleId) {
        List<Menu> allMenus = menuMapper.findAllByMenuIsDeleted(0);
        List<Menu> menus = menuMapper.findAllByRoleId(roleId);
        HashMap<Integer, Menu> roleMenuMap = new HashMap<>();
        for (Menu menu : menus) {
            roleMenuMap.put(menu.getId(), menu);
        }
        //为已使用的菜单添加标记
        for (Menu allMenu : allMenus) {
            allMenu.setChildren(new ArrayList<>());
            if (roleMenuMap.containsKey(allMenu.getId())){
                allMenu.setAdd(true);
            }
        }
        List<Menu> allMenuTree = TreeUtils.build(allMenus, false);
        List<Menu> menuTree = TreeUtils.build(allMenus, true);


        return ResponseDTO.success("获取成功", new EditNeedDTO<>(null, null, allMenuTree, menuTree, menus));
    }

    @Override
    @Transactional
    public ResponseDTO addOneRole(Role role) throws AddException {
        if(StrUtils.isEmpty(role.getRoleName())){
            return ResponseDTO.fail("未提供需求信息");
        }

        try{
            //添加
            int res = roleMapper.addOneRole(role);
            if(res <= 0){
                throw new AddException("添加角色时未知错误", role.getRoleId());
            }

            //添加权限
            addPerm(role);
        } catch (Exception e){
            if(e instanceof AddException){
                throw e;
            }
            throw new AddException("添加时出现异常", role.getRoleId());
        }
        return ResponseDTO.success("添加成功");
    }

    @Override
    @Transactional
    public ResponseDTO editRole(Role role) throws CurdException {
        if(role.getRoleName() != null && role.getRoleName().isEmpty()){
            return ResponseDTO.fail("更新失败，未提供需求信息");
        }

        try{
            int res = roleMapper.updateByRoleId(role, role.getRoleId());
            if(res <= 0){
                throw new UpdateException("更新角色时未知错误", role.getRoleId());
            }

            //添加权限
            addPerm(role);
        } catch (Exception e){
            if(e instanceof CurdException){
                throw e;
            }
            throw new UpdateException("更新时出现异常", role.getRoleId());
        }


        return ResponseDTO.success("更新成功");
    }

    /**
     * 添加角色权限
     * @param role 角色信息
     * @throws AddException 添加异常
     */
    private void addPerm(Role role) throws AddException {
        //删除全部相关id
        try{
            int res = permissionMapper.deleteByPermRoleId(role.getRoleId());
            if(res <= 0){
                throw new AddException("删除角色菜单时错误", role.getRoleId());
            }

            for (Integer itemAddId : role.getItemAddIds()) {
                int result = permissionMapper.addOnePermission(itemAddId, role.getRoleId());
                if(result <= 0){
                    throw new AddException("添加角色菜单时未知错误", role.getRoleId());
                }
            }
        } catch (Exception e){
            if(e instanceof AddException){
                throw e;
            }
            throw new AddException("添加角色菜单时异常", role.getRoleId());
        }
    }
}
