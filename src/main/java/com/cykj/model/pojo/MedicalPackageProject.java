package com.cykj.model.pojo;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author abin
 * @date 2024/8/8 10:47
 */
@Data
@NoArgsConstructor
public class MedicalPackageProject {
    /**
     * 套餐项目ID
     */
    private Integer ppContentId;

    /**
     * 套餐ID
     */
    private Integer ppPackageId;

    /**
     * 项目ID
     */
    private Integer ppProjectId;
}