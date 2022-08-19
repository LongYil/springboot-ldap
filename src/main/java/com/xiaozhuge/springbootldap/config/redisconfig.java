package com.xiaozhuge.springbootldap.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.io.Serializable;
/**
 * @Auth朱定欢
 * 作用：创建连接对象
 * */
@Configuration
public class redisconfig {
    @Bean
    //通过配置类对连接对象配置
    public RedisTemplate<String , Serializable> getRedis(RedisConnectionFactory factory){
        //获得连接对象
        RedisTemplate<String, Serializable> redisTemplate=new RedisTemplate<String, Serializable>();
      //设置连接工厂
       redisTemplate.setConnectionFactory(factory);
       //key进行序列化
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        //规定value的序列化格式
        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer=new Jackson2JsonRedisSerializer(Object.class);
        //value进行序列化
        redisTemplate.setValueSerializer(jackson2JsonRedisSerializer);
        //返回连接对象
        return redisTemplate;
    }
}

