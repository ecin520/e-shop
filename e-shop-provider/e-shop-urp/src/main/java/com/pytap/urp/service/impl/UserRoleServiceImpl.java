package com.pytap.urp.service.impl;

import com.pytap.generator.dao.SysUserRoleMapper;
import com.pytap.generator.entity.SysUserRole;
import com.pytap.generator.entity.SysUserRoleExample;
import com.pytap.urp.service.UserRoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @author Ecin520
 * @date 2020/8/22 1:10
 */
@Service
public class UserRoleServiceImpl implements UserRoleService {

    @Resource
    private SysUserRoleMapper sysUserRoleMapper;

    @Override
    public Integer insertUserRole(SysUserRole userRole) {
        userRole.setCreateTime(new Date());
        return sysUserRoleMapper.insert(userRole);
    }

    @Override
    public Integer deleteUserRoleByBothId(Long userId, Long roleId) {
        SysUserRoleExample example = new SysUserRoleExample();
        SysUserRoleExample.Criteria criteria = example.createCriteria();
        criteria.andUserIdEqualTo(userId).andRoleIdEqualTo(roleId);
        return sysUserRoleMapper.deleteByExample(example);
    }

    @Override
    public Integer deleteUserRoleById(Long id) {
        return sysUserRoleMapper.deleteByPrimaryKey(id);
    }

    @Override
    public Integer updateUserRole(SysUserRole userRole) {
        userRole.setUpdateTime(new Date());
        return sysUserRoleMapper.updateByPrimaryKey(userRole);
    }

    @Override
    public SysUserRole getUserRoleById(Long id) {
        return sysUserRoleMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<SysUserRole> getUserRolesByUserId(Long userId) {
        SysUserRoleExample example = new SysUserRoleExample();
        SysUserRoleExample.Criteria criteria = example.createCriteria();
        criteria.andUserIdEqualTo(userId);
        return sysUserRoleMapper.selectByExample(example);
    }

    @Override
    public List<SysUserRole> listUserRoles() {
        return sysUserRoleMapper.selectByExample(null);
    }
}
