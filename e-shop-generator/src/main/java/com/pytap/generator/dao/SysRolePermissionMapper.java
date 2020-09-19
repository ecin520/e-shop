package com.pytap.generator.dao;

import com.pytap.generator.entity.SysRolePermission;
import com.pytap.generator.entity.SysRolePermissionExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SysRolePermissionMapper {
    int countByExample(SysRolePermissionExample example);

    int deleteByExample(SysRolePermissionExample example);

    int deleteByPrimaryKey(Long id);

    int insert(SysRolePermission record);

    int insertSelective(SysRolePermission record);

    List<SysRolePermission> selectByExample(SysRolePermissionExample example);

    SysRolePermission selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") SysRolePermission record, @Param("example") SysRolePermissionExample example);

    int updateByExample(@Param("record") SysRolePermission record, @Param("example") SysRolePermissionExample example);

    int updateByPrimaryKeySelective(SysRolePermission record);

    int updateByPrimaryKey(SysRolePermission record);
}