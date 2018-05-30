package com.sssyayayiooo.ks.services.logging.bases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

/**
 * @author Leezer
 * @version 1.0
 **/
public class RedisBase extends LogBase {
    @Autowired
    @Qualifier("stringRedisTemplate1")
    protected StringRedisTemplate primaryStringRedisTemplate;

    @Autowired
    @Qualifier("redisTemplate1")
    protected RedisTemplate<String, Object> primaryRedisTemplate;
}
