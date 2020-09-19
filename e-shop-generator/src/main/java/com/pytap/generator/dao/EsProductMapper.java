package com.pytap.generator.dao;

import com.pytap.generator.entity.EsProduct;
import com.pytap.generator.entity.EsProductExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EsProductMapper {
    int countByExample(EsProductExample example);

    int deleteByExample(EsProductExample example);

    int deleteByPrimaryKey(Long id);

    int insert(EsProduct record);

    int insertSelective(EsProduct record);

    List<EsProduct> selectByExampleWithBLOBs(EsProductExample example);

    List<EsProduct> selectByExample(EsProductExample example);

    EsProduct selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") EsProduct record, @Param("example") EsProductExample example);

    int updateByExampleWithBLOBs(@Param("record") EsProduct record, @Param("example") EsProductExample example);

    int updateByExample(@Param("record") EsProduct record, @Param("example") EsProductExample example);

    int updateByPrimaryKeySelective(EsProduct record);

    int updateByPrimaryKeyWithBLOBs(EsProduct record);

    int updateByPrimaryKey(EsProduct record);
}