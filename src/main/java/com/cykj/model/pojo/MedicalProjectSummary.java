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
public class MedicalProjectSummary {
    /**
     * 小结ID
     */
    private Integer psId;

    /**
     * 订单ID
     */
    private Integer psOrderId;

    /**
     * 项目ID
     */
    private Integer psProjectId;

    /**
     * 小结
     */
    private String psSummary;

    /**
     * 医生id
     */
    private Integer psStaffId;

    /**
     * 时间
     */
    private Date time;
}