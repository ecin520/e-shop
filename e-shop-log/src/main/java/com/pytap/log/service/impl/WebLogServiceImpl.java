package com.pytap.log.service.impl;

import com.pytap.common.utils.TimeUtil;
import com.pytap.log.dto.WebLogDTO;
import com.pytap.log.service.WebLogService;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author Ecin520
 * @date 2020/4/7 2:21
 */
@Service
public class WebLogServiceImpl implements WebLogService {

	@Resource
	private RedisTemplate<String, Object> redisTemplate;

	@Override
	public void insertWebLog(WebLogDTO webLog) {
		redisTemplate.opsForList().leftPush("web_log:" + TimeUtil.getDayDate(), webLog);
	}

	@Override
	public List<Object> listWebLogs() {
		return redisTemplate.opsForList().range("web_log:" + TimeUtil.getDayDate(), 0, -1);
	}

	@Override
	public List<Object> listWebLogsByDate(String date) {
		return redisTemplate.opsForList().range("web_log:" + date, 0, -1);
	}

	@Override
	public Set<String> listWebLogKeys() {
		return redisTemplate.keys("web_log:*");
	}


	@Override
	@Transactional(rollbackFor = Exception.class)
	public Integer deleteAllWebLogs() {
		try {
			redisTemplate.delete("web_log:" + TimeUtil.getDayDate());
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}


}
