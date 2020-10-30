package com.pytap.urp.service.impl;

import com.github.pagehelper.PageHelper;
import com.pytap.generator.dao.EsMemberMapper;
import com.pytap.generator.entity.EsMember;
import com.pytap.generator.entity.EsMemberExample;
import com.pytap.urp.model.vo.MemberVO;
import com.pytap.urp.model.vo.UserVO;
import com.pytap.urp.service.MemberService;
import com.pytap.urp.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @author Ecin520
 * @date 2020/8/25 11:05
 */
@Service
public class MemberServiceImpl implements MemberService {

    @Resource
    private EsMemberMapper memberMapper;

    @Resource
    private UserService userService;

    @Override
    public Integer countMember() {
        return memberMapper.countByExample(null);
    }

    @Override
    public Integer insertMember(EsMember member) {
        member.setCreateTime(new Date());
        return memberMapper.insert(member);
    }

    @Override
    public Integer deleteMemberById(Long id) {
        return memberMapper.deleteByPrimaryKey(id);
    }

    @Override
    public Integer deleteMemberByUserId(Long userId) {
        EsMemberExample example = new EsMemberExample();
        EsMemberExample.Criteria criteria = example.createCriteria();
        criteria.andUserIdEqualTo(userId);
        return memberMapper.deleteByExample(example);
    }

    @Override
    public Integer updateMemberById(EsMember member) {
        member.setUpdateTime(new Date());
        return memberMapper.updateByPrimaryKey(member);
    }

    @Override
    public EsMember getMember(EsMember queryParam) {
        return getMemberByQueryParam(queryParam);
    }

    @Override
    public MemberVO getMemberVO(EsMember queryParam) {
        EsMember member = getMemberByQueryParam(queryParam);
        MemberVO vo = new MemberVO();
        BeanUtils.copyProperties(member, vo);
        UserVO user = userService.getUserVOById(member.getUserId());
        if (null != user) {
            vo.setUser(user);
        }
        return vo;
    }

    @Override
    public List<EsMember> listMembers(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return memberMapper.selectByExample(null);
    }

    private EsMember getMemberByQueryParam(EsMember queryParam) {

        EsMemberExample example = new EsMemberExample();
        EsMemberExample.Criteria criteria = example.createCriteria();

        if (null != queryParam.getId()) {
            criteria.andIdEqualTo(queryParam.getId());
        }
        if (null != queryParam.getMemberNumber()) {
            criteria.andMemberNumberEqualTo(queryParam.getMemberNumber());
        }
        if (null != queryParam.getUserId()) {
            criteria.andUserIdEqualTo(queryParam.getUserId());
        }
        if (null != queryParam.getUsername()) {
            criteria.andUsernameEqualTo(queryParam.getUsername());
        }
        List<EsMember> list= memberMapper.selectByExample(example);

        return list.size() != 0 ? list.get(0) : null;
    }
}
