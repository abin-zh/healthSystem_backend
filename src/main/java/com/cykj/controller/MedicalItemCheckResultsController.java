package com.cykj.controller;
import com.cykj.model.pojo.MedicalItemCheckResults;
import com.cykj.service.impl.MedicalItemCheckResultsServiceImpl;
import org.springframework.web.bind.annotation.*;

import org.springframework.beans.factory.annotation.Autowired;

/**
* (medical_item_check_results)表控制层
*
* @author xxxxx
*/
@RestController
@RequestMapping("/medical_item_check_results")
public class MedicalItemCheckResultsController {
/**
* 服务对象
*/
    @Autowired
    private MedicalItemCheckResultsServiceImpl medicalItemCheckResultsServiceImpl;


}
