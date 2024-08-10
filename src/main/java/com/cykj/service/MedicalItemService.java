package com.cykj.service;

import com.cykj.model.dto.ResponseDTO;
import com.cykj.model.pojo.MedicalItem;
import com.cykj.model.vo.PageVO;

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
}
