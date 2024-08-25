package com.cykj.model.pojo;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 角色信息
 * @author abin
 * @date 2024/8/8 10:47
 */
@Data
@NoArgsConstructor
public class Role extends Medical{
    /**
     * 角色ID
     */
    private Integer roleId;

    /**
     * 角色名称
     */
    private String roleName;

    /**
     * 角色是否删除(0未删除 1已删除)
     */
    private Integer roleIsDelete;
}