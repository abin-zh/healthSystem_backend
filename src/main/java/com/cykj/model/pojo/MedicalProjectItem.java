package com.cykj.model.pojo;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author abin
 * @date 2024/8/12 16:52
 */
@Data
@NoArgsConstructor
public class MedicalProjectItem {
    /**
     * 项目细项关联ID
     */
    private Integer piId;

    /**
     * 项目ID
     */
    private Integer piProjectId;

    /**
     * 细项ID
     */
    private Integer piItemId;
}