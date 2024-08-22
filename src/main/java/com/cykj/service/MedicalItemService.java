package com.cykj.service;

import com.cykj.model.dto.ResponseDTO;
import com.cykj.model.pojo.MedicalItem;
import com.cykj.model.vo.PageVO;

import java.util.List;

/**
 * @author abin
 * @date 2024/8/8 10:47
*/
public interface MedicalItemService{

    /**
     * 获取体检细项分页列表(全部、模糊)
     * @param pageVO
     * @return
     */
    ResponseDTO getMedicalItems(PageVO<MedicalItem> pageVO);

    /**
     * 添加体检细项(体检细项名称不能重复，上下限值为整数，且下限值不能大于上限值)
     * @param medicalItem 体检细项信息
     * @return
     */
    ResponseDTO addOneMedicalItem(MedicalItem medicalItem);

    /**
     * 修改体检细项信息(体检细项名称不能重复，上下限值为整数，且下限值不能大于上限值)
     * @param medicalItem
     * @return
     */
    ResponseDTO editMedicalItme(MedicalItem medicalItem);

    /**
     * 删除体检细项(体检项目和套餐中不存在细项才可删除)
     * @param itemIds
     * @return
     */
    ResponseDTO deleteMedicalItem(List<Integer> itemIds);

}
