package com.pytap.urp.dao;

import com.pytap.generator.entity.SysRole;
import com.pytap.generator.entity.SysUserRole;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Ecin520
 * @date 2020/9/2 15:06
 */
@Mapper
public interface UserDao {

    /**
     * 批量插入用户角色关系
     * @param list 用户角色列表
     * @return int 返回结果
     * */
    int insertUserRoles(@Param("list")List<SysUserRole> list);

    /**
     * 通过用户获取角色
     * @param userId 用户id
     * @return List<SysRole> 返回结果
     * */
    List<SysRole> listUserRoles(@Param("userId") Long userId);

}
