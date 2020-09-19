package com.pytap.generator.dao;

import com.pytap.generator.entity.EsPromotion;
import com.pytap.generator.entity.EsPromotionExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EsPromotionMapper {
    int countByExample(EsPromotionExample example);

    int deleteByExample(EsPromotionExample example);

    int deleteByPrimaryKey(Long id);

    int insert(EsPromotion record);

    int insertSelective(EsPromotion record);

    List<EsPromotion> selectByExample(EsPromotionExample example);

    EsPromotion selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") EsPromotion record, @Param("example") EsPromotionExample example);

    int updateByExample(@Param("record") EsPromotion record, @Param("example") EsPromotionExample example);

    int updateByPrimaryKeySelective(EsPromotion record);

    int updateByPrimaryKey(EsPromotion record);
}