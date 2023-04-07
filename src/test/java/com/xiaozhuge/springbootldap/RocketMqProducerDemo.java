
package com.xiaozhuge.springbootldap;


import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;

public class RocketMqProducerDemo {
    public static void main(String[] args) throws Exception {
        try{
            DefaultMQProducer producer = new DefaultMQProducer("test");
            producer.setNamesrvAddr("10.10.95.252:30331");//MQ服务器地址
            producer.setVipChannelEnabled(false);
            producer.start();
            for (int i = 0; i < 20; i++) {
                Message msg = new Message("test", "TagA" , ("Hello RocketMQ " + i).getBytes(RemotingHelper.DEFAULT_CHARSET));
                SendResult sendResult = producer.send(msg);
                System.out.printf("%s%n", sendResult);
                System.out.println("--");
                Thread.sleep(1000);
            }
            producer.shutdown();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}