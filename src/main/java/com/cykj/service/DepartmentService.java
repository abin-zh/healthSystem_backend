package com.cykj.service;

import com.cykj.model.dto.ResponseDTO;
import com.cykj.model.pojo.Department;
import com.cykj.model.vo.PageVO;

import java.util.List;

/**
 * @author abin
 * @date 2024/8/8 10:47
*/
public interface DepartmentService{

    /**
     * 获取科室分页列表(全部、模糊)
     * @param pageVO
     * @return
     */
    ResponseDTO getDepts(PageVO<Department> pageVO);

    /**
     * 获取所有科室信息(未删除的 isDelete为0)
     * @return
     */
    ResponseDTO getAllDepts();

    /**
     * 添加科室(科室名称不能重复)
     * @param deptName 科室名称
     * @return
     */
    ResponseDTO addOneDept(String deptName);

    /**
     * 编辑科室信息(科室名称不能重复)
     * @param department 科室信息
     * @return
     */
    ResponseDTO editDept(Department department);

    /**
     * 删除科室(科室下没有医生才可删除)
     * @param deptIds 科室id数组
     * @return
     */
    ResponseDTO deleteDept(List<Integer> deptIds);
}
