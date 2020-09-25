package com.pytap.generator.dao;

import com.pytap.generator.entity.EsOrderProduct;
import com.pytap.generator.entity.EsOrderProductExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EsOrderProductMapper {
    int countByExample(EsOrderProductExample example);

    int deleteByExample(EsOrderProductExample example);

    int deleteByPrimaryKey(Long id);

    int insert(EsOrderProduct record);

    int insertSelective(EsOrderProduct record);

    List<EsOrderProduct> selectByExample(EsOrderProductExample example);

    EsOrderProduct selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") EsOrderProduct record, @Param("example") EsOrderProductExample example);

    int updateByExample(@Param("record") EsOrderProduct record, @Param("example") EsOrderProductExample example);

    int updateByPrimaryKeySelective(EsOrderProduct record);

    int updateByPrimaryKey(EsOrderProduct record);
}