package com.pytap.generator.dao;

import com.pytap.generator.entity.EsProductSpec;
import com.pytap.generator.entity.EsProductSpecExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EsProductSpecMapper {
    int countByExample(EsProductSpecExample example);

    int deleteByExample(EsProductSpecExample example);

    int deleteByPrimaryKey(Long id);

    int insert(EsProductSpec record);

    int insertSelective(EsProductSpec record);

    List<EsProductSpec> selectByExample(EsProductSpecExample example);

    EsProductSpec selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") EsProductSpec record, @Param("example") EsProductSpecExample example);

    int updateByExample(@Param("record") EsProductSpec record, @Param("example") EsProductSpecExample example);

    int updateByPrimaryKeySelective(EsProductSpec record);

    int updateByPrimaryKey(EsProductSpec record);
}