package com.cykj.model.pojo;

import java.math.BigDecimal;
import java.util.Date;

import cn.hutool.core.annotation.Alias;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author abin
 * @date 2024/8/8 10:47
 */
@Data
@NoArgsConstructor
public class User {
    /**
     * 用户ID
     */
    private Integer userId;

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
     * 姓名
     */
    @Alias("姓名")
    private String userName;

    /**
     * 性别
     */
    @Alias("性别")
    private Integer userGender;

    /**
     * 出生日期
     */
    @Alias("出生日期")
    private Date userBirthday;

    /**
     * 余额
     */
    private BigDecimal userBalance;

    /**
     * 密码
     */
    private String userPassword;

    /**
     * 是否删除
     */
    private Integer userIsDeleted;

    /**
     * 用户邮箱
     */
    @Alias("邮箱")
    private String userEmail;

    /**
     * 确认密码
     */
    private String userCPassword;

    /**
     * 用户头像
     */
    private String userAvatar;
}