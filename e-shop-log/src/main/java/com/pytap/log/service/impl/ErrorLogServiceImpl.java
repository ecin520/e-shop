package com.pytap.log.service.impl;


import com.pytap.common.utils.TimeUtil;
import com.pytap.log.dto.ErrorLogDTO;
import com.pytap.log.service.ErrorLogService;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Ecin520
 * @date 2020/4/15 23:46
 */
@Service
public class ErrorLogServiceImpl implements ErrorLogService {

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @Override
    public void insertErrorLog(ErrorLogDTO errorLogDTO) {
        redisTemplate.opsForList().leftPush("error_log:" + TimeUtil.getDayDate(), errorLogDTO);
    }

    @Override
    public List<ErrorLogDTO> getErrorLogs(ErrorLogDTO errorLogDTO) {
        return null;
    }

    @Override
    public List<Object> listErrorLogs() {
        return redisTemplate.opsForList().range("error_log:" + TimeUtil.getDayDate(), 0, -1);
    }

    @Override
    public Integer deleteAllErrorLogs() {
        try {
            redisTemplate.delete("error_log:" + TimeUtil.getDayDate());
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }
}
