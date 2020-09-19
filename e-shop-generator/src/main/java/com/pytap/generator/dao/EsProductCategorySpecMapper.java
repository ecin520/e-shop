package com.pytap.generator.dao;

import com.pytap.generator.entity.EsProductCategorySpec;
import com.pytap.generator.entity.EsProductCategorySpecExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EsProductCategorySpecMapper {
    int countByExample(EsProductCategorySpecExample example);

    int deleteByExample(EsProductCategorySpecExample example);

    int deleteByPrimaryKey(Long id);

    int insert(EsProductCategorySpec record);

    int insertSelective(EsProductCategorySpec record);

    List<EsProductCategorySpec> selectByExample(EsProductCategorySpecExample example);

    EsProductCategorySpec selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") EsProductCategorySpec record, @Param("example") EsProductCategorySpecExample example);

    int updateByExample(@Param("record") EsProductCategorySpec record, @Param("example") EsProductCategorySpecExample example);

    int updateByPrimaryKeySelective(EsProductCategorySpec record);

    int updateByPrimaryKey(EsProductCategorySpec record);
}