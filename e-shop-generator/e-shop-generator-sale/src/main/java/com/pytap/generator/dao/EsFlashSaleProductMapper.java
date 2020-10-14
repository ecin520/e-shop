package com.pytap.generator.dao;

import com.pytap.generator.entity.EsFlashSaleProduct;
import com.pytap.generator.entity.EsFlashSaleProductExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EsFlashSaleProductMapper {
    int countByExample(EsFlashSaleProductExample example);

    int deleteByExample(EsFlashSaleProductExample example);

    int deleteByPrimaryKey(Long id);

    int insert(EsFlashSaleProduct record);

    int insertSelective(EsFlashSaleProduct record);

    List<EsFlashSaleProduct> selectByExample(EsFlashSaleProductExample example);

    EsFlashSaleProduct selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") EsFlashSaleProduct record, @Param("example") EsFlashSaleProductExample example);

    int updateByExample(@Param("record") EsFlashSaleProduct record, @Param("example") EsFlashSaleProductExample example);

    int updateByPrimaryKeySelective(EsFlashSaleProduct record);

    int updateByPrimaryKey(EsFlashSaleProduct record);
}