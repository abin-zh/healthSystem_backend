package com.cykj.model.vo;

import cn.hutool.core.annotation.Alias;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 用户excel表头对照实体
 * @author abin
 * @date 2024/8/14 13:29
 */
public class UserExcelVO {

    /**
     * 姓名
     */
    @Alias("姓名")
    private String userName;

    /**
     * 手机号
     */
    @Alias("手机号")
    private String userPhone;

    /**
     * 身份证号
     */
    @Alias("身份证号")
    private String userIdCard;

    /**
     * 用户邮箱
     */
    @Alias("邮箱")
    private String userEmail;

    /**
     * 出生日期
     */
    @Alias("出生日期")
    private Date userBirthday;

    /**
     * 性别
     */
    @Alias("性别")
    private Integer userGender;

}
