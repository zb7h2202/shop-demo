package com.myself.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class RedisContext {

    @Autowired
    private StringRedisTemplate redisTemplate;

    public void set(String key,String value,long time,TimeUnit timeUnit){
        redisTemplate.opsForValue().set(key,value,time, timeUnit);
    }

    public String getStr(String key){
        String s = redisTemplate.opsForValue().get(key);
        return s;
    }



}
