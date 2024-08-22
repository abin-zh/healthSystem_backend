package com.cykj.model.pojo;

import com.cykj.util.tree.TreeNode;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author abin
 * @date 2024/8/8 10:47
 */
@Data
@NoArgsConstructor
public class Menu extends TreeNode<Menu> implements Cloneable{

    /**
     * 菜单组件
     */
    private String component;

    /**
     * 菜单路径
     */
    private String path;

    /**
     * 是否删除 (1: 已删除, 0: 未删除)
     */
    private Integer menuIsDeleted;

    /**
     * 使用状态 (1: 活跃, 0: 非活跃)
     */
    private Integer menuStatus;

    /**
     * 重定向路径
     */
    private String redirect;

    /**
     * 菜单元信息
     */
    private Meta meta;

    /**
     * 菜单标题
     */
    private String title;

    private String icon;
}