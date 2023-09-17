package com.xiaozhuge.springbootldap;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author liyinlong
 * @since 2022/6/28 3:57 下午
 */
public class DemoUser {

    public static void main(String[] args) throws BrokenBarrierException, InterruptedException {

        CyclicBarrier cyclicBarrier = new CyclicBarrier(6);

        ExecutorService executorService = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 5; i++) {
            executorService.submit(()->{
                long id = Thread.currentThread().getId();
                System.out.println("开始执行" + id);
                try {
                    Thread.sleep(500);
                    cyclicBarrier.await();
                } catch (InterruptedException | BrokenBarrierException e) {
                    e.printStackTrace();
                }
                System.out.println("执行完成" + id);
            });
        }

        cyclicBarrier.await();
        System.out.println("执行完成");

    }
}
