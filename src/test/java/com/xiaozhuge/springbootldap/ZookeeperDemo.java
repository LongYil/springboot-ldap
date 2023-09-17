package com.xiaozhuge.springbootldap;

import java.util.List;

public class ZookeeperDemo {
 
    public static void main(String[] args) throws Exception {
        BaseZookeeper zookeeper = new BaseZookeeper();
        zookeeper.connectZookeeper("10.10.102.52:30851");

        for (int i=0;i < 1000000; i++){
            zookeeper.createNode("/ikafka9"+i, "abc");
        }

        List<String> children = zookeeper.getChildren("/");
        System.out.println(children);
    }
 
}