package com.pytap.oauth2.dao;

import com.pytap.generator.entity.SysPermission;
import com.pytap.generator.entity.SysRole;
import com.pytap.generator.entity.SysUser;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author Ecin520
 * @date 2020/8/18 10:32
 */
@Mapper
public interface AuthMapper {

    /**
     * 根据用户id获取权限
     * @param id 用户id
     * @return List<Permission>
     * */
    List<SysPermission> listUserPermissions(Long id);

    /**
     * 根据用户id获取角色
     * @param id 用户id
     * @return List<Role>
     * */
    List<SysRole> listUserRoles(Long id);

    /**
     * 根据角色id获取权限
     * @param id 角色id
     * @return List<Permission>
     * */
    List<SysPermission> listRolePermissions(Long id);

    /**
     * 通过用户名获取用户
     * @param username 用户名
     * @return SysUser 用户
     * */
    SysUser getUserByUsername(String username);

    /**
     * 通过电话号码获取用户
     * @param telephone 手机号码
     * @return SysUser 用户
     * */
    SysUser getUserByTelephone(String telephone);

}
