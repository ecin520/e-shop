package com.pytap.generator.dao;

import com.pytap.generator.entity.EsShop;
import com.pytap.generator.entity.EsShopExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EsShopMapper {
    int countByExample(EsShopExample example);

    int deleteByExample(EsShopExample example);

    int deleteByPrimaryKey(Long id);

    int insert(EsShop record);

    int insertSelective(EsShop record);

    List<EsShop> selectByExampleWithBLOBs(EsShopExample example);

    List<EsShop> selectByExample(EsShopExample example);

    EsShop selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") EsShop record, @Param("example") EsShopExample example);

    int updateByExampleWithBLOBs(@Param("record") EsShop record, @Param("example") EsShopExample example);

    int updateByExample(@Param("record") EsShop record, @Param("example") EsShopExample example);

    int updateByPrimaryKeySelective(EsShop record);

    int updateByPrimaryKeyWithBLOBs(EsShop record);

    int updateByPrimaryKey(EsShop record);
}