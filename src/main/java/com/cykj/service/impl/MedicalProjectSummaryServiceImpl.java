package com.cykj.service.impl;

import org.springframework.stereotype.Service;

import org.springframework.beans.factory.annotation.Autowired;

import com.cykj.mapper.MedicalProjectSummaryMapper;
import com.cykj.service.MedicalProjectSummaryService;
/**
 * @author abin
 * @date 2024/8/8 10:47
*/
@Service
public class MedicalProjectSummaryServiceImpl implements MedicalProjectSummaryService{

    @Autowired
    private MedicalProjectSummaryMapper medicalProjectSummaryMapper;

}
