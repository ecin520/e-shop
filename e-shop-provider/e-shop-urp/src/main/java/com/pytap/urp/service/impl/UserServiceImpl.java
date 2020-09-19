package com.pytap.urp.service.impl;

import com.github.pagehelper.PageHelper;
import com.pytap.common.utils.Pager;
import com.pytap.common.utils.UUIDUtil;
import com.pytap.generator.dao.SysUserMapper;
import com.pytap.generator.dao.SysUserRoleMapper;
import com.pytap.generator.entity.*;
import com.pytap.urp.dao.UserDao;
import com.pytap.urp.model.dto.UserDTO;
import com.pytap.urp.service.MemberService;
import com.pytap.urp.service.MerchantService;
import com.pytap.urp.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Ecin520
 * @date 2020/8/18 11:01
 */
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private PasswordEncoder passwordEncoder;

    @Resource
    private SysUserMapper userMapper;

    @Resource
    private SysUserRoleMapper userRoleMapper;

    @Resource
    private UserDao userDao;

    @Resource
    private MemberService memberService;

    @Resource
    private MerchantService merchantService;

    @Override
    public Integer countUser() {
        return userMapper.countByExample(null);
    }

    @Override
    public SysUser getUserById(Long id) {
        return userMapper.selectByPrimaryKey(id);
    }

    @Override
    public UserDTO getUserDTOById(Long id) {
        UserDTO sysUserDTO = new UserDTO();
        SysUser sysUser = userMapper.selectByPrimaryKey(id);
        if (null != sysUser) {
            BeanUtils.copyProperties(sysUser, sysUserDTO);

            EsMember member = new EsMember();
            member.setUserId(id);
            sysUserDTO.setMember(memberService.getMember(member));

            EsMerchant merchant = new EsMerchant();
            merchant.setUserId(id);
            sysUserDTO.setMerchant(merchantService.getMerchant(merchant));

        } else {
            return null;
        }
        return sysUserDTO;
    }

    @Override
    public SysUser getUserByUsername(String username) {
        SysUserExample example = new SysUserExample();
        SysUserExample.Criteria criteria = example.createCriteria();
        criteria.andUsernameEqualTo(username);
        List<SysUser> list = userMapper.selectByExample(example);
        return list.size() != 0 ? list.get(0) : null;
    }

    @Override
    public UserDTO getUserDTOByUsername(String username) {
        UserDTO sysUserDTO = new UserDTO();

        SysUserExample example = new SysUserExample();
        SysUserExample.Criteria criteria = example.createCriteria();
        criteria.andUsernameEqualTo(username);

        List<SysUser> list = userMapper.selectByExample(example);

        if (list.size() != 0) {
            BeanUtils.copyProperties(list.get(0), sysUserDTO);

            EsMember member = new EsMember();
            member.setUsername(username);
            sysUserDTO.setMember(memberService.getMember(member));

            EsMerchant merchant = new EsMerchant();
            merchant.setUsername(username);
            sysUserDTO.setMerchant(merchantService.getMerchant(merchant));

        } else {
            return null;
        }
        return sysUserDTO;
    }


    @Override
    public Integer insertUser(SysUser user) {
        user.setStatus(1);
        user.setCreateTime(new Date());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userMapper.insert(user);
    }

    @Override
    @Transactional
    public Integer register(SysUser user) {
        try {

            // 将用户状态设为正常
            user.setStatus(1);
            user.setCreateTime(new Date());
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userMapper.insert(user);

            // 默认添加为会员
            EsMember member = new EsMember();
            member.setUserId(user.getId());
            member.setMemberNumber(UUIDUtil.uuidNumberString());
            member.setUsername(user.getUsername());
            member.setCreateTime(new Date());
            memberService.insertMember(member);

        } catch (Exception e) {
            return 0;
        }
        return 1;
    }

    @Override
    public Integer updateUser(SysUser user) {
        user.setUpdateTime(new Date());
        return userMapper.updateByPrimaryKeySelective(user);
    }

    @Override
    public Pager<UserDTO> listUsers(Integer pageNum, Integer pageSize) {

        PageHelper.startPage(pageNum, pageSize);

        List<SysUser> users = userMapper.selectByExample(null);

        Pager<UserDTO> pager = new Pager<>();
        pager.setContent(userListToDto(users));
        pager.setPageNum(pageNum);
        pager.setPageSize(pageSize);
        pager.setTotal(userMapper.countByExample(null));

        return pager;
    }

    @Transactional
    @Override
    public List<SysRole> listUserRoles(String username) {
        SysUser user = getUserByUsername(username);
        return userDao.listUserRoles(user.getId());
    }

    @Override
    public List<SysPermission> listUserPermissions(String username) {
        return null;
    }

    @Transactional
    @Override
    public Integer updateUserRoles(Long userId, List<Long> roleIds) {

        // 删除用户的角色
        SysUserRoleExample example = new SysUserRoleExample();
        SysUserRoleExample.Criteria criteria = example.createCriteria();
        criteria.andUserIdEqualTo(userId);

        userRoleMapper.deleteByExample(example);

        List<SysUserRole> list = new ArrayList<>(16);

        //插入用户新角色
        for (Long roleId : roleIds) {
            SysUserRole userRole = new SysUserRole();
            userRole.setUserId(userId);
            userRole.setRoleId(roleId);
            list.add(userRole);
        }

        if (0 == list.size()) {
            return 0;
        }

        int result = userDao.insertUserRoles(list);
        System.out.println(result);
        return result;
    }

    @Transactional
    @Override
    public Integer deleteUsers(List<Long> userIds) {

        for (Long id : userIds) {

            // 删除会员
            memberService.deleteMemberByUserId(id);

            // 删除用户角色
            SysUserRoleExample example = new SysUserRoleExample();
            SysUserRoleExample.Criteria criteria = example.createCriteria();
            criteria.andUserIdEqualTo(id);
            userRoleMapper.deleteByExample(example);

            // 删除用户
            userMapper.deleteByPrimaryKey(id);

        }

        return 1;
    }

    @Override
    public Pager<UserDTO> listSearchUsers(Integer pageNum, Integer pageSize, String keyword) {

        SysUserExample example = new SysUserExample();
        SysUserExample.Criteria criteria = example.createCriteria();

        if (!StringUtils.isEmpty(keyword)) {
            criteria.andUsernameLike("%" + keyword + "%");
            example.or(example.createCriteria().andNicknameLike("%" + keyword + "%"));
        }

        PageHelper.startPage(pageNum, pageSize);

        List<SysUser> users = userMapper.selectByExample(example);

        Pager<UserDTO> pager = new Pager<>();
        pager.setPageNum(pageNum);
        pager.setPageSize(pageSize);
        pager.setContent(userListToDto(users));
        pager.setTotal(userMapper.countByExample(example));

        return pager;
    }

    private List<UserDTO> userListToDto(List<SysUser> users) {
        List<UserDTO> result = new ArrayList<>(16);
        for (SysUser user : users) {
            UserDTO dto = new UserDTO();
            BeanUtils.copyProperties(user, dto);
            result.add(dto);
        }
        return result;
    }

}
