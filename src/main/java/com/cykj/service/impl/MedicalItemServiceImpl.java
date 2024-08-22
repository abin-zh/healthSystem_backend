package com.cykj.service.impl;

import com.cykj.exception.DeleteException;
import com.cykj.model.dto.ResponseDTO;
import com.cykj.model.pojo.MedicalItem;
import com.cykj.model.vo.PageVO;
import com.cykj.util.CommonUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import org.springframework.beans.factory.annotation.Autowired;

import com.cykj.mapper.MedicalItemMapper;
import com.cykj.service.MedicalItemService;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author abin
 * @date 2024/8/8 10:47
*/
@Service
public class MedicalItemServiceImpl implements MedicalItemService{

    private MedicalItemMapper medicalItemMapper;

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

    @Override
    public ResponseDTO addOneMedicalItem(MedicalItem medicalItem) {
        MedicalItem item = medicalItemMapper.findOneByItemName(medicalItem.getItemName());
        if(item != null){
            return ResponseDTO.fail("细项已存在，请勿重复添加");
        }
        //上下限值比较
        ResponseDTO dto = CommonUtils.checkLimit(medicalItem.getItemLowerLimit(), medicalItem.getItemUpperLimit());
        if(dto != null){
            return dto;
        }


        int res = medicalItemMapper.addOneItem(medicalItem);
        return res >= 1 ? ResponseDTO.success("添加成功") : ResponseDTO.fail("添加失败");
    }

    @Override
    public ResponseDTO editMedicalItme(MedicalItem medicalItem) {
        //上下限值比较
        ResponseDTO dto = CommonUtils.checkLimit(medicalItem.getItemLowerLimit(), medicalItem.getItemUpperLimit());
        if(dto != null){
            return dto;
        }

        int res = medicalItemMapper.updateByItemId(medicalItem, medicalItem.getItemId());
        return res >= 1 ? ResponseDTO.success("修改成功") : ResponseDTO.fail("修改失败");
    }

    @Override
    @Transactional
    public ResponseDTO deleteMedicalItem(List<Integer> itemIds) throws DeleteException{
        int count = 0;
        for (Integer id : itemIds) {
            //查询项目下是否有体检套餐
            List<MedicalItem> items = medicalItemMapper.findAllByItemIdWithProjectItem(id);
            if (!items.isEmpty()) {
                throw new DeleteException("有体检项目正在使用该体检细项", id);
            }
            //尝试删除
            int res = -1;
            try {
                res = medicalItemMapper.deleteByItemId(id);
            } catch (Exception e) {
                throw new DeleteException("删除时出现异常", id);
            }
            if (res >= 1) {
                count++;
            }
        }
        return count == itemIds.size() ? ResponseDTO.success("删除成功") : ResponseDTO.fail("部分删除失败");
    }
}
