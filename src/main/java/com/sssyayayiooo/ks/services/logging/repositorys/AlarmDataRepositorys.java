package com.sssyayayiooo.ks.services.logging.repositorys;

import com.sssyayayiooo.ks.services.logging.bases.Dictionaries;
import com.sssyayayiooo.ks.services.logging.bases.RedisBase;
import com.sssyayayiooo.ks.services.logging.interfaces.IAlarmDataRepositorys;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;


/**
 * @author Leezer
 * @date 2017/12/27
 * 存储操作失败时间
 **/
@Service("alarmdataRepositorys")
public class AlarmDataRepositorys extends RedisBase implements IAlarmDataRepositorys {
    private static final String ERRO = "erro";

    /**
     * @param type 类型
     * @param key key值
     * @return 错误次数
     **/
    @Override
    public String getErrNumFromRedis(String type,String key) {
        if(type==null || key == null){
            return null;
        }else {
            ValueOperations<String, String> valueOper = primaryStringRedisTemplate.opsForValue();
            return valueOper.get(String.format("%s:%s:%s",ERRO,type,key));
        }

    }


    /**
     * @param type 错误类型
     * @param key key值
     * @param value 存储值
     **/
    @Override
    public void setErrNumToRedis(String type, String key,String value) {
        try {
            ValueOperations<String, String> valueOper = primaryStringRedisTemplate.opsForValue();
            valueOper.set(String.format("%s:%s:%s", ERRO,type, key), value, Dictionaries.REDISLIFECYCLE, TimeUnit.SECONDS);
        }catch (Exception e){
            logger.info(Dictionaries.REDIS_ERROR_PREFIX+String.format("key为%s存入redis失败",key));
        }
    }



}
