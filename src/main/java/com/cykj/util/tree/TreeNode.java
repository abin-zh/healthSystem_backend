package com.cykj.util.tree;

import com.cykj.model.pojo.Menu;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author abin
 * @date 2024/7/20 21:36
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class TreeNode<T>{
    private Integer id;

    private Integer parentId;

    private Integer order;

    private String name;

    private List<T> children;

    private boolean isAdd;

    private String parentTitle;

}
