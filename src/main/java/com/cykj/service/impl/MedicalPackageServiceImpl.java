package com.cykj.service.impl;

import com.cykj.exception.AddException;
import com.cykj.exception.DeleteException;
import com.cykj.exception.UpdateException;
import com.cykj.mapper.MedicalPackageProjectMapper;
import com.cykj.mapper.MedicalProjectMapper;
import com.cykj.model.dto.EditNeedDTO;
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
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/**
 * @author abin
 * @date 2024/8/8 10:47
 */
@Service
public class MedicalPackageServiceImpl implements MedicalPackageService {

    private MedicalPackageMapper medicalPackageMapper;

    private MedicalProjectMapper medicalProjectMapper;

    private MedicalPackageProjectMapper medicalPackageProjectMapper;

    @Autowired
    public MedicalPackageServiceImpl(MedicalPackageMapper medicalPackageMapper, MedicalProjectMapper medicalProjectMapper, MedicalPackageProjectMapper medicalPackageProjectMapper) {
        this.medicalPackageMapper = medicalPackageMapper;
        this.medicalProjectMapper = medicalProjectMapper;
        this.medicalPackageProjectMapper = medicalPackageProjectMapper;
    }

    @Override
    public ResponseDTO getMedicalPackage(PageVO<MedicalPackage> pageVO) {
        Page<Object> page = PageHelper.startPage(pageVO.getPageNo(), pageVO.getPageSize());
        List<MedicalPackage> allWithPage = medicalPackageMapper.findByAllWithPage(pageVO.getData());
        PageInfo<Object> pageInfo = new PageInfo<>(allWithPage);
        return ResponseDTO.success((int) pageInfo.getTotal(), allWithPage);
    }

    @Override
    public ResponseDTO getMedicalPackageProjects(Integer packageId) {
        List<MedicalProject> projects = medicalProjectMapper.findAllByPackageIdWithPackageProject(packageId);
        return ResponseDTO.success("获取成功", projects);
    }

    @Override
    public ResponseDTO getAllProjects(Integer packageId) {
        List<MedicalProject> fromItems = medicalProjectMapper.findAllByProjectIsDeleted(0);
        List<MedicalProject> toItems = new ArrayList<>();
        if (packageId != null) {
            //查询已存在元素
            toItems = medicalProjectMapper.findAllByPackageIdWithPackageProject(packageId);
            HashMap<Integer, Object> map = new HashMap<>();
            for (MedicalProject toItem : toItems) {
                map.put(toItem.getProjectId(), toItem);
            }
            //使用迭代器删除已存在元素
            Iterator<MedicalProject> iterator = fromItems.iterator();
            while (iterator.hasNext()) {
                MedicalProject next = iterator.next();
                if (map.containsKey(next.getProjectId())) {
                    iterator.remove();
                }
            }
        }
        return ResponseDTO.success("获取成功", new EditNeedDTO<MedicalProject>(null, null, fromItems, toItems, null));
    }

    @Override
    @Transactional
    public ResponseDTO addOnePackageAndPackageProject(MedicalPackage medicalPackage) throws AddException{
        //有同名体检套餐存在
        List<MedicalPackage> packages = medicalPackageMapper.findAllByPackageName(medicalPackage.getPackageName());
        if (!packages.isEmpty()) {
            ResponseDTO.fail("添加失败，已存在的同名的体检项目");
        }

        //添加体检套餐
        int res = medicalPackageMapper.addOneMedicalPackage(medicalPackage);
        if (res <= 0) {
            return ResponseDTO.fail("添加失败，未知异常错误");
        }
        //通过体检套餐id添加其体检项目
        List<Integer> itemIds = medicalPackage.getItemAddIds();
        int count = 0;
        for (Integer itemId : itemIds) {
            try {
                res = medicalPackageProjectMapper.addOneMedicalPackageProject(medicalPackage.getPackageId(), itemId);
            } catch (Exception e) {
                throw new AddException("体检项目未能添加成功", itemId);
            }
            //添加成功
            if (res >= 1) {
                count++;
            }
        }
        return count == itemIds.size() && res >= 1 ? ResponseDTO.success("添加成功") : ResponseDTO.fail("添加失败，未知添加错误");
    }

    @Override
    public ResponseDTO editPackageAndPackageProject(MedicalPackage medicalPackage) throws UpdateException{
        //有同名体检项目存在
        List<MedicalPackage> packages = medicalPackageMapper.findAllByPackageName(medicalPackage.getPackageName());
        if (!packages.isEmpty()) {
            ResponseDTO.fail("添加失败，已存在的同名的体检项目");
        }

        /**
         * 1. 更新体检套餐信息
         * 2. 删除关联体检项目信息(遍历删除项目列表)
         * 3. 添加关联体检项目信息(遍历添加项目列表)
         */
        int projectRes = 0, addCount = 0, rmCount = 0;
        try {
            projectRes = medicalPackageMapper.updateByPackageId(medicalPackage, medicalPackage.getPackageId());
            if (projectRes <= 0) {
                throw new UpdateException("未知的修改错误", medicalPackage.getPackageId());
            }


            //删除列表不为空，删除关联细项
            if (medicalPackage.getItemRmIds() != null) {
                for (Integer itemRmId : medicalPackage.getItemRmIds()) {
                    int res = medicalPackageProjectMapper.deleteByPpPackageIdAndPpProjectId(medicalPackage.getPackageId(), itemRmId);
                    if (res >= 1) {
                        rmCount++;
                    }
                }
            }

            if (medicalPackage.getItemAddIds() != null) {
                for (Integer itemAddId : medicalPackage.getItemAddIds()) {
                    int res = medicalPackageProjectMapper.addOneMedicalPackageProject(medicalPackage.getPackageId(), itemAddId);
                    if (res >= 1) {
                        addCount++;
                    }
                }
            }

        } catch (Exception e) {
            throw new UpdateException("在更新细项列表时出错", medicalPackage.getPackageId());
        }

        return ResponseDTO.success("更新成功");
    }

    @Override
    @Transactional
    public ResponseDTO deletePackageAndPackageProject(List<Integer> ids) throws DeleteException{

        int count = 0;
        for (Integer id : ids) {
            try{
                int res = medicalPackageMapper.deleteMedicalPackage(id);
                if(res >= 1){
                    count ++;
                }
            } catch (Exception e){
                throw new DeleteException("删除时出现异常", id);
            }
        }
        return count == ids.size() ? ResponseDTO.success("删除成功") : ResponseDTO.fail("部分删除失败");

    }

    @Override
    public ResponseDTO getMedicalPackageAndProject() {
        List<MedicalPackage> packages = medicalPackageMapper.findAllWithProjectByIsDelete(0);
        return ResponseDTO.success("获取成功", new EditNeedDTO<MedicalPackage>(null, null, packages, new ArrayList<>(), null));
    }
}
