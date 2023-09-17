package com.xiaozhuge.springbootldap.service.impl;

import com.xiaozhuge.springbootldap.service.UserService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

/**
 * @author liyinlong
 * @since 2022/6/8 9:25 上午
 */

@Service
@ConditionalOnProperty(value="system.usercenter",havingValue = "zeus")
public class UserServiceImpl implements UserService {

    @Override
    public String login() {
        return "zeus";
    }

}
