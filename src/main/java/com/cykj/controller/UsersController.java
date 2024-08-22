package com.cykj.controller;

import cn.hutool.core.annotation.Alias;
import cn.hutool.core.util.ReflectUtil;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.cykj.exception.AddException;
import com.cykj.exception.CurdException;
import com.cykj.model.dto.ResponseDTO;
import com.cykj.model.dto.UserExcelVO;
import com.cykj.model.pojo.User;
import com.cykj.model.vo.DelVO;
import com.cykj.model.vo.LoginVO;
import com.cykj.model.vo.PageVO;
import com.cykj.service.UsersService;
import com.cykj.util.CommonUtils;
import com.cykj.util.ExcelUtils;
import com.cykj.util.StrUtils;
import org.springframework.web.bind.annotation.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.Field;
import java.net.URLEncoder;
import java.util.*;

/**
 * 用户控制层
 *
 * @author abin
 */
@RestController
@RequestMapping("/user")
public class UsersController {

    private UsersService usersService;

    @Autowired
    public UsersController(UsersService usersService) {
        this.usersService = usersService;
    }

    @RequestMapping("/search")
    public ResponseDTO search(@RequestBody PageVO<User> pageVO) {
        return usersService.getUsers(pageVO);
    }

    @PostMapping("/login")
    public ResponseDTO login(@RequestBody LoginVO loginVo, HttpSession httpSession) {
        //验证码判断
        String code = (String) httpSession.getAttribute("code");

        //登录基础检查(判空，验证码比对)
        ResponseDTO dto = CommonUtils.loginCheck(loginVo.getUsername(), loginVo.getPassword(), loginVo.getCode(), code, false);
        if (dto != null) {
            return dto;
        }

        return usersService.login(loginVo.getUsername(), loginVo.getPassword());
    }

    @PostMapping("/email/code")
    public ResponseDTO getEmailCode(@RequestBody LoginVO loginVo, HttpSession httpSession, HttpServletResponse response) {
        return usersService.sendEmailCode(loginVo.getUsername(), httpSession, response);
    }

    @PostMapping("/register")
    public ResponseDTO register(@RequestBody User user) {
        return usersService.register(user);
    }

    @PostMapping("edit")
    public ResponseDTO editUser(@RequestBody User user) {

        if (user.getUserId() == null) {
            return usersService.register(user);
        } else {
            return usersService.editUser(user);
        }
    }

    @PostMapping("del")
    public ResponseDTO deleteUser(@RequestBody DelVO delVO) {
        ResponseDTO dto = CommonUtils.checkDelVO(delVO);
        if (dto != null) {
            return dto;
        }

        try {
            return usersService.deleteUser(delVO.getIds());
        } catch (CurdException e) {
            return ResponseDTO.fail(e.getMessage());
        }
    }

    @RequestMapping("template/export")
    public ResponseDTO downloadTemplate(HttpServletRequest request, HttpServletResponse response) {
        LinkedHashMap<String, Object> staff = CommonUtils.parseTokenInfo("staff", request);
        if(staff == null){
            return ResponseDTO.fail("您的角色无法下载文件");
        }
        try {
            Field[] fields = ReflectUtil.getFields(UserExcelVO.class);
            List<String> header = new ArrayList<>();
            for (Field field : fields) {
                Alias annotation = field.getAnnotation(Alias.class);
                if(annotation != null){
                    header.add(annotation.value());
                }
            }
            ExcelUtils.exportHeaders(response, header, "用户模板");
        } catch (Exception e) {
            return ResponseDTO.fail("获取用户模板时出现异常");
        }
        return ResponseDTO.success("获取用户模板成功");
    }

    @PostMapping("/add/multiple")
    public ResponseDTO addUsers(@RequestParam("file") MultipartFile file){
        String contentType = file.getContentType();
        if(!StrUtils.isEmpty(contentType) && !contentType.equals(ExcelUtil.XLS_CONTENT_TYPE) && !contentType.equals(ExcelUtil.XLSX_CONTENT_TYPE)){
            return ResponseDTO.fail("请提供正确的文件");
        }
        try {
            List<User> users = ExcelUtils.readExcelRows(file, User.class);
            if(users == null || users.isEmpty()){
                return ResponseDTO.fail("插入失败，未提供用户信息");
            }
            return usersService.addUsers(users);
        } catch (IOException e){
            return ResponseDTO.fail("解析用户信息异常");
        } catch (AddException e) {
            return ResponseDTO.fail(e.getMessage());
        }
    }
}
