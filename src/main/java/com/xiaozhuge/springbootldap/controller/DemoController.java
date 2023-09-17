package com.xiaozhuge.springbootldap.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author liyinlong
 * @since 2023/9/17 2:57 下午
 */
@RequestMapping("/main")
@RestController
public class DemoController {

    @GetMapping("/test")
    private String test(HttpServletRequest request){
        String ipAddress = request.getRemoteAddr();
        System.out.println(ipAddress);
        return ipAddress;
    }


}
