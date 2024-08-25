package com.cykj.model.vo;

import lombok.Data;

/**
 * 登录信息VO
 * @author abin
 * @date 2024/8/8 11:05
 */

@Data
public class LoginVO {

    String username;

    String password;

    String code;
}
