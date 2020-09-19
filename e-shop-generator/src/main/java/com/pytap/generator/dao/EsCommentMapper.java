package com.pytap.generator.dao;

import com.pytap.generator.entity.EsComment;
import com.pytap.generator.entity.EsCommentExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EsCommentMapper {
    int countByExample(EsCommentExample example);

    int deleteByExample(EsCommentExample example);

    int deleteByPrimaryKey(Long id);

    int insert(EsComment record);

    int insertSelective(EsComment record);

    List<EsComment> selectByExampleWithBLOBs(EsCommentExample example);

    List<EsComment> selectByExample(EsCommentExample example);

    EsComment selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") EsComment record, @Param("example") EsCommentExample example);

    int updateByExampleWithBLOBs(@Param("record") EsComment record, @Param("example") EsCommentExample example);

    int updateByExample(@Param("record") EsComment record, @Param("example") EsCommentExample example);

    int updateByPrimaryKeySelective(EsComment record);

    int updateByPrimaryKeyWithBLOBs(EsComment record);

    int updateByPrimaryKey(EsComment record);
}