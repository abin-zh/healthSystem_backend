package com.cykj.model.pojo;

import java.math.BigDecimal;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 交易流水信息
 * @author abin
 * @date 2024/8/8 10:47
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Balanceflow {
    /**
     * 流水ID
     */
    private Integer flowId;

    /**
     * 交易订单号
     */
    private String flowOrderNumber;


    /**
     * 病人ID
     */
    private Integer flowUserId;

    /**
     * 时间
     */
    private Date flowTime;

    /**
     * 类型 0消费 1充值 2退款
     */
    private Integer flowType;

    /**
     * 金额
     */
    private BigDecimal flowAmount;

    /**
     * 说明
     */
    private String flowDescription;

    /**
     * 交易状态
     */
    private Integer flowStatus;

    public Balanceflow(String flowOrderNumber, Integer flowUserId, Integer flowType, BigDecimal flowAmount) {
        this.flowOrderNumber = flowOrderNumber;
        this.flowUserId = flowUserId;
        this.flowType = flowType;
        this.flowAmount = flowAmount;
    }

    public Balanceflow(Date flowTime, String flowDescription, Integer flowStatus) {
        this.flowTime = flowTime;
        this.flowDescription = flowDescription;
        this.flowStatus = flowStatus;
    }
}