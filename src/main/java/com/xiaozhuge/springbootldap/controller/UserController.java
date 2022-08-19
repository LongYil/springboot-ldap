package com.xiaozhuge.springbootldap.controller;

import com.xiaozhuge.springbootldap.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.BoundSetOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;


/**
 * @author liyinlong
 * @since 2022/6/8 9:22 上午
 */
@RequestMapping("/user")
@RestController
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private RedisTemplate<String, Serializable> redisTemplate;

    @GetMapping("/login")
    public String login(){

        BoundSetOperations<String, Serializable> user = (BoundSetOperations<String, Serializable>) redisTemplate.boundValueOps("user");
        user.add("hahah");

        return userService.login();
    }

}
