package com.cykj.util.tree;

import com.alibaba.fastjson2.JSON;
import com.cykj.model.pojo.Menu;

import java.util.*;

/**
 * @author abin
 * @date 2024/7/20 22:38
 */
public class TreeBuilder {

    public static List<Menu> build(List<Menu> menus){
        return buildTree(0, menus, new Menu()).getChildren();
    }
    public static List<Menu> build(List<Menu> menus, boolean isAdd){
        return buildTree(menus, isAdd).getChildren();
    }

    public static List<Menu> buildUseMap(List<Menu> menus) {
        return buildTreeUseMap(menus).getChildren();
    }

    private static Menu buildTree(int parentId, List<Menu> menus, Menu node){
        List<Menu> children = new ArrayList<>();
        for (Menu menu : menus) {
            if(menu.getParentId() == parentId){
                Menu child = buildTree(menu.getId(), menus, menu);
                children.add(child);
            }
        }
        children.sort(new TreeComparator());
        node.setChildren(children);
        return node;
    }

    private static Menu buildTreeUseMap(List<Menu> menus){
        HashMap<Integer, Menu> map = new HashMap<>();
        for (Menu menu : menus) {
            menu.setChildren(new ArrayList<Menu>());
            map.put(menu.getId(), menu);
        }

        Menu root = new Menu();
        root.setId(0);
        root.setChildren(new ArrayList<Menu>());

        for (Integer id : map.keySet()) {
            Menu menuItem = map.get(id);
            if(menuItem.getParentId() == 0){
                root.getChildren().add(menuItem);
            } else {
                map.get(menuItem.getParentId()).getChildren().add(menuItem);
            }
        }
        return root;
    }

    private static Menu buildTree(List<Menu> menus, boolean isAdd){
        HashMap<Integer, Menu> map = new HashMap<>();
        for (Menu menu : menus) {
            map.put(menu.getId(), menu);
        }

        //构建集合，根据添加标记存储菜单id及其父菜单id
        Set<Integer> set = new HashSet<>();
        for (Menu menu : menus) {
            //添加标记与指定标记相同
            if(menu.isAdd() == isAdd){
                //添加当前菜单id到集合
                set.add(menu.getId());
                Menu menuItem = menu;
                //不断地将当前菜单的祖先菜单id添加到集合中
                while (true){
                    //已找到其根菜单或集合中已包含其父菜单id
                    if(menuItem.getParentId() == 0 || set.contains(menuItem.getParentId())){
                        set.add(menu.getId());
                        break;
                    }
                    menuItem = map.get(menuItem.getParentId());
                    set.add(menuItem.getId());
                }
            }
        }

        List<Menu> newMenus = new ArrayList<>();

        for (Menu menu : menus) {
            if(set.contains(menu.getId())){
                newMenus.add(JSON.parseObject(JSON.toJSONString(menu), Menu.class));
            }
        }

        return buildTreeUseMap(newMenus);
    }

}
