package com.cykj.model.pojo;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 科室信息
 * @author abin
 * @date 2024/8/8 10:47
 */
@Data
@NoArgsConstructor
public class Department {
    /**
     * 科室ID
     */
    private Integer deptId;

    /**
     * 科室名称
     */
    private String deptName;

    /**
     * 父级科室ID
     */
    private Integer deptParentId;

    /**
     * 是否删除 (1: 已删除, 0: 未删除)
     */
    private Integer deptIsDeleted;
}