package com.xiaozhuge.springbootldap.kafka;

import redis.clients.jedis.*;

import java.util.HashSet;
import java.util.Set;

public class JedisSentinelExample {

    public static void main(String[] args) {

        // Redis Sentinel 的主节点名称
        String masterName = "mymaster";

        // Redis Sentinel 的地址和端口
        Set<String> sentinels = new HashSet<>();
        sentinels.add("10.10.101.143:26379");


        // 使用 JedisSentinelPool 创建连接池
        JedisSentinelPool sentinelPool = new JedisSentinelPool(masterName, sentinels,  "Ab@123456");

        // 从连接池中获取 Jedis 连接
        Jedis jedis = sentinelPool.getResource();

        try {
            // 使用 Jedis 进行操作
            jedis.set("key", "value");
            String result = jedis.get("key");
            System.out.println("Result: " + result);
        } finally {
            // 释放 Jedis 连接回连接池
            jedis.close();
        }

        // 关闭连接池
        sentinelPool.close();
    }
}
