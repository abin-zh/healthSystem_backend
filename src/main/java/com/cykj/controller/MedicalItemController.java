package com.cykj.controller;
import com.cykj.model.dto.ResponseDTO;
import com.cykj.model.pojo.MedicalItem;
import com.cykj.model.vo.PageVO;
import com.cykj.service.MedicalItemService;
import com.cykj.service.impl.MedicalItemServiceImpl;
import org.springframework.web.bind.annotation.*;

import org.springframework.beans.factory.annotation.Autowired;

/**
* (medical_item)表控制层
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
}
