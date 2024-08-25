package com.cykj.model.dto;

import com.cykj.model.pojo.Menu;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/**
 * 登录信息
 * @author abin
 * @date 2024/8/10 18:39
 */

@Data
@AllArgsConstructor
public class InfoDTO {

    private String username;

    private String avatar;

    private List<Menu> permissions;

}
