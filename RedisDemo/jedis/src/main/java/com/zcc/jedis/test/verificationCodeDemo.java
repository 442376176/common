package com.zcc.jedis.test;

import com.zcc.jedis.utils.ConnectUtil;
import org.apache.commons.lang3.StringUtils;
import redis.clients.jedis.Jedis;

import java.util.Random;

/**
 * 1. 输入手机号，点击发送后随机生成6位数字码，2分钟内有效
 * 2. 输入验证码，点击验证，返回成功或者失败
 * 3. 每个手机号每天只能输入3次
 */
public class verificationCodeDemo {


    public static void main(String[] args) {
        clear();
//        System.out.println(send("15151832108"));
//        System.out.println(verification("843010", "15151832108"));
    }

//    @Test
    public static String send(String phone) {

        Jedis jedis = ConnectUtil.init();
        String s = jedis.get("phone" + phone);
        // 4. 每个手机号每天只能输入三次验证码
        if (StringUtils.isNotBlank(s)) {
            int i = Integer.parseInt(s);
            if (i - 2 >= 0) {
                jedis.close();
                return "超过三次,发送失败";
            } else {
                jedis.set("phone" + phone, Integer.parseInt(s) + 1 + "");
            }
        } else
            jedis.set("phone" + phone, "0");
        // 1. 生成随机6位数字验证码
        Random random = new Random();
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            builder.append(random.nextInt(10));
        }
        // 2. 验证码2分钟有效设置过期时间
        jedis.setex("varificationCode" + phone, 120, builder.toString());
        return "发送成功";
    }

//    @Test
    public static String verification(String code, String phone) {
        Jedis jedis = ConnectUtil.init();
        String result = "";
        // 3. 判断验证码是否过期，验证码是否一致
        if (StringUtils.isNotBlank(jedis.get("varificationCode" + phone))) {
            if (code.equals(jedis.get("varificationCode" + phone))) {
                return "验证成功";
            } else {
                return "验证码错误";
            }
        } else {
            return "验证码已过期，请重新获取";
        }
    }
    public  static void clear(){
        ConnectUtil.init().flushDB();
    }

}
