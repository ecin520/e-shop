package com.pytap.generator.dao;

import com.pytap.generator.entity.EsProductCategory;
import com.pytap.generator.entity.EsProductCategoryExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EsProductCategoryMapper {
    int countByExample(EsProductCategoryExample example);

    int deleteByExample(EsProductCategoryExample example);

    int deleteByPrimaryKey(Long id);

    int insert(EsProductCategory record);

    int insertSelective(EsProductCategory record);

    List<EsProductCategory> selectByExampleWithBLOBs(EsProductCategoryExample example);

    List<EsProductCategory> selectByExample(EsProductCategoryExample example);

    EsProductCategory selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") EsProductCategory record, @Param("example") EsProductCategoryExample example);

    int updateByExampleWithBLOBs(@Param("record") EsProductCategory record, @Param("example") EsProductCategoryExample example);

    int updateByExample(@Param("record") EsProductCategory record, @Param("example") EsProductCategoryExample example);

    int updateByPrimaryKeySelective(EsProductCategory record);

    int updateByPrimaryKeyWithBLOBs(EsProductCategory record);

    int updateByPrimaryKey(EsProductCategory record);
}