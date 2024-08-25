package com.cykj.mapper;
import java.math.BigDecimal;
import org.apache.ibatis.annotations.Param;
import java.util.List;
import com.cykj.model.pojo.MedicalPackage;

/**
 * 体检套餐操作
 * @author abin
 * @date 2024/8/8 10:47
 */
public interface MedicalPackageMapper {
    List<MedicalPackage> findByAllWithPage(MedicalPackage medicalPackage);
    List<MedicalPackage> findAllByPackageName(@Param("packageName")String packageName);

    int addOneMedicalPackage(MedicalPackage medicalPackage);

    int updateByPackageId(@Param("updated")MedicalPackage updated,@Param("packageId")Integer packageId);

    int deleteMedicalPackage(int packageId);

    List<MedicalPackage> findAllWithProjectByIsDelete(int isDelete);

    List<MedicalPackage> findAllByPackageIds(List<Integer> ids);
}