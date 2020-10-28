package com.pytap.generator.dao;

import com.pytap.generator.entity.EsProductCarousel;
import com.pytap.generator.entity.EsProductCarouselExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EsProductCarouselMapper {
    int countByExample(EsProductCarouselExample example);

    int deleteByExample(EsProductCarouselExample example);

    int deleteByPrimaryKey(Long id);

    int insert(EsProductCarousel record);

    int insertSelective(EsProductCarousel record);

    List<EsProductCarousel> selectByExample(EsProductCarouselExample example);

    EsProductCarousel selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") EsProductCarousel record, @Param("example") EsProductCarouselExample example);

    int updateByExample(@Param("record") EsProductCarousel record, @Param("example") EsProductCarouselExample example);

    int updateByPrimaryKeySelective(EsProductCarousel record);

    int updateByPrimaryKey(EsProductCarousel record);
}