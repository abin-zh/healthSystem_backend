package com.cykj.mapper;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.cykj.model.pojo.Role;

/**
 * 角色操作
 * @author abin
 * @date 2024/8/8 10:47
 */
public interface RoleMapper {
    List<Role> findAll();

    List<Role> findAllByRoleIsDelete(@Param("roleIsDelete")Integer roleIsDelete);

    List<Role> findAllWithPage(Role role);

    int addOneRole(Role role);

    int updateByRoleId(@Param("updated")Role updated,@Param("roleId")Integer roleId);


}