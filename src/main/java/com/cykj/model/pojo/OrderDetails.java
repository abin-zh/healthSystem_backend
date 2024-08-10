package com.cykj.model.pojo;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author abin
 * @date 2024/8/8 10:47
 */
@Data
@NoArgsConstructor
public class OrderDetails {
    /**
     * 订单详情ID
     */
    private Integer odId;

    /**
     * 订单编号
     */
    private String odOrderNumber;

    /**
     * 项目/套餐ID
     */
    private Integer odProjectId;

    /**
     * 类型 0项目 1套餐
     */
    private Integer odType;
}