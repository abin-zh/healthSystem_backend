package com.cykj.service.impl;

import com.cykj.exception.DeleteException;
import com.cykj.mapper.StaffMapper;
import com.cykj.model.dto.ResponseDTO;
import com.cykj.model.pojo.Department;
import com.cykj.model.pojo.Staff;
import com.cykj.model.vo.PageVO;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import org.springframework.beans.factory.annotation.Autowired;

import com.cykj.mapper.DepartmentMapper;
import com.cykj.service.DepartmentService;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author abin
 * @date 2024/8/8 10:47
*/
@Service
public class DepartmentServiceImpl implements DepartmentService{

    private DepartmentMapper departmentMapper;
    private StaffMapper staffMapper;

    @Autowired
    public DepartmentServiceImpl(DepartmentMapper departmentMapper, StaffMapper staffMapper) {
        this.departmentMapper = departmentMapper;
        this.staffMapper = staffMapper;
    }

    @Override
    public ResponseDTO getDepts(PageVO<Department> pageVO) {
        Page<Object> page = PageHelper.startPage(pageVO.getPageNo(), pageVO.getPageSize());
        List<Department> allWithPage = departmentMapper.findAllWithPage(pageVO.getData());
        PageInfo<Object> pageInfo = new PageInfo<>(allWithPage);
        return ResponseDTO.success((int) pageInfo.getTotal(), allWithPage);
    }

    @Override
    public ResponseDTO getAllDepts() {
        //查询未删除的
        List<Department> depts = departmentMapper.findAllByDeptIsDeleted(0);
        return ResponseDTO.success("获取成功", depts);
    }

    @Override
    public ResponseDTO addOneDept(String deptName) {
        Department dept = departmentMapper.findOneByDeptName(deptName);
        //科室是否存在
        if(dept != null){
            return ResponseDTO.fail("科室已存在，请勿重复创建");
        }

        int res = departmentMapper.addOneDept(deptName);
        return res >= 1 ? ResponseDTO.success("添加成功") : ResponseDTO.fail("添加失败");
    }

    @Override
    public ResponseDTO editDept(Department department) {
        Department dept = departmentMapper.findOneByDeptName(department.getDeptName());
        //科室是否存在
        if(dept != null){
            return ResponseDTO.fail("修改失败，科室已存在");
        }

        int res = departmentMapper.updateByDeptId(department, department.getDeptId());
        return res >= 1 ? ResponseDTO.success("修改成功") : ResponseDTO.fail("修改失败");
    }

    @Override
    @Transactional
    public ResponseDTO deleteDept(List<Integer> ids) throws DeleteException{
        int count = 0;
        for (Integer id : ids) {
            //查看科室下是否有医生
            List<Staff> staffs = staffMapper.findAllByStaffDeptId(id);
            if(!staffs.isEmpty()) {
                throw new DeleteException("该科室下还有医生", id);
            }
            //尝试删除
            int res = -1;
            try{
                res = departmentMapper.deleteByDeptId(id);
            } catch (Exception e){
                throw new DeleteException("删除时出现异常", id);
            }
            if(res >= 1){
                count++;
            }
        }

        return count == ids.size() ? ResponseDTO.success("删除成功") : ResponseDTO.fail("未知删除失败情况");
    }
}
