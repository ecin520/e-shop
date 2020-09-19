package com.pytap.generator.dao;

import com.pytap.generator.entity.EsProductSpecDetail;
import com.pytap.generator.entity.EsProductSpecDetailExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EsProductSpecDetailMapper {
    int countByExample(EsProductSpecDetailExample example);

    int deleteByExample(EsProductSpecDetailExample example);

    int deleteByPrimaryKey(Long id);

    int insert(EsProductSpecDetail record);

    int insertSelective(EsProductSpecDetail record);

    List<EsProductSpecDetail> selectByExample(EsProductSpecDetailExample example);

    EsProductSpecDetail selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") EsProductSpecDetail record, @Param("example") EsProductSpecDetailExample example);

    int updateByExample(@Param("record") EsProductSpecDetail record, @Param("example") EsProductSpecDetailExample example);

    int updateByPrimaryKeySelective(EsProductSpecDetail record);

    int updateByPrimaryKey(EsProductSpecDetail record);
}