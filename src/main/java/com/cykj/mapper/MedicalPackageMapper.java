package com.cykj.mapper;
import java.math.BigDecimal;
import org.apache.ibatis.annotations.Param;
import java.util.List;
import com.cykj.model.pojo.MedicalPackage;

/**
 * @author abin
 * @date 2024/8/8 10:47
 */
public interface MedicalPackageMapper {
    List<MedicalPackage> findByAllWithPage(MedicalPackage medicalPackage);

}