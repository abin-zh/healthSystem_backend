package com.cykj.service.impl;

import org.springframework.stereotype.Service;

import org.springframework.beans.factory.annotation.Autowired;

import com.cykj.mapper.MedicalPackageProjectMapper;
import com.cykj.service.MedicalPackageProjectService;
/**
 * @author abin
 * @date 2024/8/8 10:47
*/
@Service
public class MedicalPackageProjectServiceImpl implements MedicalPackageProjectService{

    @Autowired
    private MedicalPackageProjectMapper medicalPackageProjectMapper;

}
