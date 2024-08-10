package com.cykj.service;

import com.cykj.model.dto.ResponseDTO;
import com.cykj.model.pojo.MedicalProject;
import com.cykj.model.vo.PageVO;

/**
 * @author abin
 * @date 2024/8/8 10:47
*/
public interface MedicalProjectService{

    /**
     * 获取体检项目分页列表(全部、模糊)
     * @param pageVO
     * @return
     */
    ResponseDTO getMedicalProjects(PageVO<MedicalProject> pageVO);
}
