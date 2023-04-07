package com.xiaozhuge.springbootldap;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Handler;

/**
 * @author liyinlong
 * @since 2022/12/14 3:48 下午
 */
public class DemoW {

    public static void main(String[] args) {

        Map<String,String> map =  new HashMap<>();

        map.put("master","d");
        map.put("sync_slave","c");


        map.forEach((k,v)->{
            System.out.println(k);
        });

    }

}
