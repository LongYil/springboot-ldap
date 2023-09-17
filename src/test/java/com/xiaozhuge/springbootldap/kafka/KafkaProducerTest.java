package com.xiaozhuge.springbootldap.kafka;

import java.util.Properties;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;


/**
 * 生产消息
 */
public class KafkaProducerTest implements Runnable {
 
    private final KafkaProducer<String, String> producer;
    private final String topic;
    private String clientid;
    public KafkaProducerTest(String topicName,String clientid) {
        Properties props = new Properties();
        props.put("bootstrap.servers", "10.10.95.252:33993,10.10.95.252:33992,10.10.95.252:33991");
        props.put("acks", "all");
        props.put("retries", 0);
        props.put("batch.size", 16384);
        props.put("key.serializer", StringSerializer.class.getName());
        props.put("value.serializer", StringSerializer.class.getName());
        this.producer = new KafkaProducer<String, String>(props);
        this.topic = topicName;
        this.clientid = clientid;
    }
 
    @Override
    public void run() {
        int messageNo = 1;
        try {
            for(;;) {
                String messageStr= "你好，这是第"+messageNo+"条数据 clientid=" + clientid;
                producer.send(new ProducerRecord<String, String>(topic, "Message", messageStr));
                //生产了100条就打印
                if(messageNo%100==0){
                    System.out.println("发送给topic:"+topic+"的信息:" + messageStr);
                }
                //生产1000条就退出
                if(messageNo == 1000000000){
                    System.out.println("成功发送了"+messageNo+"条");
                    break;
                }
                messageNo++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            producer.close();
        }
    }
 
    public static void main(String args[]) {
        KafkaProducerTest test1 = new KafkaProducerTest("logstash-08-04", "clientid1");
        Thread thread1 = new Thread(test1);
        thread1.start();
    }
}