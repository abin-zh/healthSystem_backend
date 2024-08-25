package com.cykj.service;

import com.cykj.model.dto.ResponseDTO;
import com.cykj.model.pojo.MedicalPackage;
import com.cykj.model.pojo.MedicalProject;
import com.cykj.model.vo.PageVO;

import java.util.List;

/**
 * 体检套餐业务
 * @author abin
 * @date 2024/8/8 10:47
*/
public interface MedicalPackageService{

    /**
     * 获取体检套餐分页列表(全部、模糊)
     * @param pageVO 体检套餐信息及分页信息
     * @return 提示信息
     */
    ResponseDTO getMedicalPackage(PageVO<MedicalPackage> pageVO);

    /**
     * 获取体检套餐的体检项目列表
     * @param packageId 体检套餐id
     * @return 提示信息
     */
    ResponseDTO getMedicalPackageProjects(Integer packageId);

    /**
     * 获取所有体检项目(未删除的)，若有packageId则需查对应的体检项目信息
     * @param packageId 套餐id
     * @return 提示信息
     */
    ResponseDTO getAllProjects(Integer packageId);

    /**
     * 添加体检套餐(不能有同名的套餐)
     * @param medicalPackage 体检套餐信息
     * @return 提示信息
     */
    ResponseDTO addOnePackageAndPackageProject(MedicalPackage medicalPackage);

    /**
     * 修改体检套餐(修改时不能出现同名套餐)
     * @param medicalPackage 体检套餐信息
     * @return 提示信息
     */
    ResponseDTO editPackageAndPackageProject(MedicalPackage medicalPackage);

    /**
     * 删除体检套餐
     * @param ids 体检套餐的id列表
     * @return 提示信息
     */
    ResponseDTO deletePackageAndPackageProject(List<Integer> ids);

    /**
     * 获取所有的体检套餐及其体检项目(不分页)
     */
    ResponseDTO getMedicalPackageAndProject();
}
