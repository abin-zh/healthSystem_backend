package com.cykj.controller;

import com.cykj.model.pojo.Balanceflow;
import com.cykj.service.impl.BalanceflowServiceImpl;
import org.springframework.web.bind.annotation.*;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * (balanceflow)表控制层
 *
 * @author xxxxx
 */
@RestController
@RequestMapping("/balanceflow")
public class BalanceflowController {
    /**
     * 服务对象
     */
    @Autowired
    private BalanceflowServiceImpl balanceflowServiceImpl;



}
