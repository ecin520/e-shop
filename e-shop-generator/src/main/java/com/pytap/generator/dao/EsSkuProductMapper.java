package com.pytap.generator.dao;

import com.pytap.generator.entity.EsSkuProduct;
import com.pytap.generator.entity.EsSkuProductExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EsSkuProductMapper {
    int countByExample(EsSkuProductExample example);

    int deleteByExample(EsSkuProductExample example);

    int deleteByPrimaryKey(Long id);

    int insert(EsSkuProduct record);

    int insertSelective(EsSkuProduct record);

    List<EsSkuProduct> selectByExample(EsSkuProductExample example);

    EsSkuProduct selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") EsSkuProduct record, @Param("example") EsSkuProductExample example);

    int updateByExample(@Param("record") EsSkuProduct record, @Param("example") EsSkuProductExample example);

    int updateByPrimaryKeySelective(EsSkuProduct record);

    int updateByPrimaryKey(EsSkuProduct record);
}