package com.cykj.service.impl;

import com.cykj.exception.AddException;
import com.cykj.exception.DeleteException;
import com.cykj.exception.UpdateException;
import com.cykj.mapper.*;
import com.cykj.model.dto.EditNeedDTO;
import com.cykj.model.dto.ResponseDTO;
import com.cykj.model.pojo.Department;
import com.cykj.model.pojo.MedicalItem;
import com.cykj.model.pojo.MedicalProject;
import com.cykj.model.vo.PageVO;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import com.cykj.service.MedicalProjectService;
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
public class MedicalProjectServiceImpl implements MedicalProjectService {

    private MedicalProjectMapper medicalProjectMapper;

    private MedicalItemMapper medicalItemMapper;

    private DepartmentMapper departmentMapper;

    private MedicalProjectItemMapper medicalProjectItemMapper;


    public MedicalProjectServiceImpl(MedicalProjectMapper medicalProjectMapper, MedicalItemMapper medicalItemMapper, DepartmentMapper departmentMapper, MedicalProjectItemMapper medicalProjectItemMapper) {
        this.medicalProjectMapper = medicalProjectMapper;
        this.medicalItemMapper = medicalItemMapper;
        this.departmentMapper = departmentMapper;
        this.medicalProjectItemMapper = medicalProjectItemMapper;
    }

    @Override
    public ResponseDTO getMedicalProjects(PageVO<MedicalProject> pageVO) {
        Page<Object> page = PageHelper.startPage(pageVO.getPageNo(), pageVO.getPageSize());
        List<MedicalProject> allWithPage = medicalProjectMapper.findByAll(pageVO.getData());
        PageInfo<Object> pageInfo = new PageInfo<>(allWithPage);
        return ResponseDTO.success((int) pageInfo.getTotal(), allWithPage);
    }

    @Override
    public ResponseDTO getProjectItem(int projectId) {
        List<MedicalItem> items = medicalItemMapper.findAllByProjectIdWithProjectItem(projectId);
        return ResponseDTO.success("获取成功", items);
    }

    @Override
    public ResponseDTO getAllDeptsAndItems(Integer projectId) {
        List<Department> depts = departmentMapper.findAllByDeptIsDeleted(0);
        List<MedicalItem> fromItems = medicalItemMapper.findAllByItemIsDeleted(0);
        List<MedicalItem> toItems = new ArrayList<>();
        if (projectId != null) {
            //查询已存在元素
            toItems = medicalItemMapper.findAllByProjectIdWithProjectItem(projectId);
            HashMap<Integer, Object> map = new HashMap<>();
            for (MedicalItem toItem : toItems) {
                map.put(toItem.getItemId(), toItem);
            }
            //使用迭代器删除已存在元素
            Iterator<MedicalItem> iterator = fromItems.iterator();
            while (iterator.hasNext()) {
                MedicalItem next = iterator.next();
                if (map.containsKey(next.getItemId())) {
                    iterator.remove();
                }
            }
        }
        return ResponseDTO.success("获取成功", new EditNeedDTO<MedicalItem>(depts, null,fromItems, toItems, null));
    }

    @Override
    @Transactional
    public ResponseDTO addOneProjectAndProjectItem(MedicalProject medicalProject) throws AddException{
        //有同名体检项目存在
        List<MedicalProject> projects = medicalProjectMapper.findAllByProjectName(medicalProject.getProjectName());
        if (!projects.isEmpty()) {
            ResponseDTO.fail("添加失败，已存在的同名的体检项目");
        }

        //添加体检项目
        int res = medicalProjectMapper.addOneMedicalProject(medicalProject);
        if (res <= 0) {
            return ResponseDTO.fail("添加失败，未知异常错误");
        }
        //通过体检项目id添加其体检细项
        List<Integer> itemIds = medicalProject.getItemAddIds();
        int count = 0;
        for (Integer itemId : itemIds) {
            try {
                res = medicalProjectItemMapper.addOneMedicalProjectItem(medicalProject.getProjectId(), itemId);
            } catch (Exception e) {
                throw new AddException("体检细项未能添加成功", itemId);
            }
            //添加成功
            if (res >= 1) {
                count++;
            }
        }
        return count == itemIds.size() && res >= 1 ? ResponseDTO.success("添加成功") : ResponseDTO.fail("添加失败，未知添加错误");
    }

    @Override
    @Transactional
    public ResponseDTO editProjectAndProjectItem(MedicalProject medicalProject) throws UpdateException{
        //有同名体检项目存在
        List<MedicalProject> projects = medicalProjectMapper.findAllByProjectName(medicalProject.getProjectName());
        if (!projects.isEmpty()) {
            ResponseDTO.fail("添加失败，已存在的同名的体检项目");
        }

        /**
         * 1. 更新体检项目信息
         * 2. 删除关联体检细项信息(遍历删除细项列表)
         * 3. 添加关联体检细项信息(遍历添加细项列表)
         */
        int projectRes = 0, addCount = 0, rmCount = 0;
        try {
            projectRes = medicalProjectMapper.updateByProjectId(medicalProject, medicalProject.getProjectId());
            if (projectRes <= 0) {
                throw new UpdateException("未知的修改错误", medicalProject.getProjectId());
            }


            //删除列表不为空，删除关联细项
            if (medicalProject.getItemRmIds() != null) {
                for (Integer itemRmId : medicalProject.getItemRmIds()) {
                    int res = medicalProjectItemMapper.deleteByPiProjectIdAndPiItemId(medicalProject.getProjectId(), itemRmId);
                    if (res >= 1) {
                        rmCount++;
                    }
                }
            }

            if (medicalProject.getItemAddIds() != null) {
                for (Integer itemAddId : medicalProject.getItemAddIds()) {
                    int res = medicalProjectItemMapper.addOneMedicalProjectItem(medicalProject.getProjectId(), itemAddId);
                    if (res >= 1) {
                        addCount++;
                    }
                }
            }

        } catch (Exception e) {
            throw new UpdateException("在更新细项列表时出错", medicalProject.getProjectId());
        }

        return ResponseDTO.success("更新成功");
    }

    @Override
    @Transactional
    public ResponseDTO deleteProjectAndProjectItem(List<Integer> ids) throws DeleteException{

        int count = 0;
        for (Integer id : ids) {
            //查看是否有套餐使用该项目
            List<MedicalProject> projects = medicalProjectMapper.findAllByProjectIdWithPackageProject(id);
            if(!projects.isEmpty()) {
                throw new DeleteException("有体检套餐正在使用该体检项目", id);
            }

            try{
                int res = medicalProjectMapper.deleteByProjectId(id);
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
    public ResponseDTO getAllProjects() {
        List<MedicalProject> projects = medicalProjectMapper.findAllByProjectIsDeleted(0);
        return ResponseDTO.success("获取成功", new EditNeedDTO<MedicalProject>(null , null, projects, new ArrayList<MedicalProject>(), null));
    }

}
