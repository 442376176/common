package com.zcc.test;

import java.util.Calendar;
import java.util.Date;

/**
 * @author zcc
 * @version 1.0
 * @date 2021/9/22 13:16
 */
public class CommonTest {
    public static void main(String[] args) {
//        User user = new User();
//        user.setSex(null);
//        System.out.println(user);
        String s = "2020-01-15";
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        int i = calendar.get(Calendar.DATE);
        System.out.println(i);

    }
}
