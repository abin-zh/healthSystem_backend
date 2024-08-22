package com.cykj.interceptor;

import com.cykj.model.pojo.RoleMap;
import com.cykj.service.RoleService;
import com.cykj.util.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 分检医生拦截器
 * @author abin
 * @date 2024/8/18 23:40
 */
public class DoctorInterceptor implements HandlerInterceptor {

    private RoleMap roleMap;

    private RoleService roleService;

    @Autowired
    public DoctorInterceptor(RoleMap roleMap, RoleService roleService) {
        this.roleMap = roleMap;
        this.roleService = roleService;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        return CommonUtils.checkRole("分检医生", roleMap, roleService, request, response);
    }
}
