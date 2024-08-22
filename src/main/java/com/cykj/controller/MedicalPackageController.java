package com.cykj.controller;
import com.cykj.exception.CurdException;
import com.cykj.model.dto.ResponseDTO;
import com.cykj.model.pojo.MedicalPackage;
import com.cykj.model.vo.DelVO;
import com.cykj.model.vo.PageVO;
import com.cykj.service.MedicalPackageService;
import com.cykj.util.CommonUtils;
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

    @RequestMapping("/project")
    public ResponseDTO getPackageProjectItems(@RequestBody MedicalPackage medicalPackage){
        if (medicalPackage.getPackageId() != null) {
            return medicalPackageService.getMedicalPackageProjects(medicalPackage.getPackageId());
        }
        return ResponseDTO.fail("获取失败，未知的编号");
    }

    @RequestMapping("/edit/need")
    public ResponseDTO getProjects(@RequestBody MedicalPackage medicalPackage){
        return medicalPackageService.getAllProjects(medicalPackage.getPackageId());
    }

    @PostMapping("edit")
    public ResponseDTO editPackage(@RequestBody MedicalPackage medicalPackage){
        if(medicalPackage.getPackageName() == null){
            return ResponseDTO.fail("未提供需求参数");
        }
        try{
            if(medicalPackage.getPackageId() == null){
                return medicalPackageService.addOnePackageAndPackageProject(medicalPackage);
            } else {
                return medicalPackageService.editPackageAndPackageProject(medicalPackage);
            }
        } catch (CurdException e){
            return ResponseDTO.fail(e.getMessage());
        }
    }

    @RequestMapping("del")
    public ResponseDTO deletePackages(@RequestBody DelVO delVO){
        ResponseDTO dto = CommonUtils.checkDelVO(delVO);
        if(dto != null){
            return dto;
        }

        try{
            return medicalPackageService.deletePackageAndPackageProject(delVO.getIds());
        } catch (CurdException e){
            return ResponseDTO.fail(e.getMessage());
        }
    }

    @RequestMapping("all")
    public ResponseDTO getAllPackages(){
        return medicalPackageService.getMedicalPackageAndProject();
    }
}
