package com.pytap.urp.utils;

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

    public Long leftPush(String key, Object value) {
        return redisTemplate.opsForList().leftPush(key, value);
    }

    public Long leftPush(String key, Object value, long expire) {
        Long count = redisTemplate.opsForList().leftPush(key, value);
        expire(key, expire);
        return count;
    }

    public Long leftPushAll(String key, Object... values) {
        return redisTemplate.opsForList().leftPush(key, values);
    }

    public Long leftPushAll(String key, long expire, Object... values) {
        Long count = redisTemplate.opsForList().leftPush(key, values);
        expire(key, expire);
        return count;
    }

    public Long leftRemove(String key, long count, Object value) {
        return redisTemplate.opsForList().remove(key, count, value);
    }

}