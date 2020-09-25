package com.pytap.generator.dao;

import com.pytap.generator.entity.EsMerchant;
import com.pytap.generator.entity.EsMerchantExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EsMerchantMapper {
    int countByExample(EsMerchantExample example);

    int deleteByExample(EsMerchantExample example);

    int deleteByPrimaryKey(Long id);

    int insert(EsMerchant record);

    int insertSelective(EsMerchant record);

    List<EsMerchant> selectByExample(EsMerchantExample example);

    EsMerchant selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") EsMerchant record, @Param("example") EsMerchantExample example);

    int updateByExample(@Param("record") EsMerchant record, @Param("example") EsMerchantExample example);

    int updateByPrimaryKeySelective(EsMerchant record);

    int updateByPrimaryKey(EsMerchant record);
}