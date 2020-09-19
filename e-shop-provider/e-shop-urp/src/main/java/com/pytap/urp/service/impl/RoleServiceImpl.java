package com.pytap.urp.service.impl;

import com.github.pagehelper.PageHelper;
import com.pytap.common.utils.Pager;
import com.pytap.generator.dao.SysRoleMapper;
import com.pytap.generator.dao.SysRolePermissionMapper;
import com.pytap.generator.entity.*;
import com.pytap.urp.dao.RoleDao;
import com.pytap.urp.service.RoleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @author Ecin520
 * @date 2020/8/21 16:31
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Resource
    private SysRoleMapper roleMapper;

    @Resource
    private RoleDao roleDao;

    @Resource
    private SysRolePermissionMapper rolePermissionMapper;

    @Override
    public Integer countRole() {
        return roleMapper.countByExample(null);
    }

    @Override
    public Integer insertRole(SysRole role) {
        role.setCreateTime(new Date());
        return roleMapper.insert(role);
    }

    @Override
    public Integer deleteRoleById(Long id) {
        return roleMapper.deleteByPrimaryKey(id);
    }

    @Override
    public Integer updateRoleById(SysRole role) {
        role.setUpdateTime(new Date());
        return roleMapper.updateByPrimaryKey(role);
    }

    @Override
    public SysRole getRole(SysRole queryParam) {
        SysRoleExample roleExample = new SysRoleExample();
        SysRoleExample.Criteria criteria = roleExample.createCriteria();

        if (null != queryParam.getId()) {
            criteria.andIdEqualTo(queryParam.getId());
        }
        if (null != queryParam.getName()) {
            criteria.andNameEqualTo(queryParam.getName());
        }

        List<SysRole> list = roleMapper.selectByExample(roleExample);

        return list.size() != 0 ? list.get(0) : null;
    }

    @Override
    public Pager<SysRole> listRoles(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);

        Pager<SysRole> pager = new Pager<>();
        pager.setPageNum(pageNum);
        pager.setPageSize(pageSize);
        pager.setContent(roleMapper.selectByExample(null));
        pager.setTotal(roleMapper.countByExample(null));

        return pager;
    }

    @Transactional
    @Override
    public Integer deleteRoles(List<Long> roleIds) {

        for (Long id : roleIds) {

            // 删除用户角色
            SysRolePermissionExample example = new SysRolePermissionExample();
            SysRolePermissionExample.Criteria criteria = example.createCriteria();
            criteria.andRoleIdEqualTo(id);
            rolePermissionMapper.deleteByExample(example);

            // 删除用户
            roleMapper.deleteByPrimaryKey(id);

        }

        return 1;
    }

    @Override
    public List<SysPermission> listPermissionsByRoleId(Long roleId) {
        return roleDao.listPermissionByRoleId(roleId);
    }

    @Transactional
    @Override
    public Integer updateRolePermissions(Long roleId, List<Long> permissionIds) {

        // 先删除角色的所有权限关系
        SysRolePermissionExample example = new SysRolePermissionExample();
        SysRolePermissionExample.Criteria criteria = example.createCriteria();
        criteria.andRoleIdEqualTo(roleId);
        rolePermissionMapper.deleteByExample(example);

        if (permissionIds.isEmpty()) {
            return 1;
        }

        // 添加新的角色权限关系
        roleDao.insertRolePermissions(roleId, permissionIds);

        return 1;
    }

}
