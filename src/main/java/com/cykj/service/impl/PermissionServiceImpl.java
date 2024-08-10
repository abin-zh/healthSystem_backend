package com.cykj.service.impl;

import org.springframework.stereotype.Service;

import org.springframework.beans.factory.annotation.Autowired;

import com.cykj.mapper.PermissionMapper;
import com.cykj.service.PermissionService;
/**
 * @author abin
 * @date 2024/8/8 10:47
*/
@Service
public class PermissionServiceImpl implements PermissionService{

    @Autowired
    private PermissionMapper permissionMapper;

}
