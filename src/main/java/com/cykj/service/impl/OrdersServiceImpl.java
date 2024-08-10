package com.cykj.service.impl;

import org.springframework.stereotype.Service;

import org.springframework.beans.factory.annotation.Autowired;

import com.cykj.mapper.OrdersMapper;
import com.cykj.service.OrdersService;
/**
 * @author abin
 * @date 2024/8/8 10:47
*/
@Service
public class OrdersServiceImpl implements OrdersService{

    @Autowired
    private OrdersMapper ordersMapper;

}
