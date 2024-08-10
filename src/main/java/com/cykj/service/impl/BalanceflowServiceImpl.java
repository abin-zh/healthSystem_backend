package com.cykj.service.impl;

import org.springframework.stereotype.Service;

import org.springframework.beans.factory.annotation.Autowired;

import com.cykj.mapper.BalanceflowMapper;
import com.cykj.service.BalanceflowService;
/**
 * @author abin
 * @date 2024/8/8 10:47
*/
@Service
public class BalanceflowServiceImpl implements BalanceflowService{

    @Autowired
    private BalanceflowMapper balanceflowMapper;

}
