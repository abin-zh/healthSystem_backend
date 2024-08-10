package com.cykj.controller;
import com.cykj.model.pojo.MedicalCheckupSummary;
import com.cykj.service.impl.MedicalCheckupSummaryServiceImpl;
import org.springframework.web.bind.annotation.*;

import org.springframework.beans.factory.annotation.Autowired;

/**
* (medical_checkup_summary)表控制层
*
* @author xxxxx
*/
@RestController
@RequestMapping("/medical_checkup_summary")
public class MedicalCheckupSummaryController {
/**
* 服务对象
*/
    @Autowired
    private MedicalCheckupSummaryServiceImpl medicalCheckupSummaryServiceImpl;


}
