package com.sssyayayiooo.ks.services.logging.bases;

import org.springframework.stereotype.Component;


/**
 * @author : Leezer
 * @version : 1.0
 */
@Component
public class Dictionaries {
    /**
     * 异常报警触发值
     **/
    public static final Integer REDIS_EXCEED_NUM = 100;
    public static final Integer MONGO_EXCEED_NUM = 100;
    /**
     * Redis生命周期（以秒为单位）
     **/
    public static int REDISLIFECYCLE = 30;
    /**
     * redis异常前缀
     **/
    public static String REDIS_ERROR_PREFIX = "redis_error_prefix:";
    /**
     *mongo异常前缀
     **/
    public static String MONGO_ERROR_PREFIX = "mongo_error_prefix:";

}
