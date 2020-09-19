package com.pytap.urp.service;

import com.pytap.generator.entity.SysRolePermission;

import java.util.List;

/**
 * @author Ecin520
 * @date 2020/8/22 1:23
 */
public interface RolePermissionService {
    /**
     * 插入角色权限关系
     * @param rolePermission 角色权限关系实体
     * @return Integer
     * */
    Integer insertRolePermission(SysRolePermission rolePermission);

    /**
     * 删除角色权限关系
     * @param roleId 角色id
     * @param permissionId 权限id
     * @return Integer
     * */
    Integer deleteRolePermissionByBothId(Long roleId, Long permissionId);

    /**
     * 删除角色权限关系
     * @param id 主键
     * @return Integer
     * */
    Integer deleteRolePermissionById(Long id);

    /**
     * 更新角色权限关系
     * @param rolePermission 角色权限关系实体
     * @return Integer
     * */
    Integer updateRolePermission(SysRolePermission rolePermission);

    /**
     * 主键获得角色权限关系
     * @param id 主键
     * @return rolePermission
     * */
    SysRolePermission getRolePermissionById(Long id);

    /**
     * 获取所有角色权限关系
     * @return List<RolePermission>
     * */
    List<SysRolePermission> listRolePermissions();
}
