package com.cykj.model.pojo;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 权限分配信息
 * @author abin
 * @date 2024/8/8 10:47
 */
@Data
@NoArgsConstructor
public class Permission {
    /**
     * 权限ID
     */
    private Integer permId;

    /**
     * 菜单ID
     */
    private Integer permMenuId;

    /**
     * 角色ID
     */
    private Integer permRoleId;
}