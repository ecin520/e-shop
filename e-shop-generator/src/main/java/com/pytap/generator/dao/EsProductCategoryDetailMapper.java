package com.pytap.generator.dao;

import com.pytap.generator.entity.EsProductCategoryDetail;
import com.pytap.generator.entity.EsProductCategoryDetailExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EsProductCategoryDetailMapper {
    int countByExample(EsProductCategoryDetailExample example);

    int deleteByExample(EsProductCategoryDetailExample example);

    int deleteByPrimaryKey(Long id);

    int insert(EsProductCategoryDetail record);

    int insertSelective(EsProductCategoryDetail record);

    List<EsProductCategoryDetail> selectByExampleWithBLOBs(EsProductCategoryDetailExample example);

    List<EsProductCategoryDetail> selectByExample(EsProductCategoryDetailExample example);

    EsProductCategoryDetail selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") EsProductCategoryDetail record, @Param("example") EsProductCategoryDetailExample example);

    int updateByExampleWithBLOBs(@Param("record") EsProductCategoryDetail record, @Param("example") EsProductCategoryDetailExample example);

    int updateByExample(@Param("record") EsProductCategoryDetail record, @Param("example") EsProductCategoryDetailExample example);

    int updateByPrimaryKeySelective(EsProductCategoryDetail record);

    int updateByPrimaryKeyWithBLOBs(EsProductCategoryDetail record);

    int updateByPrimaryKey(EsProductCategoryDetail record);
}