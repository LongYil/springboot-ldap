package com.xiaozhuge.springbootldap;

import redis.clients.jedis.Jedis;

/**
 * @description traefik vip故障自动切换测试
 * @author  liyinlong
 * @since 2023/8/21 5:02 下午
 */
public class TestRedis {

    private static Jedis jedis;

    public static void main(String[] args) throws InterruptedException {
        jedis = connectRedis();
        for (int i = 0; i < 1000000; i++) {
            try {
                jedis.set("test", "valuetest" + i);
                System.out.println(jedis.get("test"));
            } catch (Exception e){
                e.printStackTrace();
                System.out.println("redis连接异常");
                jedis = connectRedis();
            }
            Thread.sleep(1000);
        }
    }

    private static Jedis connectRedis() throws InterruptedException {
        Jedis jedis = null;
        boolean success = false;
        for (; ; ) {
            // 10.162.200.12:31810
            try {
                jedis = new Jedis("10.10.95.17", 31809);
                jedis.auth("d6Kj7PKaFT");//如果需要密码
                success = true;
                System.out.println("连接redis成功");
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("尝试重连redis...");
            }
            if (success) {
                return jedis;
            }
            Thread.sleep(1000);
        }
    }

}
