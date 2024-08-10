package com.cykj.controller;
import com.cykj.model.dto.ResponseDTO;
import com.cykj.model.pojo.Menu;
import com.cykj.service.MenuService;
import com.cykj.service.impl.MenuServiceImpl;
import com.cykj.util.CommonUtil;
import org.springframework.web.bind.annotation.*;

import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import java.util.LinkedHashMap;

/**
* (menu)表控制层
*
* @author xxxxx
*/
@RestController
@RequestMapping("/menu")
public class MenuController {

    private final MenuService menuService;

    @Autowired
    public MenuController(MenuService menuService) {
        this.menuService = menuService;
    }

    @RequestMapping("/navigate")
    public ResponseDTO getMenus(HttpServletRequest request){
        LinkedHashMap<String, Object> staff = CommonUtil.parseTokenInfo("staff", request);

        if(staff != null){
            Integer staffId = (Integer) staff.get("staffId");
            Integer roleId = (Integer) staff.get("staffRoleId");
            if(staffId != null || roleId != null){
                return menuService.getRoleMenu(roleId);
            }
        }

        return ResponseDTO.fail("错误的登录凭证");
    }
}
