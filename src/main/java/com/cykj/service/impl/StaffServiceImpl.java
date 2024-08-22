package com.cykj.service.impl;

import com.cykj.mapper.DepartmentMapper;
import com.cykj.mapper.MenuMapper;
import com.cykj.mapper.RoleMapper;
import com.cykj.model.dto.EditNeedDTO;
import com.cykj.model.dto.InfoDTO;
import com.cykj.model.dto.ResponseDTO;
import com.cykj.model.pojo.Department;
import com.cykj.model.pojo.Role;
import com.cykj.model.pojo.Staff;
import com.cykj.model.vo.InfoVO;
import com.cykj.model.vo.PageVO;
import com.cykj.util.*;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import org.springframework.beans.factory.annotation.Autowired;

import com.cykj.mapper.StaffMapper;
import com.cykj.service.StaffService;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;

/**
 * 管理员(工作人员)实现层
 * @author abin
 * @date 2024/8/8 10:47
*/
@Service
public class StaffServiceImpl implements StaffService{

    private StaffMapper staffMapper;

    private MenuMapper menuMapper;

    private RoleMapper roleMapper;

    private DepartmentMapper deptMapper;

    public StaffServiceImpl(StaffMapper staffMapper, MenuMapper menuMapper, RoleMapper roleMapper, DepartmentMapper deptMapper) {
        this.staffMapper = staffMapper;
        this.menuMapper = menuMapper;
        this.roleMapper = roleMapper;
        this.deptMapper = deptMapper;
    }

    @Override
    public ResponseDTO login(String username, String password, HttpSession session) {

        //查询用户是否存在
        Staff staff = staffMapper.findOneByStaffAccount(username);

        ResponseDTO dto = CommonUtils.passwordCheck(staff, password, staff.getStaffPassword());
        if(dto != null){
            return dto;
        }

        staff.setStaffPassword(null);

        //创建token
        HashMap<String, Object> data = new HashMap<>();
        data.put("staff", staff);
        String token = JWTUtils.generateToken(data);
        session.removeAttribute("code");

        return ResponseDTO.success("登录成功", new InfoVO<Staff>(token, staff));
    }

    @Override
    public ResponseDTO sendEmailCode(String username, HttpSession httpSession, HttpServletResponse response) {
        //判空
        if(StrUtils.isEmpty(username)){
            return ResponseDTO.fail("发送失败，账号为空");
        }

        //查询用户是否存在
        Staff staff = staffMapper.findOneByStaffAccount(username);

        //若邮箱不存在则发送不成功
        if(staff == null || StrUtils.isEmpty(staff.getStaffEmail())){
            return ResponseDTO.fail("发送失败，管理员或邮箱不存在");
        }

        return  CaptchaUtils.sendSmtp(staff.getStaffEmail(), httpSession, response) ? ResponseDTO.success("发送成功") : ResponseDTO.fail("发送失败");
    }

    @Override
    public ResponseDTO getStaffs(PageVO<Staff> pageVO) {
        Page<Object> page = PageHelper.startPage(pageVO.getPageNo(), pageVO.getPageSize());
        List<Staff> allWithPage = staffMapper.findAllWithPage();
        PageInfo<Object> pageInfo = new PageInfo<>(allWithPage);
        return ResponseDTO.success((int) pageInfo.getTotal(), allWithPage);
    }

    @Override
    public ResponseDTO getStaffInfo(int staffId,int roleId) {
        Staff staff = staffMapper.findOneByStaffId(staffId);
        return staff != null ? ResponseDTO.success("获取成功", new InfoDTO(staff.getStaffName(), staff.getStaffAvatar(), null)) : ResponseDTO.fail("获取失败");
    }

    @Override
    public ResponseDTO getRolesAndDepts() {
        List<Role> roles = roleMapper.findAllByRoleIsDelete(0);
        List<Department> depts = deptMapper.findAllByDeptIsDeleted(0);
        return ResponseDTO.success("获取成功", new EditNeedDTO<>(depts, roles, null, null, null));
    }

    @Override
    public ResponseDTO addOneStaff(Staff staff) {
        /**
         * 1.判空
         * 2.两次输入密码比对
         * 3.查看是否管理员是否已经存在
         * 4.添加管理员
         */
        if(StrUtils.hasEmpty(staff.getStaffName(), staff.getStaffAccount(), staff.getStaffEmail(), staff.getStaffPassword(), staff.getStaffCPassword())
                || staff.getStaffDeptId() == null || staff.getStaffRoleId() == null){
            return ResponseDTO.fail("添加失败，未提供需求信息");
        } else if (!staff.getStaffPassword().equals(staff.getStaffCPassword())) {
            return ResponseDTO.fail("添加失败，两次输入密码不一致");
        }

        Staff staffInfo = staffMapper.findOneByStaffAccount(staff.getStaffAccount());
        if(staffInfo != null){
            return ResponseDTO.fail("添加失败，管理员账号已存在");
        }

        staff.setStaffPassword(MD5Utils.encrypt(staff.getStaffPassword()));
        int res = staffMapper.addOneStaff(staff);

        return res >= 1 ? ResponseDTO.success("添加成功") : ResponseDTO.fail("添加失败");
    }

    @Override
    public ResponseDTO editStaff(Staff staff) {
        int res = staffMapper.updateByStaffId(staff, staff.getStaffId());
        return res >= 1 ? ResponseDTO.success("修改成功") : ResponseDTO.fail("修改失败");
    }

}
