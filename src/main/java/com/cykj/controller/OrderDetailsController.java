package com.cykj.controller;
import com.cykj.model.pojo.OrderDetails;
import com.cykj.service.impl.OrderDetailsServiceImpl;
import org.springframework.web.bind.annotation.*;

import org.springframework.beans.factory.annotation.Autowired;

/**
* (order_details)表控制层
*
* @author xxxxx
*/
@RestController
@RequestMapping("/order_details")
public class OrderDetailsController {
/**
* 服务对象
*/
    @Autowired
    private OrderDetailsServiceImpl orderDetailsServiceImpl;

}
