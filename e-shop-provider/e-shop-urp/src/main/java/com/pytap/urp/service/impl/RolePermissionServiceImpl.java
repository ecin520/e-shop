package com.pytap.urp.service.impl;

import com.pytap.generator.dao.SysRolePermissionMapper;
import com.pytap.generator.entity.SysRolePermission;
import com.pytap.generator.entity.SysRolePermissionExample;
import com.pytap.urp.service.RolePermissionService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @author Ecin520
 * @date 2020/8/22 1:02
 */
@Service
public class RolePermissionServiceImpl implements RolePermissionService {

    @Resource
    private SysRolePermissionMapper sysRolePermissionMapper;

    @Override
    public Integer insertRolePermission(SysRolePermission rolePermission) {
        rolePermission.setCreateTime(new Date());
        return sysRolePermissionMapper.insert(rolePermission);
    }

    @Override
    public Integer deleteRolePermissionByBothId(Long roleId, Long permissionId) {
        SysRolePermissionExample example = new SysRolePermissionExample();
        SysRolePermissionExample.Criteria criteria = example.createCriteria();
        criteria.andRoleIdEqualTo(roleId).andPermissionIdEqualTo(permissionId);
        return sysRolePermissionMapper.deleteByExample(example);
    }

    @Override
    public Integer deleteRolePermissionById(Long id) {
        return sysRolePermissionMapper.deleteByPrimaryKey(id);
    }

    @Override
    public Integer updateRolePermission(SysRolePermission rolePermission) {
        rolePermission.setCreateTime(new Date());
        return sysRolePermissionMapper.updateByPrimaryKey(rolePermission);
    }

    @Override
    public SysRolePermission getRolePermissionById(Long id) {
        return sysRolePermissionMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<SysRolePermission> listRolePermissions() {
        return sysRolePermissionMapper.selectByExample(null);
    }
}
