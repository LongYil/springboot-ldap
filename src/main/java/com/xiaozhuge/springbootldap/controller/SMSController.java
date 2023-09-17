package com.xiaozhuge.springbootldap.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @author liyinlong
 * @since 2023/6/12 9:49 上午
 */
@Slf4j
@Controller
@RequestMapping("/sms")
public class SMSController {

    @ResponseBody
    @PostMapping("/sendsms")
    public JSONObject send(@RequestParam(name = "TelList") String TelList,
                       @RequestParam(name = "Content") String Content) {
        log.info("告警信息{}", TelList);
        log.info("告警信息{}", Content);
        return new JSONObject();
    }

}
