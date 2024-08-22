package com.cykj.interceptor;

import com.alibaba.fastjson.JSON;
import com.cykj.model.dto.ResponseDTO;
import com.cykj.util.JWTUtils;
import com.cykj.util.StrUtils;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @author abin
 * @date 2024/8/11 18:21
 */
public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //获取到token
        String token = request.getHeader("token");
        response.setContentType("application/json;charset=utf-8");

        //判断token是否存在
        if(StrUtils.isEmpty(token)){
            response.getOutputStream().write(JSON.toJSONBytes(ResponseDTO.fail(402,"token不存在，请重新登录")));
            return false;
        }

        Map<String, Object> stringObjectMap = null;
        try{
            stringObjectMap = JWTUtils.parseToken(token);
        } catch (ExpiredJwtException e){
            response.getOutputStream().write(JSON.toJSONBytes(ResponseDTO.fail(402,"token已过期，请重新登录")));
            return false;
        }

        return true;
    }
}
