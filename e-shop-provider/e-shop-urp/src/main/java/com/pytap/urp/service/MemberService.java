package com.pytap.urp.service;

import com.pytap.generator.entity.EsMember;

import java.util.List;

/**
 * @author Ecin520
 * @date 2020/8/25 11:00
 */
public interface MemberService {
    /**
     * 统计总数
     * @return Integer
     * */
    Integer countMember();

    /**
     * 插入会员
     * @param member 会员实体
     * @return Integer
     * */
    Integer insertMember(EsMember member);

    /**
     * 删除会员
     * @param id 会员id
     * @return Integer
     * */
    Integer deleteMemberById(Long id);

    /**
     * 删除会员
     * @param userId 用户id
     * @return Integer
     * */
    Integer deleteMemberByUserId(Long userId);

    /**
     * 更新会员
     * @param member 会员实体
     * @return Integer
     * */
    Integer updateMemberById(EsMember member);

    /**
     * 查询会员
     * @param queryParam 查询参数
     * @return Member 会员实体
     * */
    EsMember getMember(EsMember queryParam);

    /**
     * 获取所有会员
     * @param pageNum 第几页
     * @param pageSize 每页条目数量
     * @return List<SysRole>
     * */
    List<EsMember> listMembers(Integer pageNum, Integer pageSize);
}
