package com.cykj.mapper;
import com.cykj.model.pojo.MedicalCheckupSummary;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 体检总结操作
 * @author abin
 * @date 2024/8/19 14:55
 */
public interface MedicalCheckupSummaryMapper {
    int addOneSummary(int csOrderId);

    int updateCsStatusByCsOrderId(@Param("updatedCsStatus")Integer updatedCsStatus,@Param("csOrderId")Integer csOrderId);

    List<MedicalCheckupSummary> findAllByCsStatus(int csStatus);

    int updateByCsId(@Param("updated")MedicalCheckupSummary updated,@Param("csId")Integer csId);

    MedicalCheckupSummary findOneByOrderIdAndUserId(@Param("orderUserId") Integer orderUserId,@Param("csOrderId")Integer csOrderId);

}