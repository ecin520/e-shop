package com.pytap.oauth2.model.bo;

import com.pytap.generator.entity.SysUser;
import com.pytap.oauth2.model.dto.AuthDTO;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * 用户详情（角色--需带ROLE_前缀 权限）
 * @author Ecin520
 * @date 2020/8/18 0:07
 */
public class UserDetailsImpl implements UserDetails {

    private static final long serialVersionUID = 9061159403035701438L;

    private final SysUser sysUser;

    private final List<AuthDTO> auths;



    public UserDetailsImpl(SysUser sysUser, List<AuthDTO> auths) {
        this.sysUser = sysUser;
        this.auths = auths;
    }

    public SysUser getSysUser() {
        return sysUser;
    }

    /**
     * 权限分配，如果权限改变需要刷新token
     * */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> list = new ArrayList<>(16);
        for (AuthDTO auth : auths) {
            list.add(new SimpleGrantedAuthority(auth.getName()));
        }
        return list;
    }

    @Override
    public String getPassword() {
        return sysUser.getPassword();
    }

    @Override
    public String getUsername() {
        return sysUser.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return sysUser.getStatus().equals(1);
    }

}
