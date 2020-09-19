package com.pytap.generator.dao;

import com.pytap.generator.entity.EsMember;
import com.pytap.generator.entity.EsMemberExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EsMemberMapper {
    int countByExample(EsMemberExample example);

    int deleteByExample(EsMemberExample example);

    int deleteByPrimaryKey(Long id);

    int insert(EsMember record);

    int insertSelective(EsMember record);

    List<EsMember> selectByExample(EsMemberExample example);

    EsMember selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") EsMember record, @Param("example") EsMemberExample example);

    int updateByExample(@Param("record") EsMember record, @Param("example") EsMemberExample example);

    int updateByPrimaryKeySelective(EsMember record);

    int updateByPrimaryKey(EsMember record);
}