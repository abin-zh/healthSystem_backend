package com.cykj.controller;
import com.cykj.annotation.Monitor;
import com.cykj.exception.DeleteException;
import com.cykj.model.dto.ResponseDTO;
import com.cykj.model.pojo.MedicalItem;
import com.cykj.model.vo.DelVO;
import com.cykj.model.vo.PageVO;
import com.cykj.service.MedicalItemService;
import com.cykj.util.CommonUtils;
import com.cykj.util.StrUtils;
import org.springframework.web.bind.annotation.*;

import org.springframework.beans.factory.annotation.Autowired;

/**
* 体检细项控制层
*
* @author xxxxx
*/
@RestController
@RequestMapping("/medical_item")
public class MedicalItemController {

    private MedicalItemService medicalItemService;

    @Autowired
    public MedicalItemController(MedicalItemService medicalItemService) {
        this.medicalItemService = medicalItemService;
    }

    @RequestMapping("/search")
    public ResponseDTO search(@RequestBody PageVO<MedicalItem> pageVO){
        return medicalItemService.getMedicalItems(pageVO);
    }

    @PostMapping("/edit")
    @Monitor("添加/编辑体检细项")
    public ResponseDTO editMeicalItem(@RequestBody MedicalItem medicalItem){
        if(StrUtils.hasEmpty(medicalItem.getItemName())){
            return ResponseDTO.fail("未提供需求信息");
        }

        if(medicalItem.getItemId() == null){
            return medicalItemService.addOneMedicalItem(medicalItem);
        } else {
            return medicalItemService.editMedicalItme(medicalItem);
        }
    }

    @PostMapping("del")
    @Monitor("删除体检细项")
    public ResponseDTO deleteMedicalItem(@RequestBody DelVO delVO){
        ResponseDTO dto = CommonUtils.checkDelVO(delVO);
        if(dto != null){
            return dto;
        }
        try {
            return medicalItemService.deleteMedicalItem(delVO.getIds());
        } catch (DeleteException e){
            return ResponseDTO.fail(e.getMessage());
        }
    }
}
