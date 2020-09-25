package com.pytap.generator.dao;

import com.pytap.generator.entity.EsCartProduct;
import com.pytap.generator.entity.EsCartProductExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EsCartProductMapper {
    int countByExample(EsCartProductExample example);

    int deleteByExample(EsCartProductExample example);

    int deleteByPrimaryKey(Long id);

    int insert(EsCartProduct record);

    int insertSelective(EsCartProduct record);

    List<EsCartProduct> selectByExample(EsCartProductExample example);

    EsCartProduct selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") EsCartProduct record, @Param("example") EsCartProductExample example);

    int updateByExample(@Param("record") EsCartProduct record, @Param("example") EsCartProductExample example);

    int updateByPrimaryKeySelective(EsCartProduct record);

    int updateByPrimaryKey(EsCartProduct record);
}