package com.pytap.api.service.api.urp;

import com.pytap.api.model.vo.MemberVO;
import com.pytap.api.service.hystrix.urp.MemberFeignHystrix;
import com.pytap.common.utils.ResultEntity;
import com.pytap.generator.entity.EsMember;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author Ecin520
 * @date 2020/9/24 16:59
 */
@FeignClient(value = "URP-PROVIDER", fallbackFactory = MemberFeignHystrix.class)
public interface MemberFeignService {

    /**
     * 远程调用会员参数查询会员
     * @param queryParam 查询参数
     * @return ResultEntity<EsMember>
     * */
    @RequestMapping(value = "/feign/member/query")
    ResultEntity<EsMember> getMember(@RequestBody EsMember queryParam);

    /**
     * 远程调用会员参数查询会员VO
     * @param queryParam 查询参数
     * @return ResultEntity<MemberVO>
     * */
    @RequestMapping(value = "/feign/member/view/query", method = RequestMethod.POST)
    ResultEntity<MemberVO> getMemberVO(@RequestBody EsMember queryParam);

}