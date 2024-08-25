package com.cykj.controller;
import com.cykj.annotation.Monitor;
import com.cykj.model.dto.ResponseDTO;
import com.cykj.model.pojo.Menu;
import com.cykj.model.vo.PageVO;
import com.cykj.service.MenuService;
import com.cykj.util.CommonUtils;
import org.springframework.web.bind.annotation.*;

import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import java.util.LinkedHashMap;

/**
* 菜单控制层
* @author abin
*/

@RestController
@RequestMapping("/menu")
public class MenuController {

    private MenuService menuService;

    @Autowired
    public MenuController(MenuService menuService) {
        this.menuService = menuService;
    }

    @RequestMapping("/navigate")
    public ResponseDTO getMenus(HttpServletRequest request){
        LinkedHashMap<String, Object> staff = CommonUtils.parseTokenInfo("staff", request);

        if(staff != null){
            Integer staffId = (Integer) staff.get("staffId");
            Integer roleId = (Integer) staff.get("staffRoleId");
            if(staffId != null || roleId != null){
                return menuService.getRoleMenu(roleId);
            }
        }

        return ResponseDTO.fail("错误的登录凭证");
    }

    @RequestMapping("search")
    public ResponseDTO search(@RequestBody PageVO<Menu> vo){
        return menuService.getAllMenus(vo);
    }

    @RequestMapping("edit")
    @Monitor("添加/编辑菜单")
    public ResponseDTO editMenu(@RequestBody Menu menu){
        if(menu.getId() == null){
            return menuService.addOneMenu(menu);
        } else {
            return menuService.editMenu(menu);
        }
    }
}
