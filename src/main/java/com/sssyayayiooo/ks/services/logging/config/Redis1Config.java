package com.sssyayayiooo.ks.services.logging.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * @author : Leezer
 * @version : 1.0
 */
@Configuration
public class Redis1Config {
    @Value("${spring.data.redis1.enabled}")
    private Boolean enabled;

    /**
     * Primary库Redis连接池配置
     * @return JedisConnectionFactory
     */
    @Primary
    @Bean(name = "redisConnectionFactory1")
    @Qualifier("redisConnectionFactory1")
    @ConfigurationProperties(prefix="spring.data.redis1")
    public RedisConnectionFactory redisConnectionFactory1() {
        if(enabled == null || !enabled){
            return null;
        }
        else {
            return new JedisConnectionFactory();
        }
    }

    /**
     * Primary库Redis连接模板
     * @param redisConnectionFactory 系统自动注入
     * @return redisTemplate1
     */
    @Primary
    @Bean(name = "redisTemplate1")
    @Qualifier("redisTemplate1")
    public RedisTemplate<String, Object> redisTemplate1(@Qualifier("redisConnectionFactory1") RedisConnectionFactory redisConnectionFactory) {
        if(enabled == null || !enabled){
            return null;
        }
        else {
            RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
            redisTemplate.setConnectionFactory(redisConnectionFactory);
            redisTemplate.setKeySerializer(new StringRedisSerializer());
            redisTemplate.setHashKeySerializer(new StringRedisSerializer());
            redisTemplate.setHashValueSerializer(new JdkSerializationRedisSerializer());
            redisTemplate.setValueSerializer(new JdkSerializationRedisSerializer());
            redisTemplate.afterPropertiesSet();
            return redisTemplate;
        }
    }

    /**
     * Primary库Redis的String连接模板
     * @param redisConnectionFactory 系统自动注入
     * @return stringRedisTemplate1
     */
    @Primary
    @Bean(name = "stringRedisTemplate1")
    @Qualifier("stringRedisTemplate1")
    public StringRedisTemplate stringRedisTemplate1(@Qualifier("redisConnectionFactory1") RedisConnectionFactory redisConnectionFactory) {
        if(enabled == null || !enabled){
            return null;
        }
        else {
            StringRedisTemplate stringRedisTemplate = new StringRedisTemplate();
            stringRedisTemplate.setConnectionFactory(redisConnectionFactory);
            return stringRedisTemplate;
        }
    }

}
