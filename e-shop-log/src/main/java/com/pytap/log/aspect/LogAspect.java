package com.pytap.log.aspect;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.pytap.common.annotation.Log;
import com.pytap.common.utils.TimeUtil;
import com.pytap.log.vo.ErrorLogVO;
import com.pytap.log.vo.WebLogVO;
import com.pytap.log.service.ErrorLogService;
import com.pytap.log.service.WebLogService;
import com.pytap.log.utils.SecurityUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

/**
 * web日志切面
 * @author Ecin520
 * @date 2020/4/7 0:35
 */
@Aspect
@Component
public class LogAspect {

	@Resource
	private WebLogService webLogService;

	@Resource
	private ErrorLogService errorLogService;

	private final ThreadLocal<Long> currentTime = new ThreadLocal<>();

	private static final Logger logger = LoggerFactory.getLogger(LogAspect.class);

	@Pointcut("@annotation(com.pytap.common.annotation.Log)")
	public void pointCut() {

	}


	@Around("pointCut()")
	public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {

		currentTime.set(System.currentTimeMillis());

		WebLogVO webLogVO = new WebLogVO();

		Object result;

		MethodSignature signature = (MethodSignature) joinPoint.getSignature();
		Method method = signature.getMethod();

		Log log = method.getAnnotation(Log.class);

		ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();

		if (null != requestAttributes) {
			HttpServletRequest request = requestAttributes.getRequest();
			webLogVO.setIp(request.getRemoteAddr());
			webLogVO.setUrl(request.getRequestURI());

			if (request.getParameterMap().size() == 0) {
				StringBuilder sb = new StringBuilder();
				Object[] args = joinPoint.getArgs();
				for (int i = 0; i < args.length; i++) {
					sb.append(args[i].toString());
					if (i != args.length - 1) {
						sb.append(",");
					}
				}
				webLogVO.setParam(sb.toString());
			} else {
				webLogVO.setParam(JSONObject.toJSONString(request.getParameterMap()));
			}

		}

		result = joinPoint.proceed();

		webLogVO.setMessage(log.value());
		webLogVO.setMethod(joinPoint.getSignature().getName());

		if (null != result) {
			webLogVO.setResult(JSONObject.toJSONString(result));
		} else {
			webLogVO.setResult("void");
		}
		webLogVO.setUsername(SecurityUtil.getUsername());
		webLogVO.setCreateTime(TimeUtil.getDate());
		webLogVO.setSpendTime(System.currentTimeMillis() - currentTime.get());

		logger.info(webLogVO.toString());

		webLogService.insertWebLog(webLogVO);

		currentTime.remove();

		return result;

	}

	/**
	 * 异常切面
	 * */
	@AfterThrowing(pointcut = "pointCut()", throwing = "e")
	public void errorLogSave(JoinPoint joinPoint, Throwable e) throws Throwable {
		RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
		if (null != requestAttributes) {
			HttpServletRequest request = (HttpServletRequest)
					requestAttributes.resolveReference(RequestAttributes.REFERENCE_REQUEST);
			if (null != request) {
				try {
					ErrorLogVO errorLog = new ErrorLogVO();

					MethodSignature signature = (MethodSignature) joinPoint.getSignature();
					Method method = signature.getMethod();

					errorLog.setMethod(method.getName());
					if (request.getParameterMap().size() == 0) {
						errorLog.setParam(JSONArray.toJSONString(joinPoint.getArgs()));
					} else {
						errorLog.setParam(JSONObject.toJSONString(request.getParameterMap()));
					}
					errorLog.setName(e.getClass().getName());
					errorLog.setMessage(stackTraceToString(e.getClass().getName(), e.getMessage(), e.getStackTrace()));
					errorLog.setUsername(SecurityUtil.getUsername());
					errorLog.setUrl(request.getRequestURI());
					errorLog.setIp(request.getRemoteAddr());
					errorLog.setCreateTime(TimeUtil.getDate());

					errorLogService.insertErrorLog(errorLog);

				} catch (Exception exception) {
					logger.error(exception.getMessage());
				}
			}
		}
		// 不要忘记抛出异常，不然ControllerAdvice无法捕获
		throw e;
	}


	/**
	 * 获取堆栈信息
	 * */
	private String stackTraceToString(String name, String message, StackTraceElement[] elements) {
		StringBuilder builder = new StringBuilder();
	    for (StackTraceElement element : elements) {
		    builder.append(element).append("\n");
	    }
	    return name + ":" + message + "\n\t" + builder.toString();
	}

}
