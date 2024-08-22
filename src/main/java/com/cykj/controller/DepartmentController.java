package com.cykj.controller;

import com.cykj.exception.DeleteException;
import com.cykj.model.dto.ResponseDTO;
import com.cykj.model.pojo.Department;
import com.cykj.model.vo.DelVO;
import com.cykj.model.vo.PageVO;
import com.cykj.service.DepartmentService;
import com.cykj.util.CommonUtils;
import com.cykj.util.StrUtils;
import org.springframework.web.bind.annotation.*;

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
    public ResponseDTO search(@RequestBody PageVO<Department> pageVO) {
        return departmentService.getDepts(pageVO);
    }

    @PostMapping("edit")
    public ResponseDTO editDept(@RequestBody Department department) {
        if (StrUtils.isEmpty(department.getDeptName())) {
            return ResponseDTO.fail("未提供需求信息");
        }

        //是否有id，有id为修改
        if (department.getDeptId() == null) {
            return departmentService.addOneDept(department.getDeptName());
        } else {
            return departmentService.editDept(department);
        }
    }

    @PostMapping("del")
    public ResponseDTO deleteDept(@RequestBody DelVO delVO) {
        ResponseDTO dto = CommonUtils.checkDelVO(delVO);
        if(dto != null){
            return dto;
        }
        try {
            return departmentService.deleteDept(delVO.getIds());
        } catch (DeleteException e){
            return ResponseDTO.fail(e.getMessage());
        }
    }

    @PostMapping("all")
    public ResponseDTO getAllDepts(){
        return departmentService.getAllDepts();
    }
}

