package com.cykj.service.impl;

import org.springframework.stereotype.Service;

import org.springframework.beans.factory.annotation.Autowired;

import com.cykj.mapper.OrderDetailsMapper;
import com.cykj.service.OrderDetailsService;
/**
 * @author abin
 * @date 2024/8/8 10:47
*/
@Service
public class OrderDetailsServiceImpl implements OrderDetailsService{

    @Autowired
    private OrderDetailsMapper orderDetailsMapper;

}
