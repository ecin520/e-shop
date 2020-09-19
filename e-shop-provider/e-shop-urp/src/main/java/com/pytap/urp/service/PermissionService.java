package com.pytap.urp.service;

import com.pytap.common.utils.Pager;
import com.pytap.generator.entity.SysPermission;
import com.pytap.generator.entity.SysPermissionGroup;
import com.pytap.urp.model.dto.PermissionGroupDTO;

import java.util.List;

/**
 * @author Ecin520
 * @date 2020/8/21 17:08
 */
public interface PermissionService {

    /**
     * 统计总数
     * @return Integer
     * */
    Integer countPermission();

    /**
     * 插入权限
     * @param permission 权限实体
     * @return Integer
     * */
    Integer insertPermission(SysPermission permission);

    /**
     * 删除权限
     * @param id 权限id
     * @return Integer
     * */
    Integer deletePermissionById(Long id);

    /**
     * 更新权限
     * @param permission 权限实体
     * @return Integer
     * */
    Integer updatePermissionById(SysPermission permission);

    /**
     * 主键获得权限
     * @param queryParam 查询参数
     * @return Permission 权限实体
     * */
    List<SysPermission> listPermissionsByQuery(SysPermission queryParam);

    /**
     * 获取所有权限
     * @param pageNum 第几页
     * @param pageSize 每页条目数量
     * @return Pager<SysPermission>
     * */
    Pager<SysPermission> listPermissions(Integer pageNum, Integer pageSize);

    /**
     * 批量刪除权限
     * @param permissionIds 权限id列表
     * @return Integer 返回結果
     * */
    Integer deletePermissions(List<Long> permissionIds);

    /**
     * 统计总数
     * @return Integer
     * */
    Integer countPermissionGroup();

    /**
     * 添加权限组
     * @param permissionGroup 权限组实体
     * @return Integer 返回结果
     * */
    Integer insertPermissionGroup(SysPermissionGroup permissionGroup);

    /**
     * 更新权限组
     * @param permissionGroup 权限组实体
     * @return Integer 返回结果
     * */
    Integer updatePermissionGroup(SysPermissionGroup permissionGroup);

    /**
     * 删除权限组
     * @param id 权限组主键
     * @return Integer 返回结果
     * */
    Integer deletePermissionGroupById(Long id);

    /**
     * 删除权限组
     * @param id 权限组主键
     * @return SysPermissionGroup 返回结果
     * */
    SysPermissionGroup getPermissionGroupById(Long id);

    /**
     * 删除权限组
     * @param pageNum 第几页
     * @param pageSize 每页条目数量
     * @return Pager<SysPermissionGroup> 返回结果
     * */
    Pager<SysPermissionGroup> listPermissionGroups(Integer pageNum, Integer pageSize);

    /**
     * 获取所有权限
     * @param groupId 权限组id
     * @return List<SysPermission>
     * */
    List<SysPermission> listPermissionsByGroupId(Long groupId);

}
