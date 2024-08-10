package com.cykj.service.impl;

import com.cykj.model.dto.ResponseDTO;
import com.cykj.model.pojo.MedicalItem;
import com.cykj.model.pojo.Staff;
import com.cykj.model.vo.PageVO;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import org.springframework.beans.factory.annotation.Autowired;

import com.cykj.mapper.MedicalItemMapper;
import com.cykj.service.MedicalItemService;

import java.util.List;

/**
 * @author abin
 * @date 2024/8/8 10:47
*/
@Service
public class MedicalItemServiceImpl implements MedicalItemService{

    private final MedicalItemMapper medicalItemMapper;

    @Autowired
    public MedicalItemServiceImpl(MedicalItemMapper medicalItemMapper) {
        this.medicalItemMapper = medicalItemMapper;
    }

    @Override
    public ResponseDTO getMedicalItems(PageVO<MedicalItem> pageVO) {
        Page<Object> page = PageHelper.startPage(pageVO.getPageNo(), pageVO.getPageSize());
        List<MedicalItem> allWithPage = medicalItemMapper.findByAllWithPage(pageVO.getData());
        PageInfo<Object> pageInfo = new PageInfo<>(allWithPage);
        return ResponseDTO.success((int) pageInfo.getTotal(), allWithPage);
    }
}
