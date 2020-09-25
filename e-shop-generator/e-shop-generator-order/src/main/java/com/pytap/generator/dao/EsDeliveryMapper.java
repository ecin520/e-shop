package com.pytap.generator.dao;

import com.pytap.generator.entity.EsDelivery;
import com.pytap.generator.entity.EsDeliveryExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EsDeliveryMapper {
    int countByExample(EsDeliveryExample example);

    int deleteByExample(EsDeliveryExample example);

    int deleteByPrimaryKey(Long id);

    int insert(EsDelivery record);

    int insertSelective(EsDelivery record);

    List<EsDelivery> selectByExample(EsDeliveryExample example);

    EsDelivery selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") EsDelivery record, @Param("example") EsDeliveryExample example);

    int updateByExample(@Param("record") EsDelivery record, @Param("example") EsDeliveryExample example);

    int updateByPrimaryKeySelective(EsDelivery record);

    int updateByPrimaryKey(EsDelivery record);
}