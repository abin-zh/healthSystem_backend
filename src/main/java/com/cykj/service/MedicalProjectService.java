package com.cykj.service;

import com.cykj.model.dto.ResponseDTO;
import com.cykj.model.pojo.MedicalProject;
import com.cykj.model.vo.PageVO;

import java.util.List;

/**
 * 体检项目业务
 * @author abin
 * @date 2024/8/8 10:47
*/
public interface MedicalProjectService{

    /**
     * 获取体检项目分页列表(全部、模糊)
     * @param pageVO 体检项目信息和分页信息
     * @return 提示信息
     */
    ResponseDTO getMedicalProjects(PageVO<MedicalProject> pageVO);

    /**
     * 获取体检项目的体检细项
     * @param projectId 体检项目id
     * @return 提示信息
     */
    ResponseDTO getProjectItem(int projectId);

    /**
     * 获取所有的部门，以及体检细项信息，若有projectId则需查对应的体检细项信息
     * @param projectId 体检项目id
     * @return 提示信息
     */
    ResponseDTO getAllDeptsAndItems(Integer projectId);

    /**
     * 添加体检项目，以及其选择的体检细项
     * @param medicalProject 体检项目信息
     * @return 提示信息
     */
    ResponseDTO addOneProjectAndProjectItem(MedicalProject medicalProject);

    /**
     * 编辑体检项目，以及其选择的体检细项
     * @param medicalProject 体检项目信息
     * @return 提示信息
     */
    ResponseDTO editProjectAndProjectItem(MedicalProject medicalProject);

    /**
     * 删除体检项目，以及关联的体检细项
     * @param ids 删除id列表
     * @return 提示信息
     */
    ResponseDTO deleteProjectAndProjectItem(List<Integer> ids);

    /**
     * 获取所有的项目(不分页且未被删除)
     * @return 提示信息
     */
    ResponseDTO getAllProjects();
}
