package com.cykj.controller;
import com.cykj.service.impl.OrdersServiceImpl;
import org.springframework.web.bind.annotation.*;

import org.springframework.beans.factory.annotation.Autowired;

/**
* (orders)表控制层
*
* @author xxxxx
*/
@RestController
@RequestMapping("/orders")
public class OrdersController {
/**
* 服务对象
*/
    @Autowired
    private OrdersServiceImpl ordersServiceImpl;

}
