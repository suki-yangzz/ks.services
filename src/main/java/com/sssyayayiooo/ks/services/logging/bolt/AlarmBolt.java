package com.sssyayayiooo.ks.services.logging.bolt;


import com.sssyayayiooo.ks.services.logging.bases.Dictionaries;
import com.sssyayayiooo.ks.services.logging.interfaces.IAlarmDataRepositorys;
import com.sssyayayiooo.ks.services.logging.launcher.StormKafkaLauncher;
import com.sssyayayiooo.ks.services.logging.repositorys.AlarmDataRepositorys;
import org.apache.storm.task.OutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseRichBolt;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Tuple;
import java.util.Map;

/**
 * @author  Leezer
 * @date 2017/12/26
 * 处理相关失败次数统计
 */
public class AlarmBolt extends BaseRichBolt {
    private OutputCollector collector;
    private IAlarmDataRepositorys dataRepositorys;

    @Override
    public void prepare(Map stormConf, TopologyContext context,
                        OutputCollector collector) {
        this.collector = collector;
        StormKafkaLauncher stormLauncher = StormKafkaLauncher.getStormLauncher();
        dataRepositorys =(AlarmDataRepositorys)  stormLauncher.getBean("alarmdataRepositorys");
    }

    @Override
    public void execute(Tuple input) {
        try {
            String mesg = input.getString(0);
            System.out.println(mesg);
                if (mesg.contains(Dictionaries.REDIS_ERROR_PREFIX)) {
                    String erroNum = dataRepositorys.getErrNumFromRedis("redis","failure-connect-of-minutes");
                    if(erroNum != null) {
                        int num = Integer.parseInt(erroNum);
                        if(num > Dictionaries.REDIS_EXCEED_NUM) {
//                            sendSms("redis失败次数超过" + Dictionaries.REDIS_EXCEED_NUM);
                            num = num - Math.round(num/2);
                        }
                        dataRepositorys.setErrNumToRedis("redis","failure-connect-of-minutes",Integer.toString(num+1));
                    } else {
                        dataRepositorys.setErrNumToRedis("redis","failure-connect-of-minutes","1");
                    }
                }

                if (mesg.contains(Dictionaries.MONGO_ERROR_PREFIX)) {
                    String erroNum = dataRepositorys.getErrNumFromRedis("mongo","failure-connect-of-minutes");
                    if(erroNum != null) {
                        int num = Integer.parseInt(erroNum);
                        if(num > Dictionaries.MONGO_EXCEED_NUM) {
//                            sendSms("mongo失败次数超过" + Dictionaries.MONGO_EXCEED_NUM);
                            num = num - Math.round(num/2);
                        }
                        dataRepositorys.setErrNumToRedis("mongo","failure-connect-of-minutes",Integer.toString(num+1));
                    } else {
                        dataRepositorys.setErrNumToRedis("mongo","failure-connect-of-minutes","1");
                    }
                }


        } catch (Exception e) {
            e.printStackTrace();
            collector.fail(input);
        }
        collector.ack(input);
    }


    @Override
    public void declareOutputFields(OutputFieldsDeclarer declarer) {
        declarer.declare(new Fields("id","data"));
    }
}
