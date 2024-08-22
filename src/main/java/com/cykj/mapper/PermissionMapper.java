package com.cykj.mapper;

import org.apache.ibatis.annotations.Param;

/**
 * @author abin
 * @date 2024/8/8 10:47
 */
public interface PermissionMapper {
    int addOnePermission(@Param("permMenuId") int permMenuId, @Param("permRoleId") int permRoleId);

    int deleteByPermMenuIdAndPermRoleId(@Param("permMenuId")Integer permMenuId,@Param("permRoleId")Integer permRoleId);

}