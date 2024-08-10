package com.cykj.service.impl;

import com.cykj.model.dto.ResponseDTO;
import com.cykj.model.pojo.MedicalPackage;
import com.cykj.model.pojo.MedicalProject;
import com.cykj.model.vo.PageVO;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import org.springframework.beans.factory.annotation.Autowired;

import com.cykj.mapper.MedicalPackageMapper;
import com.cykj.service.MedicalPackageService;

import java.util.List;

/**
 * @author abin
 * @date 2024/8/8 10:47
*/
@Service
public class MedicalPackageServiceImpl implements MedicalPackageService{

    @Autowired
    private MedicalPackageMapper medicalPackageMapper;

    @Override
    public ResponseDTO getMedicalPackage(PageVO<MedicalPackage> pageVO) {
        Page<Object> page = PageHelper.startPage(pageVO.getPageNo(), pageVO.getPageSize());
        List<MedicalPackage> allWithPage =  medicalPackageMapper.findByAllWithPage(pageVO.getData());
        PageInfo<Object> pageInfo = new PageInfo<>(allWithPage);
        return ResponseDTO.success((int) pageInfo.getTotal(), allWithPage);
    }
}
