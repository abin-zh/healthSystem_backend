package com.cykj.model.pojo;

import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author abin
 * @date 2024/8/8 10:47
 */
@Data
@NoArgsConstructor
public class Order {
    /**
     * 订单ID
     */
    private Integer orderId;

    /**
     * 订单编号（体检编号）
     */
    private String orderNumber;

    /**
     * 病人ID
     */
    private Integer orderUserId;

    /**
     * 下单时间
     */
    private Date orderTime;

    /**
     * 支付总金额
     */
    private BigDecimal orderTotalAmount;

    /**
     * 支付状态 0未支付 1已支付
     */
    private Integer orderStatus;
}