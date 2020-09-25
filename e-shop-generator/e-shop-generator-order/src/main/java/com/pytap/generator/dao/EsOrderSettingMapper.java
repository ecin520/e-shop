package com.pytap.generator.dao;

import com.pytap.generator.entity.EsOrderSetting;
import com.pytap.generator.entity.EsOrderSettingExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EsOrderSettingMapper {
    int countByExample(EsOrderSettingExample example);

    int deleteByExample(EsOrderSettingExample example);

    int deleteByPrimaryKey(Long id);

    int insert(EsOrderSetting record);

    int insertSelective(EsOrderSetting record);

    List<EsOrderSetting> selectByExample(EsOrderSettingExample example);

    EsOrderSetting selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") EsOrderSetting record, @Param("example") EsOrderSettingExample example);

    int updateByExample(@Param("record") EsOrderSetting record, @Param("example") EsOrderSettingExample example);

    int updateByPrimaryKeySelective(EsOrderSetting record);

    int updateByPrimaryKey(EsOrderSetting record);
}