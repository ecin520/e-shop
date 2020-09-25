package com.pytap.generator.dao;

import com.pytap.generator.entity.EsOrder;
import com.pytap.generator.entity.EsOrderExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EsOrderMapper {
    int countByExample(EsOrderExample example);

    int deleteByExample(EsOrderExample example);

    int deleteByPrimaryKey(Long id);

    int insert(EsOrder record);

    int insertSelective(EsOrder record);

    List<EsOrder> selectByExample(EsOrderExample example);

    EsOrder selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") EsOrder record, @Param("example") EsOrderExample example);

    int updateByExample(@Param("record") EsOrder record, @Param("example") EsOrderExample example);

    int updateByPrimaryKeySelective(EsOrder record);

    int updateByPrimaryKey(EsOrder record);
}