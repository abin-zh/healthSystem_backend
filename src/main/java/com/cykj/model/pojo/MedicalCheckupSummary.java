package com.cykj.model.pojo;

import java.util.Date;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author abin
 * @date 2024/8/8 10:47
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
}