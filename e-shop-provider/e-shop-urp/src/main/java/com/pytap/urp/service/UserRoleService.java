package com.pytap.urp.service;

import com.pytap.generator.entity.SysUserRole;

import java.util.List;

/**
 * @author Ecin520
 * @date 2020/8/22 1:07
 */
public interface UserRoleService {
    /**
     * 插入用户角色关系
     * @param userRole 用户角色关系实体
     * @return Integer
     * */
    Integer insertUserRole(SysUserRole userRole);

    /**
     * 删除用户角色关系
     * @param userId 用户id
     * @param roleId 角色id
     * @return Integer
     * */
    Integer deleteUserRoleByBothId(Long userId, Long roleId);

    /**
     * 删除用户角色关系
     * @param id 用户角色关系主键
     * @return Integer
     * */
    Integer deleteUserRoleById(Long id);

    /**
     * 更新用户角色关系
     * @param userRole 用户角色关系实体
     * @return Integer
     * */
    Integer updateUserRole(SysUserRole userRole);

    /**
     * 主键获得用户角色关系
     * @param id 主键
     * @return SysUserRole
     * */
    SysUserRole getUserRoleById(Long id);

    /**
     * 用户id获得用户角色关系
     * @param userId 用户id
     * @return SysUserRole
     * */
    List<SysUserRole> getUserRolesByUserId(Long userId);

    /**
     * 获取所有用户角色关系
     * @return List<SysUserRole>
     * */
    List<SysUserRole> listUserRoles();
}
