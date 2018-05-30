package com.sssyayayiooo.ks.services.logging.interfaces;

/**
 * @author Leezer
 * @date 2017/12/28
 **/
public interface IAlarmDataRepositorys {

    /**
     * 从redis获取错误次数
     * @param type 错误类型(如redis/mongo/服务器错误)
     * @param key 错误原因
     * @return 错误次数
     **/
    String getErrNumFromRedis(String type, String key);

    /**
     * 存储错误次数到redis
     * @param type 错误类型(如redis/mongo/服务器错误)
     * @param key 错误原因
     * @param value 错误次数
     **/
    void setErrNumToRedis(String type, String key, String value);


}
