package com.cykj.model.vo;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * 开单VO
 * @author abin
 * @date 2024/8/16 20:27
 */

@Data
public class OrderVO {
    /**
     * 下单用户id
     */
    private Integer userId;

    /**
     * 订单总金额
     */
    private BigDecimal orderTotalAmount;

    /**
     * 体检编号
     */
    private String orderNumber;

    /**
     * 订单的套餐id列表
     */
    private List<Integer> packageAddIds;

    /**
     * 订单的项目id列表
     */
    private List<Integer> projectAddIds;

}
