package com.xiaozhuge.springbootldap;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author liyinlong
 * @since 2022/8/19 10:10 上午
 */
public class DateUtils {

    public static void main(String[] args) throws ParseException {

        DateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String datestr = format.format(new Date());
        System.out.println(datestr);
        Date date = format.parse(datestr);
        System.out.println(date);

    }
}
