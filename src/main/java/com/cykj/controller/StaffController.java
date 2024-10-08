package com.cykj.controller;

import com.cykj.annotation.Monitor;
import com.cykj.model.dto.ResponseDTO;
import com.cykj.model.pojo.Staff;
import com.cykj.model.vo.LoginVO;
import com.cykj.model.vo.PageVO;
import com.cykj.service.StaffService;
import com.cykj.util.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.LinkedHashMap;

/**
 * 管理员(工作人员)控制器
 * @author abin
 * @date 2024/8/7 16:10
 */

@RestController
@RequestMapping("/staff")
public class StaffController {

    private StaffService staffService;

    @Autowired
    public StaffController(StaffService staffService) {
        this.staffService = staffService;
    }

    @RequestMapping("/search")
    public ResponseDTO search(@RequestBody PageVO<Staff> pageVO){
        return staffService.getStaffs(pageVO);
    }

    @PostMapping("/login")
    public ResponseDTO login(@RequestBody LoginVO loginVo, HttpSession httpSession){
        //验证码判断
        String code = (String) httpSession.getAttribute("code");

        //登录基础检查(判空，验证码比对)
        ResponseDTO dto = CommonUtils.loginCheck(loginVo.getUsername(), loginVo.getPassword(), loginVo.getCode(), code,true);
        if(dto != null){
            return dto;
        }

        return staffService.login(loginVo.getUsername(), loginVo.getPassword(), httpSession);
    }

    @PostMapping("/email/code")
    public ResponseDTO getEmailCode(@RequestBody LoginVO loginVo, HttpSession httpSession, HttpServletResponse response){
        return staffService.sendEmailCode(loginVo.getUsername(), httpSession, response);
    }

    @PostMapping("/info")
    public ResponseDTO getStaffInfo(HttpServletRequest request){
        LinkedHashMap<String, Object> staff = CommonUtils.parseTokenInfo("staff", request);

        if(staff != null){
            Integer staffId = (Integer) staff.get("staffId");
            Integer roleId = (Integer) staff.get("staffRoleId");
            if(staffId != null && roleId != null){
                return staffService.getStaffInfo(staffId, roleId);
            }
        }

        return ResponseDTO.fail("错误的登录凭证");
    }

    @RequestMapping("/edit/need")
    public ResponseDTO getRolesAndDepts(){
        return staffService.getRolesAndDepts();
    }

    @RequestMapping("/edit")
    @Monitor("添加/编辑工作人员(管理员)")
    public ResponseDTO editStaff(@RequestBody Staff staff){
        if(staff.getStaffId() == null){
            return staffService.addOneStaff(staff);
        } else {
            return staffService.editStaff(staff);
        }
    }
}
