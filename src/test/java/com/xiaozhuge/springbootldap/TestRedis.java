package com.xiaozhuge.springbootldap;

import redis.clients.jedis.Jedis;

/**
 * @author liyinlong
 * @since 2023/1/10 1:49 下午
 */
public class TestRedis {

    public static void main(String[] args) throws InterruptedException {
        Jedis jedis = new Jedis("10.10.101.140",31369);
        jedis.auth("Ab123456");//如果需要密码

        for (int i = 0; i < 100; i++) {
            try {
                jedis.set("test", "valuetest" + i);
                System.out.println(jedis.get("test"));
            } catch (Exception e) {
                e.printStackTrace();
            }
            Thread.sleep(1000);
        }

    }

}
