package com.xiaozhuge.springbootldap;

import java.util.List;

public class ZookeeperDemo {
 
    public static void main(String[] args) throws Exception {
        BaseZookeeper zookeeper = new BaseZookeeper();
        zookeeper.connectZookeeper("10.10.101.140:31217");

        zookeeper.createNode("/ikafka7", "abc");

        List<String> children = zookeeper.getChildren("/");
        System.out.println(children);

    }
 
}