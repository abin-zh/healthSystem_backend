package com.cykj.util.tree;

import com.cykj.model.pojo.Menu;

import java.util.Comparator;

/**
 * @author abin
 * @date 2024/7/20 23:16
 */
public class TreeComparator implements Comparator<Menu> {
    @Override
    public int compare(Menu o1, Menu o2) {
        int order1 = o1.getOrder();
        int order2 = o2.getOrder();
        return order1 - order2;
    }
}
