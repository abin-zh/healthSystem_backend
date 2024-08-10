package com.cykj.service.impl;

import org.springframework.stereotype.Service;

import org.springframework.beans.factory.annotation.Autowired;

import com.cykj.mapper.MedicalProjectItemMapper;
import com.cykj.service.MedicalProjectItemService;
/**
 * @author abin
 * @date 2024/8/8 10:47
*/
@Service
public class MedicalProjectItemServiceImpl implements MedicalProjectItemService{

    @Autowired
    private MedicalProjectItemMapper medicalProjectItemMapper;

}
