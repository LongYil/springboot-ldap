package com.xiaozhuge.springbootldap;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

import java.util.Map;

public class RedisSentinelExample {

    public static void main(String[] args) {
        // 创建 Redisson 配置对象
        Config config = new Config();
        config.useSentinelServers()
              .setMasterName("mymaster") // Sentinel 配置中的主服务器名称
              .addSentinelAddress("redis-sentinel-1:26379", "redis-sentinel-2:26379", "redis-sentinel-3:26379")
              .setPassword("your_password")
        .setCheckSentinelsList(false); // 如果 Redis 设置了密码，请提供密码

        // 创建 Redisson 客户端
        RedissonClient redisson = Redisson.create(config);

        // 现在你可以使用 redisson 对象来执行各种 Redis 操作
        // 例如：获取 Redis Map 对象
        Map<String, String> map = redisson.getMap("myMap");

        // 将数据放入 Redis Map
        map.put("key1", "value1");
        map.put("key2", "value2");

        // 从 Redis Map 获取数据
        String value1 = map.get("key1");
        String value2 = map.get("key2");

        System.out.println("Value1: " + value1);
        System.out.println("Value2: " + value2);

        // 关闭 Redisson 客户端
        redisson.shutdown();
    }
}
