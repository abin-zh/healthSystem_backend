package com.cykj.service.impl;

import org.springframework.stereotype.Service;

import org.springframework.beans.factory.annotation.Autowired;

import com.cykj.mapper.RoleMapper;
import com.cykj.service.RoleService;
/**
 * @author abin
 * @date 2024/8/8 10:47
*/
@Service
public class RoleServiceImpl implements RoleService{

    @Autowired
    private RoleMapper roleMapper;

}
