package com.pytap.generator.dao;

import com.pytap.generator.entity.EsCoupon;
import com.pytap.generator.entity.EsCouponExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EsCouponMapper {
    int countByExample(EsCouponExample example);

    int deleteByExample(EsCouponExample example);

    int deleteByPrimaryKey(Long id);

    int insert(EsCoupon record);

    int insertSelective(EsCoupon record);

    List<EsCoupon> selectByExample(EsCouponExample example);

    EsCoupon selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") EsCoupon record, @Param("example") EsCouponExample example);

    int updateByExample(@Param("record") EsCoupon record, @Param("example") EsCouponExample example);

    int updateByPrimaryKeySelective(EsCoupon record);

    int updateByPrimaryKey(EsCoupon record);
}