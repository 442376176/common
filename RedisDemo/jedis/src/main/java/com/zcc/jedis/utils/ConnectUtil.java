package com.zcc.jedis.utils;

import redis.clients.jedis.Jedis;

/**
 * @Author zcc
 * @Date 2021/8/6 14:07
 * @Describe
 */
public class ConnectUtil {
    public static Jedis init(){
        Jedis jedis = new Jedis("47.94.231.7",6379);
        jedis.auth("");
        return jedis;
    }
}
