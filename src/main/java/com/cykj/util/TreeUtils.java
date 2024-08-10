package com.cykj.util;

import com.cykj.model.pojo.Menu;
import com.cykj.util.tree.TreeBuilder;

import java.util.List;

/**
 * @author abin
 * @date 2024/7/20 21:36
 */
public class TreeUtils {

    public static List<Menu> build(List<Menu> menus){
        return TreeBuilder.build(menus);
    }

    public static List<Menu> build(List<Menu> menus, boolean isAdd){
        return TreeBuilder.build(menus, isAdd);
    }

    public static List<Menu> buildUseMap(List<Menu> menus){
        return TreeBuilder.buildUseMap(menus);
    }
}
