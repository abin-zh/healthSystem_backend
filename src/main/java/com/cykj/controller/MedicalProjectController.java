package com.cykj.controller;

import com.cykj.exception.DeleteException;
import com.cykj.model.dto.ResponseDTO;
import com.cykj.model.pojo.MedicalProject;
import com.cykj.model.vo.DelVO;
import com.cykj.model.vo.PageVO;
import com.cykj.service.MedicalProjectService;
import com.cykj.util.CommonUtils;
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
    public ResponseDTO search(@RequestBody PageVO<MedicalProject> pageVO) {
        return medicalProjectService.getMedicalProjects(pageVO);
    }


    @RequestMapping("/item")
    public ResponseDTO getProjectItem(@RequestBody MedicalProject medicalProject) {
        if (medicalProject.getProjectId() != null) {
            return medicalProjectService.getProjectItem(medicalProject.getProjectId());
        }
        return ResponseDTO.fail("获取失败，未知的编号");
    }

    @RequestMapping("edit/need")
    public ResponseDTO getAllDeptsAndItems(@RequestBody MedicalProject medicalProject) {
        return medicalProjectService.getAllDeptsAndItems(medicalProject.getProjectId());
    }

    @PostMapping("edit")
    public ResponseDTO editProject(@RequestBody MedicalProject medicalProject) {
        if (medicalProject.getProjectName() == null) {
            return ResponseDTO.fail("未提供需求信息");
        }
        try {
            if (medicalProject.getProjectId() == null) {
                return medicalProjectService.addOneProjectAndProjectItem(medicalProject);
            } else {
                return medicalProjectService.editProjectAndProjectItem(medicalProject);
            }
        } catch (Exception e) {
            return ResponseDTO.fail(e.getMessage());
        }
    }

    @PostMapping("del")
    public ResponseDTO deleteMedicalProject(@RequestBody DelVO delVO) {
        ResponseDTO dto = CommonUtils.checkDelVO(delVO);
        if(dto != null){
            return dto;
        }

        try {
            return medicalProjectService.deleteProjectAndProjectItem(delVO.getIds());
        } catch (DeleteException e) {
            return ResponseDTO.fail(e.getMessage());
        }
    }

    @RequestMapping("all")
    public ResponseDTO getAllProjects(){
        return medicalProjectService.getAllProjects();
    }
}
