package com.pytap.generator.dao;

import com.pytap.generator.entity.EsSkuSpec;
import com.pytap.generator.entity.EsSkuSpecExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EsSkuSpecMapper {
    int countByExample(EsSkuSpecExample example);

    int deleteByExample(EsSkuSpecExample example);

    int deleteByPrimaryKey(Long id);

    int insert(EsSkuSpec record);

    int insertSelective(EsSkuSpec record);

    List<EsSkuSpec> selectByExample(EsSkuSpecExample example);

    EsSkuSpec selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") EsSkuSpec record, @Param("example") EsSkuSpecExample example);

    int updateByExample(@Param("record") EsSkuSpec record, @Param("example") EsSkuSpecExample example);

    int updateByPrimaryKeySelective(EsSkuSpec record);

    int updateByPrimaryKey(EsSkuSpec record);
}