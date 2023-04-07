package com.xiaozhuge.springbootldap;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.remoting.common.RemotingHelper;

import java.io.UnsupportedEncodingException;
import java.util.List;

public class TransactionConsumer{
    
    //namesrv地址
    private static String namesrcaddress="10.10.102.52:31206";
    
    public static void main(String[] args) throws MQClientException {
        //创建DefaultMQPushConsumer
        DefaultMQPushConsumer consumer = new
            DefaultMQPushConsumer("groupb");
        //设置namesrv地址
        consumer.setNamesrvAddr(namesrcaddress);
        //设置每次拉取的消息个数
        consumer.subscribe("demo","TagA");
        //消息监听
        consumer.registerMessageListener(new MessageListenerConcurrently(){
           @Override
           public ConsumeConcurrentlyStatus consumeMessage(
                   List<MessageExt> msgs, ConsumeConcurrentlyContext context){
               try{
                   for(Message msg : msgs){
                       String topic = msg.getTopic();
                       String tags = msg.getTags();
                       String keys = msg.getKeys();
                       String body = 
                           new String(msg.getBody(), RemotingHelper.DEFAULT_CHARSET);
                       System.out.println("topic:"+topic+",tags:"+tags
                                        +",keys:"+keys+",body:"+body);
                   }
               }catch(UnsupportedEncodingException e){
                   e.printStackTrace();
               }
               return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
           }
        });

        try {
            Thread.sleep(50000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
