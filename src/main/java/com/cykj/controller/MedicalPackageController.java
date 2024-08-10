package com.cykj.controller;
import com.cykj.model.dto.ResponseDTO;
import com.cykj.model.pojo.MedicalPackage;
import com.cykj.model.vo.PageVO;
import com.cykj.service.MedicalPackageService;
import com.cykj.service.impl.MedicalPackageServiceImpl;
import org.springframework.web.bind.annotation.*;

import org.springframework.beans.factory.annotation.Autowired;

/**
* 体检套餐控制层
*
* @author xxxxx
*/
@RestController
@RequestMapping("/medical_package")
public class MedicalPackageController {

    private MedicalPackageService medicalPackageService;

    @Autowired
    public MedicalPackageController(MedicalPackageService medicalPackageService) {
        this.medicalPackageService = medicalPackageService;
    }

    @RequestMapping("/search")
    public ResponseDTO search(@RequestBody PageVO<MedicalPackage> pageVO){
        return medicalPackageService.getMedicalPackage(pageVO);
    }
}
