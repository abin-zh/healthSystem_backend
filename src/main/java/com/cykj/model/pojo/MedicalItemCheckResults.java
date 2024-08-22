package com.cykj.model.pojo;

import java.util.Date;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author abin
 * @date 2024/8/19 14:56
 */
@Data
@NoArgsConstructor
public class MedicalItemCheckResults {
    /**
     * 检查结果ID
     */
    private Integer icrId;

    /**
     * 细项ID
     */
    private Integer icrItemId;

    /**
     * 结果(数值)
     */
    private String icrResult;

    /**
     * 订单ID
     */
    private Integer icrOrderId;

    /**
     * 医生ID
     */
    private Integer icrStaffId;

    /**
     * 时间
     */
    private Date icrTime;

    /**
     * 检查项目id
     */
    private Integer icrProjectSummaryId;

    /**
     * 细项结果状态 0未评 1已评
     */
    private Integer icrStatus;

    /**
     * 细项结果
     */
    private String icrSummary;

    /**
     * 关联的体检细项
     */
    private MedicalItem item;

    /**
     * 关联的医生
     */
    private Staff staff;

    public MedicalItemCheckResults(Integer icrItemId, Integer icrOrderId, Integer icrProjectSummaryId) {
        this.icrItemId = icrItemId;
        this.icrOrderId = icrOrderId;
        this.icrProjectSummaryId = icrProjectSummaryId;
    }
}