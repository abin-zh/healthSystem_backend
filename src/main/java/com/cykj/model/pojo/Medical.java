package com.cykj.model.pojo;

import lombok.Data;

import java.util.List;

/**
 * 体检基类(一些通用属性)
 * @author abin
 * @date 2024/8/13 13:33
 */

@Data
public class Medical {

    /**
     * 需要添加的id
     */
    private List<Integer> itemAddIds;

    /**
     * 需要移除的id
     */
    private List<Integer> itemRmIds;

}
