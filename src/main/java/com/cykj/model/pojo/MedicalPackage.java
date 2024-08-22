package com.cykj.model.pojo;

import java.math.BigDecimal;
import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 体检套餐
 * @author abin
 * @date 2024/8/8 10:47
 */
@Data
@NoArgsConstructor
public class MedicalPackage extends Medical{
    /**
     * 套餐ID
     */
    private Integer packageId;

    /**
     * 套餐名称
     */
    private String packageName;

    /**
     * 价格
     */
    private BigDecimal packagePrice;

    /**
     * 是否删除 (1: 已删除, 0: 未删除)
     */
    private Integer packageIsDeleted;

    /**
     * 查询价格区间(上限)
     */
    private BigDecimal beginPrice;

    /**
     * 查询价格区间(下限)
     */
    private BigDecimal endPrice;

    /**
     * 对应的体检项目列表
     */
    private List<MedicalProject> projects;
}