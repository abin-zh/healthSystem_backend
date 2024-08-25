package com.cykj.aspect;

import cn.hutool.core.bean.BeanUtil;
import com.cykj.annotation.Monitor;
import com.cykj.mapper.LogMapper;
import com.cykj.model.pojo.Staff;
import com.cykj.util.CommonUtils;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.LinkedHashMap;

/**
 * 日志切面(日志监控)
 * @author abin
 * @date 2024/8/22 0:54
 */

@Slf4j
@Aspect
@Component
public class LoggingAspect {

    private HttpServletRequest request;

    private LogMapper logMapper;

    @Autowired
    public LoggingAspect(HttpServletRequest request, LogMapper logMapper) {
        this.request = request;
        this.logMapper = logMapper;
    }

    /**
     * 定义切点，匹配带有 @Monitor 注解的方法
     * @param monitor
     */
    @Pointcut("@annotation(monitor)")
    public void monitorPointcut(Monitor monitor){

    }

    @After("monitorPointcut(monitor)")
    public void logAfter(JoinPoint joinPoint, Monitor monitor){
        LinkedHashMap<String, Object> staffMap = CommonUtils.parseTokenInfo("staff", request);
        Staff staff = BeanUtil.mapToBean(staffMap, Staff.class, true);
        String str = staff.getStaffAccount() + "执行了" + monitor.value();
        logMapper.addOneLog(staff.getStaffId(), str);
        log.info(str);
    }
}
