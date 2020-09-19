package com.pytap.urp.service;

import com.pytap.common.utils.Pager;
import com.pytap.generator.entity.SysPermission;
import com.pytap.generator.entity.SysRole;

import java.util.List;

/**
 * @author Ecin520
 * @date 2020/8/21 16:30
 */
public interface RoleService {
    /**
     * 统计总数
     * @return Integer
     * */
    Integer countRole();

    /**
     * 插入角色
     * @param role 角色实体
     * @return Integer
     * */
    Integer insertRole(SysRole role);

    /**
     * 删除角色
     * @param id 角色id
     * @return Integer
     * */
    Integer deleteRoleById(Long id);

    /**
     * 更新角色
     * @param role 角色实体
     * @return Integer
     * */
    Integer updateRoleById(SysRole role);

    /**
     * 获得角色
     * @param queryParam 查询参数
     * @return SysRole 角色实体
     * */
    SysRole getRole(SysRole queryParam);

    /**
     * 获取所有角色
     * @param pageNum 第几页
     * @param pageSize 每页条目数量
     * @return Pager<SysRole>
     * */
    Pager<SysRole> listRoles(Integer pageNum, Integer pageSize);

    /**
     * 批量刪除角色
     * @param roleIds 角色id列表
     * @return Integer 返回結果
     * */
    Integer deleteRoles(List<Long> roleIds);

    /**
     * 角色id获取对应权限
     * @param roleId 角色id
     * @return List<SysPermission>
     * */
    List<SysPermission> listPermissionsByRoleId(Long roleId);

    /**
     * 更新角色的权限
     * @param roleId 角色id
     * @param permissionIds 权限id
     * @return Integer
     * */
    Integer updateRolePermissions(Long roleId, List<Long> permissionIds);
}
