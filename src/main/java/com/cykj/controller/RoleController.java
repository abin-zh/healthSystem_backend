package com.cykj.controller;
import com.cykj.model.pojo.Role;
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
/**
* 服务对象
*/
    @Autowired
    private RoleServiceImpl roleServiceImpl;


}
