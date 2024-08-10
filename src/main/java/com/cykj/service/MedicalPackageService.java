package com.cykj.service;

import com.cykj.model.dto.ResponseDTO;
import com.cykj.model.pojo.MedicalPackage;
import com.cykj.model.vo.PageVO;

/**
 * @author abin
 * @date 2024/8/8 10:47
*/
public interface MedicalPackageService{

    /**
     * 获取体检套餐分页列表(全部、模糊)
     * @param pageVO 分页数据
     * @return
     */
    ResponseDTO getMedicalPackage(PageVO<MedicalPackage> pageVO);
}
