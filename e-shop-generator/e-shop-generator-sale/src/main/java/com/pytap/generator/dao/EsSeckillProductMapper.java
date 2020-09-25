package com.pytap.generator.dao;

import com.pytap.generator.entity.EsSeckillProduct;
import com.pytap.generator.entity.EsSeckillProductExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EsSeckillProductMapper {
    int countByExample(EsSeckillProductExample example);

    int deleteByExample(EsSeckillProductExample example);

    int deleteByPrimaryKey(Long id);

    int insert(EsSeckillProduct record);

    int insertSelective(EsSeckillProduct record);

    List<EsSeckillProduct> selectByExample(EsSeckillProductExample example);

    EsSeckillProduct selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") EsSeckillProduct record, @Param("example") EsSeckillProductExample example);

    int updateByExample(@Param("record") EsSeckillProduct record, @Param("example") EsSeckillProductExample example);

    int updateByPrimaryKeySelective(EsSeckillProduct record);

    int updateByPrimaryKey(EsSeckillProduct record);
}