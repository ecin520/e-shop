package com.pytap.urp.service.impl;

import com.github.pagehelper.PageHelper;
import com.pytap.common.utils.Pager;
import com.pytap.generator.dao.SysPermissionGroupMapper;
import com.pytap.generator.dao.SysPermissionMapper;
import com.pytap.generator.dao.SysRolePermissionMapper;
import com.pytap.generator.entity.SysPermission;
import com.pytap.generator.entity.SysPermissionExample;
import com.pytap.generator.entity.SysPermissionGroup;
import com.pytap.generator.entity.SysRolePermissionExample;
import com.pytap.urp.service.PermissionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @author Ecin520
 * @date 2020/8/21 17:08
 */
@Service
public class PermissionServiceImpl implements PermissionService {

    @Resource
    private SysPermissionMapper permissionMapper;

    @Resource
    private SysPermissionGroupMapper permissionGroupMapper;

    @Resource
    private SysRolePermissionMapper rolePermissionMapper;

    @Override
    public Integer countPermission() {
        return permissionMapper.countByExample(null);
    }

    @Override
    public Integer insertPermission(SysPermission permission) {
        permission.setCreateTime(new Date());
        return permissionMapper.insert(permission);
    }

    @Override
    public Integer deletePermissionById(Long id) {
        return permissionMapper.deleteByPrimaryKey(id);
    }

    @Override
    public Integer updatePermissionById(SysPermission permission) {
        permission.setUpdateTime(new Date());
        return permissionMapper.updateByPrimaryKey(permission);
    }

    @Override
    public List<SysPermission> listPermissionsByQuery(SysPermission queryParam) {

        SysPermissionExample example = new SysPermissionExample();
        SysPermissionExample.Criteria criteria = example.createCriteria();

        if (null != queryParam.getId()) {
            criteria.andIdEqualTo(queryParam.getId());
        }

        if (null != queryParam.getName()) {
            criteria.andNameEqualTo(queryParam.getName());
        }

        if (null != queryParam.getGroupId()) {
            criteria.andGroupIdEqualTo(queryParam.getGroupId());
        }

        List<SysPermission> list = permissionMapper.selectByExample(example);

        return list.size() != 0 ? list : null;
    }

    @Override
    public Pager<SysPermission> listPermissions(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);

        Pager<SysPermission> pager = new Pager<>();
        pager.setPageNum(pageNum);
        pager.setPageSize(pageSize);
        pager.setContent(permissionMapper.selectByExample(null));
        pager.setTotal(permissionMapper.countByExample(null));

        return pager;
    }

    @Override
    public Integer insertPermissionGroup(SysPermissionGroup permissionGroup) {
        permissionGroup.setCreateTime(new Date());
        return permissionGroupMapper.insert(permissionGroup);
    }

    @Override
    public Integer updatePermissionGroup(SysPermissionGroup permissionGroup) {
        permissionGroup.setUpdateTime(new Date());
        return permissionGroupMapper.updateByPrimaryKeySelective(permissionGroup);
    }

    @Transactional
    @Override
    public Integer deletePermissionGroupById(Long id) {

        // 删除依赖权限组的权限
        SysPermissionExample example = new SysPermissionExample();
        SysPermissionExample.Criteria criteria = example.createCriteria();
        criteria.andGroupIdEqualTo(id);
        permissionMapper.deleteByExample(example);

        // 主键删除权限组
        return permissionGroupMapper.deleteByPrimaryKey(id);
    }

    @Override
    public SysPermissionGroup getPermissionGroupById(Long id) {
        return permissionGroupMapper.selectByPrimaryKey(id);
    }

    @Override
    public Pager<SysPermissionGroup> listPermissionGroups(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);

        Pager<SysPermissionGroup> pager = new Pager<>();
        pager.setPageNum(pageNum);
        pager.setPageSize(pageSize);
        pager.setContent(permissionGroupMapper.selectByExample(null));
        pager.setTotal(permissionGroupMapper.countByExample(null));

        return pager;
    }

    @Override
    public List<SysPermission> listPermissionsByGroupId(Long groupId) {
        SysPermissionExample example = new SysPermissionExample();
        SysPermissionExample.Criteria criteria = example.createCriteria();
        criteria.andGroupIdEqualTo(groupId);
        return permissionMapper.selectByExample(example);
    }

    @Transactional
    @Override
    public Integer deletePermissions(List<Long> permissionIds) {

        for (Long id : permissionIds) {

            // 删除权限对应角色关系
            SysRolePermissionExample example = new SysRolePermissionExample();
            SysRolePermissionExample.Criteria criteria = example.createCriteria();
            criteria.andPermissionIdEqualTo(id);
            rolePermissionMapper.deleteByExample(example);

            // 删除权限
            permissionMapper.deleteByPrimaryKey(id);

        }

        return 1;
    }

    @Override
    public Integer countPermissionGroup() {
        return permissionGroupMapper.countByExample(null);
    }
}
