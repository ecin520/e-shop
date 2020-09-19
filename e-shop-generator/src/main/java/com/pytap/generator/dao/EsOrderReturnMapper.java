package com.pytap.generator.dao;

import com.pytap.generator.entity.EsOrderReturn;
import com.pytap.generator.entity.EsOrderReturnExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EsOrderReturnMapper {
    int countByExample(EsOrderReturnExample example);

    int deleteByExample(EsOrderReturnExample example);

    int deleteByPrimaryKey(Long id);

    int insert(EsOrderReturn record);

    int insertSelective(EsOrderReturn record);

    List<EsOrderReturn> selectByExample(EsOrderReturnExample example);

    EsOrderReturn selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") EsOrderReturn record, @Param("example") EsOrderReturnExample example);

    int updateByExample(@Param("record") EsOrderReturn record, @Param("example") EsOrderReturnExample example);

    int updateByPrimaryKeySelective(EsOrderReturn record);

    int updateByPrimaryKey(EsOrderReturn record);
}