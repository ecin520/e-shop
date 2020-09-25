package com.pytap.urp.controller.api;

import com.pytap.common.annotation.Log;
import com.pytap.common.utils.ResultEntity;
import com.pytap.generator.entity.EsMember;
import com.pytap.urp.model.vo.MemberVO;
import com.pytap.urp.service.MemberService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author Ecin520
 * @date 2020/9/24 16:54
 */
@RequestMapping("/feign/member")
@RestController
public class MemberFeignClient {

    @Resource
    private MemberService memberService;

    @Log("远程调用会员参数查询会员")
    @RequestMapping(value = "/query", method = RequestMethod.POST)
    public ResultEntity<EsMember> getMember(@RequestBody EsMember queryParam) {
        return ResultEntity.success(memberService.getMember(queryParam));
    }

    @Log("远程调用会员参数查询会员VO")
    @RequestMapping(value = "/view/query", method = RequestMethod.POST)
    public ResultEntity<MemberVO> getMemberVO(@RequestBody EsMember queryParam) {
        return ResultEntity.success(memberService.getMemberVO(queryParam));
    }

}
