package com.cykj.model.pojo;

import java.util.Date;
import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author abin
 * @date 2024/8/19 14:55
 */
@Data
@NoArgsConstructor
public class MedicalCheckupSummary {
    /**
     * 总结ID
     */
    private Integer csId;

    /**
     * 订单ID
     */
    private Integer csOrderId;

    /**
     * 结果说明
     */
    private String csSummary;

    /**
     * 医生ID
     */
    private Integer csStaffId;

    /**
     * 时间
     */
    private Date csTime;

    /**
     * 体检总结状态 0未评 1已评
     */
    private Integer csStatus;

    /**
     * 关联的订单
     */
    private Order order;

    /**
     * 关联的用户信息
     */
    private User user;

}