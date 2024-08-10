package com.cykj.controller;
import com.cykj.model.dto.ResponseDTO;
import com.cykj.model.pojo.User;
import com.cykj.model.vo.LoginVO;
import com.cykj.model.vo.PageVO;
import com.cykj.service.UsersService;
import com.cykj.util.CommonUtil;
import org.springframework.web.bind.annotation.*;

import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
    public ResponseDTO search(@RequestBody PageVO<User> pageVO){
        return usersService.getUsers(pageVO);
    }

    @PostMapping("/login")
    public ResponseDTO login(@RequestBody LoginVO loginVo, HttpSession httpSession){
        //验证码判断
        String code = (String) httpSession.getAttribute("code");

        //登录基础检查(判空，验证码比对)
        ResponseDTO dto = CommonUtil.loginCheck(loginVo.getUsername(), loginVo.getPassword(), loginVo.getCode(), code,false);
        if(dto != null){
            return dto;
        }

        return usersService.login(loginVo.getUsername(), loginVo.getPassword());
    }

    @PostMapping("/email/code")
    public ResponseDTO getEmailCode(@RequestBody LoginVO loginVo, HttpSession httpSession, HttpServletResponse response){
        return usersService.sendEmailCode(loginVo.getUsername(), httpSession, response);
    }

    @PostMapping("/register")
    public ResponseDTO register(@RequestBody User user){
        return usersService.register(user);
    }
}
