package com.cykj.service.impl;

import org.springframework.stereotype.Service;

import org.springframework.beans.factory.annotation.Autowired;

import com.cykj.mapper.LogMapper;
import com.cykj.service.LogService;
/**
 * @author abin
 * @date 2024/8/8 10:47
*/
@Service
public class LogServiceImpl implements LogService{

    @Autowired
    private LogMapper logMapper;

}
