package com.cykj.service.impl;

import com.cykj.mapper.MenuMapper;
import com.cykj.model.dto.InfoDTO;
import com.cykj.model.dto.ResponseDTO;
import com.cykj.model.pojo.Menu;
import com.cykj.model.pojo.Staff;
import com.cykj.model.pojo.User;
import com.cykj.model.vo.InfoVO;
import com.cykj.model.vo.PageVO;
import com.cykj.util.CommonUtil;
import com.cykj.util.JWTUtils;
import com.cykj.util.MD5Utils;
import com.cykj.util.StrUtils;
import com.cykj.util.tree.CaptchaUtils;
import com.cykj.util.tree.TreeBuilder;
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

    @Autowired
    public StaffServiceImpl(StaffMapper staffMapper, MenuMapper menuMapper) {
        this.staffMapper = staffMapper;
        this.menuMapper = menuMapper;
    }

    @Override
    public ResponseDTO login(String username, String password) {

        //查询用户是否存在
        Staff staff = staffMapper.findOneByStaffAccount(username);

        ResponseDTO dto = CommonUtil.passwordCheck(staff, password, staff.getStaffPassword());
        if(dto != null){
            return dto;
        }

        staff.setStaffPassword(null);

        //创建token
        HashMap<String, Object> data = new HashMap<>();
        data.put("staff", staff);
        String token = JWTUtils.generateToken(data);

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
            return ResponseDTO.fail("发送失败，邮箱不存在");
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
}
