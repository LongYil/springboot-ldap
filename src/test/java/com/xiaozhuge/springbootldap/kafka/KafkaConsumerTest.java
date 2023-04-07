package com.xiaozhuge.springbootldap.kafka;
 
import java.util.Arrays;
import java.util.Properties;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;
 
 
public class KafkaConsumerTest implements Runnable {
 
    private final KafkaConsumer<String, String> consumer;
    private ConsumerRecords<String, String> msgList;
    private final String topic;
    private String clientid;
    private static final String GROUPID = "groupA";
 
    public KafkaConsumerTest(String topicName,String clientid) {
 
        Properties props = new Properties();
        props.put("bootstrap.servers", "10.10.102.211:30004,10.10.102.211:30005,10.10.102.211:30006");
        props.put("group.id", GROUPID);
        props.put("enable.auto.commit", "true");
        props.put("auto.commit.interval.ms", "1000");
        props.put("session.timeout.ms", "30000");
        props.put("auto.offset.reset", "earliest");
        props.put("key.deserializer", StringDeserializer.class.getName());
        props.put("value.deserializer", StringDeserializer.class.getName());
        this.consumer = new KafkaConsumer<String, String>(props);
        this.topic = topicName;
        this.consumer.subscribe(Arrays.asList(topic));
        this.clientid = clientid;
    }
 
    @Override
    public void run() {
        int messageNo = 1;
        System.out.println("---------开始消费---------");
        try {
            for (;;) {
                    msgList = consumer.poll(1000);
                    if(null!=msgList&&msgList.count()>0){
                    for (ConsumerRecord<String, String> record : msgList) {
                        //消费100条就打印 ,但打印的数据不一定是这个规律的
                        if(messageNo%100==0){
                            System.out.println(messageNo+"=======成功消费：receive: key = " + record.key() + ", value = " + record.value()+" offset==="+record.offset());
                        }
                        //当消费了1000条就退出
                        if(messageNo == 1000){
                            break;
                        }
                        messageNo++;
                    }
                }else{
                    Thread.sleep(1000);
                }
            }        
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            consumer.close();
        }
    }
 
    public static void main(String args[]) {
        KafkaConsumerTest test1 = new KafkaConsumerTest("logstash-08-04", "clientid1");
        Thread thread1 = new Thread(test1);
        thread1.start();
    }
}