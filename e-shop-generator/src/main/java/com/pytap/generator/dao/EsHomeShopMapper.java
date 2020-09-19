package com.pytap.generator.dao;

import com.pytap.generator.entity.EsHomeShop;
import com.pytap.generator.entity.EsHomeShopExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EsHomeShopMapper {
    int countByExample(EsHomeShopExample example);

    int deleteByExample(EsHomeShopExample example);

    int deleteByPrimaryKey(Long id);

    int insert(EsHomeShop record);

    int insertSelective(EsHomeShop record);

    List<EsHomeShop> selectByExample(EsHomeShopExample example);

    EsHomeShop selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") EsHomeShop record, @Param("example") EsHomeShopExample example);

    int updateByExample(@Param("record") EsHomeShop record, @Param("example") EsHomeShopExample example);

    int updateByPrimaryKeySelective(EsHomeShop record);

    int updateByPrimaryKey(EsHomeShop record);
}