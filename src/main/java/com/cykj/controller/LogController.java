package com.cykj.controller;
import com.cykj.model.pojo.Log;
import com.cykj.service.impl.LogServiceImpl;
import org.springframework.web.bind.annotation.*;

import org.springframework.beans.factory.annotation.Autowired;

/**
* (log)表控制层
*
* @author xxxxx
*/
@RestController
@RequestMapping("/log")
public class LogController {
/**
* 服务对象
*/
    @Autowired
    private LogServiceImpl logServiceImpl;

}
