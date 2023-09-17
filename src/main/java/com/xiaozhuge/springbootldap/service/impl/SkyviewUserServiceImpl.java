package com.xiaozhuge.springbootldap.service.impl;

import com.xiaozhuge.springbootldap.service.UserService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

/**
 * @author liyinlong
 * @since 2022/6/8 9:26 上午
 */
@Service
@ConditionalOnProperty(value="system.usercenter",havingValue = "skyview")
public class SkyviewUserServiceImpl implements UserService {

    @Override
    public String login() {
        return "skyview";
    }

}
