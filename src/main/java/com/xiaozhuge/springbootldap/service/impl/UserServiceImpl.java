package com.xiaozhuge.springbootldap.service.impl;

import com.xiaozhuge.springbootldap.service.UserService;
import org.springframework.stereotype.Service;

/**
 * @author liyinlong
 * @since 2022/6/8 9:25 上午
 */
@Service("UserServiceImpl")
public class UserServiceImpl implements UserService {

    @Override
    public String login() {
        return "zeus";
    }
}
