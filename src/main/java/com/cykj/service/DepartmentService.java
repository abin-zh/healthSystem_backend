package com.cykj.service;

import com.cykj.model.dto.ResponseDTO;
import com.cykj.model.pojo.Department;
import com.cykj.model.vo.PageVO;

import java.util.List;

/**
 * 科室业务
 * @author abin
 * @date 2024/8/8 10:47
*/
public interface DepartmentService{

    /**
     * 获取科室分页列表(全部、模糊)
     * @param pageVO 科室信息及分页信息
     * @return
     */
    ResponseDTO getDepts(PageVO<Department> pageVO);

    /**
     * 获取所有科室信息(未删除的 isDelete为0)
     * @return 提示信息
     */
    ResponseDTO getAllDepts();

    /**
     * 添加科室(科室名称不能重复)
     * @param deptName 科室名称
     * @return 提示信息
     */
    ResponseDTO addOneDept(String deptName);

    /**
     * 编辑科室信息(科室名称不能重复)
     * @param department 科室信息
     * @return 提示信息
     */
    ResponseDTO editDept(Department department);

    /**
     * 删除科室(科室下没有医生才可删除)
     * @param deptIds 科室id数组
     * @return 提示信息
     */
    ResponseDTO deleteDept(List<Integer> deptIds);
}
