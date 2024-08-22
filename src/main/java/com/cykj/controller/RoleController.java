package com.cykj.controller;

import com.cykj.exception.CurdException;
import com.cykj.model.dto.ResponseDTO;
import com.cykj.model.pojo.Role;
import com.cykj.model.vo.PageVO;
import com.cykj.service.RoleService;
import com.cykj.service.impl.RoleServiceImpl;
import org.springframework.web.bind.annotation.*;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * (role)表控制层
 *
 * @author xxxxx
 */
@RestController
@RequestMapping("/role")
public class RoleController {

    private final RoleService roleService;

    @Autowired
    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @RequestMapping("/search")
    public ResponseDTO search(@RequestBody PageVO<Role> vo){
        return roleService.getRoles(vo);
    }

    @RequestMapping("edit/need")
    public ResponseDTO getMenus(@RequestBody Role role){
        if(role.getRoleId() == null){
            return roleService.getAllMenus();
        } else {
            return roleService.getRoleMenus(role.getRoleId());
        }
    }

    @RequestMapping("edit")
    public ResponseDTO edtiRoleAndMenu(@RequestBody Role role) {
        try{
            if (role.getRoleId() == null) {
                return roleService.addOneRole(role);
            } else {
                return roleService.editRole(role);
            }
        } catch (CurdException e){
            return ResponseDTO.fail(e.getMessage());
        }
    }
}
