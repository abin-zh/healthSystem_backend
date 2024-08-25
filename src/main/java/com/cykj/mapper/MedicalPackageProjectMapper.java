package com.cykj.mapper;

import org.apache.ibatis.annotations.Param;

/**
 * 体检套餐关联体检项目操作
 * @author abin
 * @date 2024/8/8 10:47
 */
public interface MedicalPackageProjectMapper {
    int addOneMedicalPackageProject(@Param("packageId") int packageId, @Param("projectId") int projectId);

    int deleteByPpPackageIdAndPpProjectId(@Param("ppPackageId")Integer ppPackageId,@Param("ppProjectId")Integer ppProjectId);

}