package com.cykj.model.pojo;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

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

    /**
     * 项目小结
     */
    private List<MedicalProjectSummary> projectSummaries;

    /**
     * 对应用户基本信息
     */
    private User user;

    public Order(String orderNumber, Integer orderUserId, BigDecimal orderTotalAmount, Integer orderStatus) {
        this.orderNumber = orderNumber;
        this.orderUserId = orderUserId;
        this.orderTotalAmount = orderTotalAmount;
        this.orderStatus = orderStatus;
    }

    public Order(String orderNumber) {
        this.orderNumber = orderNumber;
    }
}