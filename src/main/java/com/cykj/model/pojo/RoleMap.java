package com.cykj.model.pojo;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * 角色集合
 * @author abin
 * @date 2024/8/19 19:49
 */

@Component
@Data
public class RoleMap {
    private Map<String, Role> map = new HashMap<>();
}
