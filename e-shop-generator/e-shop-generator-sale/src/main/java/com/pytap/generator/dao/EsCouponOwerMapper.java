package com.pytap.generator.dao;

import com.pytap.generator.entity.EsCouponOwer;
import com.pytap.generator.entity.EsCouponOwerExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EsCouponOwerMapper {
    int countByExample(EsCouponOwerExample example);

    int deleteByExample(EsCouponOwerExample example);

    int deleteByPrimaryKey(Long id);

    int insert(EsCouponOwer record);

    int insertSelective(EsCouponOwer record);

    List<EsCouponOwer> selectByExample(EsCouponOwerExample example);

    EsCouponOwer selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") EsCouponOwer record, @Param("example") EsCouponOwerExample example);

    int updateByExample(@Param("record") EsCouponOwer record, @Param("example") EsCouponOwerExample example);

    int updateByPrimaryKeySelective(EsCouponOwer record);

    int updateByPrimaryKey(EsCouponOwer record);
}