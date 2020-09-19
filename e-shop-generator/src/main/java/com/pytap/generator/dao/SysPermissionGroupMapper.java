package com.pytap.generator.dao;

import com.pytap.generator.entity.SysPermissionGroup;
import com.pytap.generator.entity.SysPermissionGroupExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SysPermissionGroupMapper {
    int countByExample(SysPermissionGroupExample example);

    int deleteByExample(SysPermissionGroupExample example);

    int deleteByPrimaryKey(Long id);

    int insert(SysPermissionGroup record);

    int insertSelective(SysPermissionGroup record);

    List<SysPermissionGroup> selectByExample(SysPermissionGroupExample example);

    SysPermissionGroup selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") SysPermissionGroup record, @Param("example") SysPermissionGroupExample example);

    int updateByExample(@Param("record") SysPermissionGroup record, @Param("example") SysPermissionGroupExample example);

    int updateByPrimaryKeySelective(SysPermissionGroup record);

    int updateByPrimaryKey(SysPermissionGroup record);
}