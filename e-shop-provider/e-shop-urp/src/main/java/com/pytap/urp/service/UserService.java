package com.pytap.urp.service;

import com.pytap.common.utils.Pager;
import com.pytap.generator.entity.SysPermission;
import com.pytap.generator.entity.SysRole;
import com.pytap.generator.entity.SysUser;
import com.pytap.urp.model.vo.UserVO;

import java.util.List;


/**
 * @author Ecin520
 * @date 2020/8/18 11:01
 */
public interface UserService {

    /**
     * 统计总数
     * @return Integer
     * */
    Integer countUser();

    /**
     * 通过主键获取用户
     * @param id 主键
     * @return SysUser 用户
     * */
    SysUser getUserById(Long id);

    /**
     * 通过主键获取用户VO
     * @param id 主键
     * @return SysUser 用户
     * */
    UserVO getUserVOById(Long id);

    /**
     * 通过用户名获取用户
     * @param username 用户名
     * @return SysUser 用户
     * */
    SysUser getUserByUsername(String username);

    /**
     * 通过用户名获取用户VO
     * @param username 用户名
     * @return SysUser 用户
     * */
    UserVO getUserVOByUsername(String username);

    /**
     * 添加用户
     * @param user 用户实体
     * @return Integer 返回结果
     * */
    Integer insertUser(SysUser user);

    /**
     * 用户注册
     * @param user 用户实体
     * @return Integer 返回结果
     * */
    Integer register(SysUser user);

    /**
     * 更新用户
     * @param user 用户实体
     * @return Integer 返回结果
     * */
    Integer updateUser(SysUser user);

    /**
     * 获取所有用户
     * @param pageNum 第几页
     * @param pageSize 每页条目数量
     * @return Pager<User>
     * */
    Pager<UserVO> listUsers(Integer pageNum, Integer pageSize);

    /**
     * 通过用户名获取用户的角色
     * @param username 用户名
     * @return List<SysRole> 返回结果
     * */
    List<SysRole> listUserRoles(String username);

    /**
     * 通过用户名获取用户的权限
     * @param username 用户名
     * @return List<SysPermission> 返回结果
     * */
    List<SysPermission> listUserPermissions(String username);

    /**
     * 通过用户名获取用户的权限
     * @param userId 用户id
     * @param roleIds 角色id列表
     * @return Integer 返回结果
     * */
    Integer updateUserRoles(Long userId, List<Long> roleIds);

    /**
     * 通过用户名获取用户的权限
     * @param userIds 用户id列表
     * @return Integer 返回结果
     * */
    Integer deleteUsers(List<Long> userIds);

    /**
     * 用户名或者昵称模糊搜索用户
     * @param pageNum 第几页
     * @param pageSize 页面大小
     * @param keyword 搜索关键字
     * @return Pager<UserVO>
     * */
    Pager<UserVO> listSearchUsers(Integer pageNum, Integer pageSize, String keyword);

}
