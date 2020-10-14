package com.pytap.oauth2.service.impl;

import com.pytap.generator.dao.SysUserMapper;
import com.pytap.generator.entity.SysPermission;
import com.pytap.generator.entity.SysRole;
import com.pytap.generator.entity.SysUser;
import com.pytap.generator.entity.SysUserExample;
import com.pytap.oauth2.dao.AuthMapper;
import com.pytap.oauth2.model.dto.AuthDTO;
import com.pytap.oauth2.service.AuthService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Ecin520
 * @date 2020/8/22 17:06
 */
@Service
public class AuthServiceImpl implements AuthService {

    @Resource
    private AuthMapper authMapper;

    @Resource
    private SysUserMapper userMapper;

    @Override
    public List<SysPermission> listUserPermissions(Long id) {
        return authMapper.listUserPermissions(id);
    }

    @Override
    public List<SysRole> listUserRoles(Long id) {
        return authMapper.listUserRoles(id);
    }

    @Override
    public List<SysPermission> listRolePermissions(Long id) {
        return authMapper.listRolePermissions(id);
    }

    @Override
    public List<AuthDTO> listUserAllRolePermissions(Long id) {

        List<AuthDTO> auths = new ArrayList<>(16);

        List<SysRole> roles = authMapper.listUserRoles(id);

        List<SysPermission> permissions = authMapper.listUserPermissions(id);

        for (SysPermission permission : permissions) {
            AuthDTO authDTO = new AuthDTO();
            BeanUtils.copyProperties(permission, authDTO);
            auths.add(authDTO);
        }

        for (SysRole role : roles) {
            AuthDTO authDTO = new AuthDTO();
            BeanUtils.copyProperties(role, authDTO);
            auths.add(authDTO);
        }

        return auths;
    }

    @Override
    public SysUser getUserByUsername(String username) {
        return authMapper.getUserByUsername(username);
    }

    @Override
    public SysUser getUserByTelephone(String telephone) {
        return authMapper.getUserByTelephone(telephone);
    }

    @Override
    public Integer updateUserInfo(SysUser user) {
        return userMapper.updateByPrimaryKeySelective(user);
    }


}
