package com.pytap.generator.dao;

import com.pytap.generator.entity.EsProductAd;
import com.pytap.generator.entity.EsProductAdExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EsProductAdMapper {
    int countByExample(EsProductAdExample example);

    int deleteByExample(EsProductAdExample example);

    int deleteByPrimaryKey(Long id);

    int insert(EsProductAd record);

    int insertSelective(EsProductAd record);

    List<EsProductAd> selectByExample(EsProductAdExample example);

    EsProductAd selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") EsProductAd record, @Param("example") EsProductAdExample example);

    int updateByExample(@Param("record") EsProductAd record, @Param("example") EsProductAdExample example);

    int updateByPrimaryKeySelective(EsProductAd record);

    int updateByPrimaryKey(EsProductAd record);
}