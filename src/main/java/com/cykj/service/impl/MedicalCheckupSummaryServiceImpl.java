package com.cykj.service.impl;

import org.springframework.stereotype.Service;

import org.springframework.beans.factory.annotation.Autowired;

import com.cykj.mapper.MedicalCheckupSummaryMapper;
import com.cykj.service.MedicalCheckupSummaryService;
/**
 * @author abin
 * @date 2024/8/8 10:47
*/
@Service
public class MedicalCheckupSummaryServiceImpl implements MedicalCheckupSummaryService{

    @Autowired
    private MedicalCheckupSummaryMapper medicalCheckupSummaryMapper;

}
