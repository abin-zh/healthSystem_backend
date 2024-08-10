package com.cykj.model.pojo;

import java.math.BigDecimal;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author abin
 * @date 2024/8/8 10:47
 */
@Data
@NoArgsConstructor
public class MedicalItem {
    /**
     * 细项ID
     */
    private Integer itemId;

    /**
     * 细项名称
     */
    private String itemName;

    /**
     * 单位
     */
    private String itemUnit;

    /**
     * 上限
     */
    private BigDecimal itemUpperLimit;

    /**
     * 下限
     */
    private BigDecimal itemLowerLimit;

    /**
     * 是否删除 (1: 已删除, 0: 未删除)
     */
    private Boolean itemIsDeleted;
}