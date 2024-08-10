package com.cykj.mapper;

import com.cykj.model.pojo.Menu;

import java.util.List;

/**
 * @author abin
 * @date 2024/8/8 10:47
 */
public interface MenuMapper {

    List<Menu> findAllByRoleId(int roleId);

}