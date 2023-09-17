package com.xiaozhuge.springbootldap.kafka;

import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.admin.CreateTopicsResult;

import java.util.Collections;
import java.util.Properties;
import java.util.concurrent.ExecutionException;

/**
 * 创建topic
 */
public class KafkaTopicCreator {
    public static void main(String[] args) {
        // 配置Kafka集群的地址
        String bootstrapServers = "10.10.95.252:33993,10.10.95.252:33992,10.10.95.252:33991";

        // 创建Kafka客户端
        Properties properties = new Properties();
        properties.put("bootstrap.servers", bootstrapServers);
        AdminClient adminClient = AdminClient.create(properties);

        // 定义要创建的主题名称
        String topicName = "my-new-topic";

        // 创建NewTopic对象
        NewTopic newTopic = new NewTopic(topicName, 1, (short) 1);

        // 创建主题
        CreateTopicsResult createTopicsResult = adminClient.createTopics(Collections.singletonList(newTopic));

        // 等待主题创建完成
        try {
            createTopicsResult.all().get();
            System.out.println("Topic created successfully: " + topicName);
        } catch (InterruptedException | ExecutionException e) {
            if (e.getCause() instanceof org.apache.kafka.common.errors.TopicExistsException) {
                System.out.println("Topic already exists: " + topicName);
            } else {
                System.err.println("Error creating topic: " + e.getMessage());
            }
        } finally {
            // 关闭Kafka客户端
            adminClient.close();
        }
    }
}
