package com.pytap.generator.dao;

import com.pytap.generator.entity.EmProductVerify;
import com.pytap.generator.entity.EmProductVerifyExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EmProductVerifyMapper {
    int countByExample(EmProductVerifyExample example);

    int deleteByExample(EmProductVerifyExample example);

    int deleteByPrimaryKey(Long id);

    int insert(EmProductVerify record);

    int insertSelective(EmProductVerify record);

    List<EmProductVerify> selectByExample(EmProductVerifyExample example);

    EmProductVerify selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") EmProductVerify record, @Param("example") EmProductVerifyExample example);

    int updateByExample(@Param("record") EmProductVerify record, @Param("example") EmProductVerifyExample example);

    int updateByPrimaryKeySelective(EmProductVerify record);

    int updateByPrimaryKey(EmProductVerify record);
}