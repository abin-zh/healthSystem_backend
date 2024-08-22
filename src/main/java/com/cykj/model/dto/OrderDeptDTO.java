package com.cykj.model.dto;

import com.cykj.model.pojo.MedicalProjectSummary;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 科室关联订单DTO
 * @author abin
 * @date 2024/8/18 16:14
 */

@Data
public class OrderDeptDTO {

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
     * 对应的项目小结列表
     */
    List<MedicalProjectSummary> summaries;

}
