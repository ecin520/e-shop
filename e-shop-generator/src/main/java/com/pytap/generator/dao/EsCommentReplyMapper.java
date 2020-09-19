package com.pytap.generator.dao;

import com.pytap.generator.entity.EsCommentReply;
import com.pytap.generator.entity.EsCommentReplyExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EsCommentReplyMapper {
    int countByExample(EsCommentReplyExample example);

    int deleteByExample(EsCommentReplyExample example);

    int deleteByPrimaryKey(Long id);

    int insert(EsCommentReply record);

    int insertSelective(EsCommentReply record);

    List<EsCommentReply> selectByExample(EsCommentReplyExample example);

    EsCommentReply selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") EsCommentReply record, @Param("example") EsCommentReplyExample example);

    int updateByExample(@Param("record") EsCommentReply record, @Param("example") EsCommentReplyExample example);

    int updateByPrimaryKeySelective(EsCommentReply record);

    int updateByPrimaryKey(EsCommentReply record);
}