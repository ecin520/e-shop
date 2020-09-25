package com.pytap.generator.dao;

import com.pytap.generator.entity.EsCustomerService;
import com.pytap.generator.entity.EsCustomerServiceExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EsCustomerServiceMapper {
    int countByExample(EsCustomerServiceExample example);

    int deleteByExample(EsCustomerServiceExample example);

    int deleteByPrimaryKey(Long id);

    int insert(EsCustomerService record);

    int insertSelective(EsCustomerService record);

    List<EsCustomerService> selectByExample(EsCustomerServiceExample example);

    EsCustomerService selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") EsCustomerService record, @Param("example") EsCustomerServiceExample example);

    int updateByExample(@Param("record") EsCustomerService record, @Param("example") EsCustomerServiceExample example);

    int updateByPrimaryKeySelective(EsCustomerService record);

    int updateByPrimaryKey(EsCustomerService record);
}