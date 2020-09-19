package com.pytap.generator.dao;

import com.pytap.generator.entity.EsNewProductRecommend;
import com.pytap.generator.entity.EsNewProductRecommendExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EsNewProductRecommendMapper {
    int countByExample(EsNewProductRecommendExample example);

    int deleteByExample(EsNewProductRecommendExample example);

    int deleteByPrimaryKey(Long id);

    int insert(EsNewProductRecommend record);

    int insertSelective(EsNewProductRecommend record);

    List<EsNewProductRecommend> selectByExample(EsNewProductRecommendExample example);

    EsNewProductRecommend selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") EsNewProductRecommend record, @Param("example") EsNewProductRecommendExample example);

    int updateByExample(@Param("record") EsNewProductRecommend record, @Param("example") EsNewProductRecommendExample example);

    int updateByPrimaryKeySelective(EsNewProductRecommend record);

    int updateByPrimaryKey(EsNewProductRecommend record);
}