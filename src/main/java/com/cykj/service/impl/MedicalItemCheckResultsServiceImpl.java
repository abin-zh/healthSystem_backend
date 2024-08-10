package com.cykj.service.impl;

import org.springframework.stereotype.Service;

import org.springframework.beans.factory.annotation.Autowired;

import com.cykj.mapper.MedicalItemCheckResultsMapper;
import com.cykj.service.MedicalItemCheckResultsService;
/**
 * @author abin
 * @date 2024/8/8 10:47
*/
@Service
public class MedicalItemCheckResultsServiceImpl implements MedicalItemCheckResultsService{

    @Autowired
    private MedicalItemCheckResultsMapper medicalItemCheckResultsMapper;

}
