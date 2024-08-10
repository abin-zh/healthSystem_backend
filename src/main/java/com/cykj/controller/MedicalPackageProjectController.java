package com.cykj.controller;
import com.cykj.model.pojo.MedicalPackageProject;
import com.cykj.service.impl.MedicalPackageProjectServiceImpl;
import org.springframework.web.bind.annotation.*;

import org.springframework.beans.factory.annotation.Autowired;

/**
* (medical_package_project)表控制层
*
* @author xxxxx
*/
@RestController
@RequestMapping("/medical_package_project")
public class MedicalPackageProjectController {
/**
* 服务对象
*/
    @Autowired
    private MedicalPackageProjectServiceImpl medicalPackageProjectServiceImpl;



}
