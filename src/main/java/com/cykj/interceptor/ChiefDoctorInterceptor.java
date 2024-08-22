package com.cykj.interceptor;

import com.cykj.model.pojo.RoleMap;
import com.cykj.service.RoleService;
import com.cykj.util.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 总检医生拦截器
 * @author abin
 * @date 2024/8/19 19:57
 */
public class ChiefDoctorInterceptor implements HandlerInterceptor {

    private RoleMap roleMap;

    private RoleService roleService;

    @Autowired
    public ChiefDoctorInterceptor(RoleMap roleMap, RoleService roleService) {
        this.roleMap = roleMap;
        this.roleService = roleService;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        return CommonUtils.checkRole("总检医生", roleMap, roleService, request, response);
    }
}
