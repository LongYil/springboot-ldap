package com.xiaozhuge.springbootldap;

/**
 * @author liyinlong
 * @since 2022/7/26 8:55 下午
 */
public class demo {

    public static void main(String[] args) {

        System.out.println(getPasswordRemindCode(29.9));
    }

    public static void check(Object id){
        if(id instanceof Integer){
            System.out.println("是整型");
        }else{
            System.out.println("不是整型");
        }
    }

    private static Integer getPasswordRemindCode(double passwordUsedDay) {
        double expiredDay = 30d;
        double remindDay = expiredDay / 3;
        double leftDay = expiredDay - passwordUsedDay;
        if (leftDay <= 0) {
            System.out.println("密码已过期");
            return -1;
        }
        if (leftDay < remindDay) {
            System.out.println("该修改密码了");
            return 0;
        }
        return 1;
    }

}
