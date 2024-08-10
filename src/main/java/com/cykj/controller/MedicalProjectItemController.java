package com.cykj.controller;
import com.cykj.model.pojo.MedicalProjectItem;
import com.cykj.service.impl.MedicalProjectItemServiceImpl;
import org.springframework.web.bind.annotation.*;

import org.springframework.beans.factory.annotation.Autowired;

/**
* (medical_project_item)表控制层
*
* @author xxxxx
*/
@RestController
@RequestMapping("/medical_project_item")
public class MedicalProjectItemController {
/**
* 服务对象
*/
    @Autowired
    private MedicalProjectItemServiceImpl medicalProjectItemServiceImpl;


}
