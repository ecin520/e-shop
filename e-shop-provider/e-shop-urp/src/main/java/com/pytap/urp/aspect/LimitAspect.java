package com.pytap.urp.aspect;

import com.pytap.common.annotation.Limit;
import com.pytap.common.exception.OverLimitException;
import com.pytap.urp.utils.RedisUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

/**
 * @author Ecin520
 * @date 2020/8/19 10:50
 */
@Aspect
@Component
public class LimitAspect {

    private static final Logger logger = LoggerFactory.getLogger(LimitAspect.class);

    private static final String LIMIT_NAME = "IP_LIMIT";

    @Resource
    private RedisUtil redisUtil;

    @Pointcut("@annotation(com.pytap.common.annotation.Limit)")
    public void pointCut() {

    }

    @Around("pointCut()")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {

        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();

        Limit limit = method.getAnnotation(Limit.class);

        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();

        if (null != requestAttributes) {
            HttpServletRequest request = requestAttributes.getRequest();
            String ip = request.getRemoteAddr();
            long count = redisUtil.increment(LIMIT_NAME + ip, 1);
            if (count == 1) {
                redisUtil.expire(LIMIT_NAME + ip, limit.seconds());
            } else if (count > limit.maxCount()) {
                logger.error("{}:操作超出频数限制", ip);
                throw new OverLimitException("操作超出频数限制，请稍后再试。");
            }
        }
        return joinPoint.proceed();
    }

}
