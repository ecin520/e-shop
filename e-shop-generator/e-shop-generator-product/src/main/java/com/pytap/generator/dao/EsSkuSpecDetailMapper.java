package com.pytap.generator.dao;

import com.pytap.generator.entity.EsSkuSpecDetail;
import com.pytap.generator.entity.EsSkuSpecDetailExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EsSkuSpecDetailMapper {
    int countByExample(EsSkuSpecDetailExample example);

    int deleteByExample(EsSkuSpecDetailExample example);

    int deleteByPrimaryKey(Long id);

    int insert(EsSkuSpecDetail record);

    int insertSelective(EsSkuSpecDetail record);

    List<EsSkuSpecDetail> selectByExample(EsSkuSpecDetailExample example);

    EsSkuSpecDetail selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") EsSkuSpecDetail record, @Param("example") EsSkuSpecDetailExample example);

    int updateByExample(@Param("record") EsSkuSpecDetail record, @Param("example") EsSkuSpecDetailExample example);

    int updateByPrimaryKeySelective(EsSkuSpecDetail record);

    int updateByPrimaryKey(EsSkuSpecDetail record);
}