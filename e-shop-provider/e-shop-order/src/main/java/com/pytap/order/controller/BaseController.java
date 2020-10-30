package com.pytap.order.controller;

import com.pytap.api.model.vo.UserVO;
import com.pytap.api.service.api.urp.MemberFeignService;
import com.pytap.api.service.api.urp.UserFeignService;
import com.pytap.common.constant.RedisKey;
import com.pytap.common.exception.GeneralException;
import com.pytap.common.utils.ResultEntity;
import com.pytap.generator.entity.EsMember;
import com.pytap.order.utils.RedisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.annotation.Resource;

/**
 * @author Ecin520
 * @date 2020/10/30 10:59
 */
public class BaseController {

    private static final Logger logger = LoggerFactory.getLogger(BaseController.class);

    @Resource
    private MemberFeignService memberFeignService;

    @Resource
    private UserFeignService userFeignService;

    @Resource
    private RedisUtil redisUtil;

    protected Long getUserId() throws GeneralException {
        logger.info("开始获取用户id");
        String username = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Object object = redisUtil.get(RedisKey.USER_KEY + username);
        if (null == object) {
            logger.info("缓存没有用户信息，正在远程调用用户信息查询接口");
            ResultEntity<UserVO> resultEntity = userFeignService.getUserByUsername(username);
            if (200 == resultEntity.getCode()) {
                UserVO vo = resultEntity.getData();

                if (null == vo) {
                    throw new GeneralException("用户不存在");
                }

                redisUtil.set(RedisKey.USER_KEY + username, vo.getId());
                return vo.getId();
            }
        } else {
            return Long.valueOf(object.toString());
        }
        return null;
    }

    protected EsMember getMember() throws GeneralException {
        logger.info("获取会员信息");
        Long userId = getUserId();
        Object object = redisUtil.get(RedisKey.MEMBER_KEY + userId);
        if (null == object) {
            logger.info("缓存没有会员信息，正在远程调用会员信息查询接口");
            EsMember member = new EsMember();
            member.setUserId(userId);
            ResultEntity<EsMember> resultEntity = memberFeignService.getMember(member);
            if (200 == resultEntity.getCode()) {
                redisUtil.set(RedisKey.MEMBER_KEY + userId, resultEntity.getData());
                return resultEntity.getData();
            }
        }
        return (EsMember) object;
    }

}
