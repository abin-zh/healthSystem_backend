package com.cykj.mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

import com.cykj.model.pojo.MedicalItemCheckResults;

/**
 * 体检细项结论操作
 * @author abin
 * @date 2024/8/19 14:56
 */
public interface MedicalItemCheckResultsMapper {

    int addOneResult(MedicalItemCheckResults results);

    List<MedicalItemCheckResults> findAllByIcrProjectSummaryId(@Param("icrProjectSummaryId")Integer icrProjectSummaryId);

    int updateByIcrId(@Param("updated")MedicalItemCheckResults updated,@Param("icrId")Integer icrId);


}