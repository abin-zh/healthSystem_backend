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
public class Balanceflow {
    /**
     * 流水ID
     */
    private Integer flowId;

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
    private String flowType;

    /**
     * 金额
     */
    private BigDecimal flowAmount;

    /**
     * 说明
     */
    private String flowDescription;
}