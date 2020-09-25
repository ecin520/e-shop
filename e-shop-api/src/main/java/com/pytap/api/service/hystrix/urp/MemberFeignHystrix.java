package com.pytap.api.service.hystrix.urp;

import com.pytap.api.model.vo.MemberVO;
import com.pytap.api.service.api.urp.MemberFeignService;
import com.pytap.common.utils.ResultEntity;
import com.pytap.generator.entity.EsMember;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * @author Ecin520
 * @date 2020/9/24 16:59
 */
@Component
public class MemberFeignHystrix implements FallbackFactory<MemberFeignService> {

    @Override
    public MemberFeignService create(Throwable throwable) {
        return new MemberFeignService() {
            @Override
            public ResultEntity<EsMember> getMember(EsMember queryParam) {
                return ResultEntity.fail(500, "服务暂时不可用");
            }

            @Override
            public ResultEntity<MemberVO> getMemberVO(EsMember queryParam) {
                return ResultEntity.fail(500, "服务暂时不可用");
            }
        };
    }
}
