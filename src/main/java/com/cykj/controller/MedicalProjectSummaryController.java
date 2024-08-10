package com.cykj.controller;
import com.cykj.model.pojo.MedicalProjectSummary;
import com.cykj.service.impl.MedicalProjectSummaryServiceImpl;
import org.springframework.web.bind.annotation.*;

import org.springframework.beans.factory.annotation.Autowired;

/**
* (medical_project_summary)表控制层
*
* @author xxxxx
*/
@RestController
@RequestMapping("/medical_project_summary")
public class MedicalProjectSummaryController {
/**
* 服务对象
*/
    @Autowired
    private MedicalProjectSummaryServiceImpl medicalProjectSummaryServiceImpl;


}
