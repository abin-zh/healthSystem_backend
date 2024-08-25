package com.cykj.model.pojo;

import java.util.Date;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 体检总结信息
 * @author abin
 * @date 2024/8/19 14:56
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
    private Date psTime;

    /**
     * 项目小结状态 0未评 1已评
     */
    private Integer psStatus;

    /**
     * 关联体检项目
     */
    private MedicalProject project;

    /**
     * 关联医生
     */
    private Staff staff;

    public MedicalProjectSummary(Integer psOrderId, Integer psProjectId) {
        this.psOrderId = psOrderId;
        this.psProjectId = psProjectId;
    }
}