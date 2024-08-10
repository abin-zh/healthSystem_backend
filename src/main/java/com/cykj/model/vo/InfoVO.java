package com.cykj.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 用户(管理员)信息VO
 * @author abin
 * @date 2024/8/9 17:36
 */

@Data
@AllArgsConstructor
public class InfoVO<T> {

    /**
     * 登录凭证
     */
    private String token;

    /**
     * 用户信息
     */
    private T info;
}
