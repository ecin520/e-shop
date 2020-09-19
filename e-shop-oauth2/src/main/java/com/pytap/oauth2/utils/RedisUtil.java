package com.pytap.oauth2.utils;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @author Ecin520
 * @date 2020/8/19 10:52
 */
@Component
public class RedisUtil {

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    public void set(String key, Object object) {
        redisTemplate.opsForValue().set(key, object);
    }

    public void set(String key, Object object, long expire) {
        redisTemplate.opsForValue().set(key, object, expire, TimeUnit.SECONDS);
    }

    public void expire(String key, long expire) {
        redisTemplate.expire(key, expire, TimeUnit.SECONDS);
    }

    public Object get(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    public void remove(String key) {
        redisTemplate.delete(key);
    }

    public Long increment(String key, long expire) {
        return redisTemplate.opsForValue().increment(key, expire);
    }

}