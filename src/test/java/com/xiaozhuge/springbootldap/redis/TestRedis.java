package com.xiaozhuge.springbootldap.redis;

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
        Thread.sleep(2000);
        for (int i = 0; i < 1000000; i++) {
            jedis.set("hc_lyl_test" + i, "a");
            System.out.println("hc_lyl_test" + i + ":" + jedis.get("hc_lyl_test" + i));
            Thread.sleep(1000);
        }
    }

    private static Jedis connectRedis() throws InterruptedException {
        Jedis jedis = null;
        boolean success = false;
        for (; ; ) {
            // 10.162.200.12:31810
            try {
                jedis = new Jedis("10.162.200.12", 31810);
                jedis.auth("9wTjwyras1");//如果需要密码
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
