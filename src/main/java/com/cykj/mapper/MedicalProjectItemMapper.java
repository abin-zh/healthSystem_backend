package com.cykj.mapper;

import com.cykj.model.pojo.MedicalProjectItem;
import org.apache.ibatis.annotations.Param;

/**
 * 体检项目关联细项操作
 * @author abin
 * @date 2024/8/12 16:52
*/
public interface MedicalProjectItemMapper {

    int addOneMedicalProjectItem(@Param("piProjectId") int piProjectId, @Param("piItemId") int piItemId);
    int deleteByPiProjectIdAndPiItemId(@Param("piProjectId")Integer piProjectId,@Param("piItemId")Integer piItemId);

}