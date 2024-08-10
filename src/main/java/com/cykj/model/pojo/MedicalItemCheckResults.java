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
     * 结果
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
}