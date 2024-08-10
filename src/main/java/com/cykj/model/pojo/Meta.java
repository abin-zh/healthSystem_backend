package com.cykj.model.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 菜单元信息
 * @author abin
 * @date 2024/8/10 21:04
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Meta {

    /**
     * 菜单标题
     */
    private String title;

    /**
     * 菜单图标
     */
    private String icon;

}
