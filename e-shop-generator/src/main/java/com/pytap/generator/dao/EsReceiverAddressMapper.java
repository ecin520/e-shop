package com.pytap.generator.dao;

import com.pytap.generator.entity.EsReceiverAddress;
import com.pytap.generator.entity.EsReceiverAddressExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EsReceiverAddressMapper {
    int countByExample(EsReceiverAddressExample example);

    int deleteByExample(EsReceiverAddressExample example);

    int deleteByPrimaryKey(Long id);

    int insert(EsReceiverAddress record);

    int insertSelective(EsReceiverAddress record);

    List<EsReceiverAddress> selectByExample(EsReceiverAddressExample example);

    EsReceiverAddress selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") EsReceiverAddress record, @Param("example") EsReceiverAddressExample example);

    int updateByExample(@Param("record") EsReceiverAddress record, @Param("example") EsReceiverAddressExample example);

    int updateByPrimaryKeySelective(EsReceiverAddress record);

    int updateByPrimaryKey(EsReceiverAddress record);
}