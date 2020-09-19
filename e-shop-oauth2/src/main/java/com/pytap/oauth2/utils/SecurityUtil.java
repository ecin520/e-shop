package com.pytap.oauth2.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * @author Ecin520
 * @date 2020/4/14 0:48
 */
public class SecurityUtil {

	private static final Logger logger = LoggerFactory.getLogger(SecurityUtil.class);

	public static String getUsername() {
		String username = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		logger.info("用户{}调用接口", username);
		return username;
	}

}
