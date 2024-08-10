package com.cykj.controller;
import com.cykj.model.dto.ResponseDTO;
import com.cykj.model.pojo.MedicalProject;
import com.cykj.model.vo.PageVO;
import com.cykj.service.MedicalProjectService;
import com.cykj.service.impl.MedicalProjectServiceImpl;
import org.springframework.web.bind.annotation.*;

import org.springframework.beans.factory.annotation.Autowired;

/**
* 体检项目表控制层
*
* @author abin
*/
@RestController
@RequestMapping("/medical_project")
public class MedicalProjectController {

    private MedicalProjectService medicalProjectService;

    @Autowired
    public MedicalProjectController(MedicalProjectService medicalProjectService) {
        this.medicalProjectService = medicalProjectService;
    }

    @RequestMapping("/search")
    public ResponseDTO search(@RequestBody PageVO<MedicalProject> pageVO){
        return medicalProjectService.getMedicalProjects(pageVO);
    }

}
