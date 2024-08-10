package com.cykj.controller;
import com.cykj.model.pojo.Permission;
import com.cykj.service.impl.PermissionServiceImpl;
import org.springframework.web.bind.annotation.*;

import org.springframework.beans.factory.annotation.Autowired;

/**
* (permission)表控制层
*
* @author xxxxx
*/
@RestController
@RequestMapping("/permission")
public class PermissionController {
/**
* 服务对象
*/
    @Autowired
    private PermissionServiceImpl permissionServiceImpl;


}
