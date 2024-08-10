package com.cykj.model.pojo;

import java.math.BigDecimal;
import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author abin
 * @date 2024/8/8 10:47
 */
@Data
@NoArgsConstructor
public class MedicalProject {
    /**
     * 项目ID
     */
    private Integer projectId;

    /**
     * 项目名称
     */
    private String projectName;

    /**
     * 价格
     */
    private BigDecimal projectPrice;

    /**
     * 是否删除 (1: 已删除, 0: 未删除)
     */
    private Boolean projectIsDeleted;

    /**
     * 查询价格区间(下限)
     */
    private BigDecimal beginPrice;

    /**
     * 查询价格区间(上限)
     */
    private BigDecimal endPrice;

    /**
     *
     */
    private Integer projectDeptId;

    private List<MedicalItem> items;
}