package com.cykj.mapper;

import org.apache.ibatis.annotations.Param;

/**
 * @author abin
 * @date 2024/8/8 10:47
 */
public interface MedicalPackageProjectMapper {
    int addOneMedicalPackageProject(@Param("packageId") int packageId, @Param("projectId") int projectId);

    int deleteByPpPackageIdAndPpProjectId(@Param("ppPackageId")Integer ppPackageId,@Param("ppProjectId")Integer ppProjectId);

}