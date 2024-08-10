package com.cykj.model.vo;

import lombok.Data;

/**
 * 通用的分页VO
 * @author abin
 * @date 2024/7/22 18:27
 */

@Data
public class PageVO<T> {
    /**
     * 当前页
     */
    private int pageNo;

    /**
     * 页显示数量
     */
    private int pageSize;

    /**
     * 查询参数
     */
    private T data;
}
