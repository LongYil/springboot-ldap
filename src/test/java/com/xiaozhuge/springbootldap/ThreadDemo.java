package com.xiaozhuge.springbootldap;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author liyinlong
 * @since 2022/6/27 2:25 下午
 */
public class ThreadDemo {

    public static void main(String[] args) throws InterruptedException {

        ExecutorService executorService = Executors.newFixedThreadPool(5);

        long start = System.currentTimeMillis();
        List<Integer> list = new ArrayList<>();
        for (int i=0;i< 5;i++){
            list.add(new Random().nextInt(10));
        }
        CountDownLatch latch = new CountDownLatch(list.size());
        for (int i = 0; i < list.size(); i++) {
            int finalI = i;
            executorService.submit(()->{
                System.out.println("开始执行任务："+ finalI);
                System.out.println("预计用时："+ list.get(finalI));
                try {
                    Thread.sleep(list.get(finalI) * 1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("任务"+finalI+"执行完成");
                latch.countDown();
            });
        }
        latch.await();
        executorService.shutdown();
        long end = System.currentTimeMillis();
        long used = (end - start)/1000;
        System.out.println("全部执行完成,用时：" + used);
    }
}
