package com.cykj.service;

import com.cykj.model.dto.ResponseDTO;
import com.cykj.model.pojo.Department;
import com.cykj.model.vo.PageVO;

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
}
