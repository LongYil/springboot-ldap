package com.xiaozhuge.springbootldap.controller;

import com.xiaozhuge.springbootldap.service.UserService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


/**
 * @author liyinlong
 * @since 2022/6/8 9:22 上午
 */
@RequestMapping("/user")
@RestController
public class UserController {

    @Qualifier
    @Resource(name=  "${system.usercenter}UserServiceImpl")
    private UserService userService;

    @GetMapping("/login")
    public String login(){
        return userService.login();
    }

}
