package com.cykj.mapper;

import com.cykj.model.pojo.MedicalProjectSummary;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 体检项目小结操作
 * @author abin
 * @date 2024/8/19 14:56
 */
public interface MedicalProjectSummaryMapper {
    int addOneProjectSummary(MedicalProjectSummary summary);

    List<MedicalProjectSummary> findAllByOrderNumberAndDeptId(@Param("orderNumber") String orderNumber, @Param("deptId") int deptId);

    int updateByPsId(@Param("updated")MedicalProjectSummary updated,@Param("psId")Integer psId);

    List<MedicalProjectSummary> findAllOrderProjectSummaryByPsId(@Param("psOrderId")Integer psOrderId);

    MedicalProjectSummary findOneByPsId(@Param("psId")Integer psId);

    List<MedicalProjectSummary> findAllByPsOrderId(@Param("psOrderId")Integer psOrderId);

    List<MedicalProjectSummary> findAllByOrderIdAndUserId(@Param("orderUserId") Integer orderUserId,@Param("psOrderId")Integer psOrderId);

}