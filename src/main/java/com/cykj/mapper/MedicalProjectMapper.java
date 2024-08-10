package com.cykj.mapper;

import java.util.List;
import com.cykj.model.pojo.MedicalProject;

/**
 * @author abin
 * @date 2024/8/8 10:47
 */
public interface MedicalProjectMapper {
    List<MedicalProject> findByAll(MedicalProject medicalProject);

}