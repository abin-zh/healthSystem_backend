package com.cykj.util.tree;

import com.cykj.model.pojo.Menu;

import java.util.List;

/**
 * @author abin
 * @date 2024/7/20 22:36
 */
public class Tree{

    private Menu node;
    private List<Tree> child;

    public Tree(Menu node, List<Tree> child) {
        this.node = node;
        this.child = child;
    }

    public Menu getNode() {
        return node;
    }

    public void setNode(Menu node) {
        this.node = node;
    }

    public List<Tree> getChild() {
        return child;
    }

    public void setChild(List<Tree> child) {
        this.child = child;
    }
}
