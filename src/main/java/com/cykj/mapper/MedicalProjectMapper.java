package com.cykj.mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import com.cykj.model.pojo.MedicalProject;

/**
 * @author abin
 * @date 2024/8/8 10:47
 */
public interface MedicalProjectMapper {
    List<MedicalProject> findByAll(MedicalProject medicalProject);

    int addOneMedicalProject(MedicalProject medicalProject);

    List<MedicalProject> findAllByProjectName(@Param("projectName")String projectName);

    int updateByProjectId(@Param("updated")MedicalProject updated,@Param("projectId")Integer projectId);

    int deleteByProjectId(int projectId);

    List<MedicalProject> findAllByProjectId(@Param("projectId")Integer projectId);



    List<MedicalProject> findAllByProjectIdWithPackageProject(Integer projectId);

    List<MedicalProject> findAllByPackageIdWithPackageProject(int packageId);

    List<MedicalProject> findAllByProjectIsDeleted(@Param("projectIsDeleted")Integer projectIsDeleted);

    List<MedicalProject> findAllByProjectIds(List<Integer> ids);

}