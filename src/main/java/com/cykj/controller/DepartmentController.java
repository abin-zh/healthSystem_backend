package com.cykj.controller;
import com.cykj.model.dto.ResponseDTO;
import com.cykj.model.pojo.Department;
import com.cykj.model.vo.PageVO;
import com.cykj.service.DepartmentService;
import com.cykj.service.impl.DepartmentServiceImpl;
import org.springframework.web.bind.annotation.*;

import org.springframework.beans.factory.annotation.Autowired;

/**
* (department)表控制层
*
* @author xxxxx
*/
@RestController
@RequestMapping("/dept")
public class DepartmentController {

    private DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @RequestMapping("/search")
    public ResponseDTO search(PageVO<Department> pageVO){
        return departmentService.getDepts(pageVO);
    }
}
