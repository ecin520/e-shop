package com.pytap.generator.dao;

import com.pytap.generator.entity.EsOrderReturnReason;
import com.pytap.generator.entity.EsOrderReturnReasonExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EsOrderReturnReasonMapper {
    int countByExample(EsOrderReturnReasonExample example);

    int deleteByExample(EsOrderReturnReasonExample example);

    int deleteByPrimaryKey(Long id);

    int insert(EsOrderReturnReason record);

    int insertSelective(EsOrderReturnReason record);

    List<EsOrderReturnReason> selectByExample(EsOrderReturnReasonExample example);

    EsOrderReturnReason selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") EsOrderReturnReason record, @Param("example") EsOrderReturnReasonExample example);

    int updateByExample(@Param("record") EsOrderReturnReason record, @Param("example") EsOrderReturnReasonExample example);

    int updateByPrimaryKeySelective(EsOrderReturnReason record);

    int updateByPrimaryKey(EsOrderReturnReason record);
}