package com.pytap.urp.dao;

import com.pytap.generator.entity.SysPermission;
import com.pytap.generator.entity.SysUserRole;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Ecin520
 * @date 2020/9/7 11:09
 */
@Mapper
public interface RoleDao {

    /**
     * 批量插入用户角色关系
     * @param roleId 角色id
     * @param permissionIds 对应权限id
     * @return int 返回结果
     * */
    int insertRolePermissions(@Param("roleId") Long roleId,@Param("list") List<Long> permissionIds);

    /**
     * 角色id获取对应权限
     * @param roleId 角色id
     * @return List<SysPermission>
     * */
    List<SysPermission> listPermissionByRoleId(Long roleId);

}
