package com.pytap.oauth2.service;

import com.pytap.generator.entity.SysPermission;
import com.pytap.generator.entity.SysRole;
import com.pytap.generator.entity.SysUser;
import com.pytap.oauth2.model.dto.AuthDTO;

import java.util.List;

/**
 * @author Ecin520
 * @date 2020/8/22 12:52
 */
public interface AuthService {

    /**
     * 根据用户id获取权限
     * @param id 用户id
     * @return List<PermissionDO>
     * */
    List<SysPermission> listUserPermissions(Long id);


    /**
     * 根据用户id获取角色
     * @param id 用户id
     * @return List<RoleDO>
     * */
    List<SysRole> listUserRoles(Long id);


    /**
     * 根据角色id获取权限
     * @param id 角色id
     * @return List<Permission>
     * */
    List<SysPermission> listRolePermissions(Long id);

    /**
     * 根据用户id获取所有角色权限信息
     * @param id 用户id
     * @return List<AuthDTO>
     * */
    List<AuthDTO> listUserAllRolePermissions(Long id);

    /**
     * 通过用户名获取用户
     * @param username 用户名
     * @return SysUser 用户
     * */
    SysUser getUserByUsername(String username);

    /**
     * 更新用户信息
     * @param user 用户实体
     * @return Integer 返回结果
     * */
    Integer updateUserInfo(SysUser user);

}
