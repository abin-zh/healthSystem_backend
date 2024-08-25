package com.cykj.mapper;
import com.cykj.model.pojo.Role;
import org.apache.ibatis.annotations.Param;

import com.cykj.model.pojo.Menu;

import java.util.List;

/**
 * 菜单操作
 * @author abin
 * @date 2024/8/8 10:47
 */
public interface MenuMapper {

    List<Menu> findAllByRoleId(int roleId);

    List<Menu> findAllByMenuIsDeleted(@Param("menuIsDeleted")Integer menuIsDeleted);

    List<Menu> findAll(Menu menu);

    int addOneMenu(Menu menu);

    int updateById(@Param("updated")Menu updated,@Param("id")Integer id);

    List<Menu> findAllByParentIdAndMenuIsDeleted(@Param("parentId")Integer parentId,@Param("menuIsDeleted")Integer menuIsDeleted);





}