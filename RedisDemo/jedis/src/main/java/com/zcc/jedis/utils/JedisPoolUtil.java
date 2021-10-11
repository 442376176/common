package com.zcc.jedis.utils;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class JedisPoolUtil {
    private static volatile JedisPool jedisPool = null;

    private JedisPoolUtil() {
    }

    // 连接池，用于解决连接超时问题
    public static JedisPool getJedisPoolInstance() {
        if (null == jedisPool) {
            synchronized (JedisPoolConfig.class) {
                if (null == jedisPool) {
                    JedisPoolConfig poolConfig = new JedisPoolConfig();
                    poolConfig.setMaxTotal(300); // 最大连接数
                    poolConfig.setMaxIdle(100); // 连接池中的最大空闲连接
                    poolConfig.setMaxWaitMillis(10000); // 最大等待时间
                    poolConfig.setBlockWhenExhausted(true);
                    poolConfig.setTestOnBorrow(false);

                    jedisPool = new JedisPool(poolConfig, "47.94.231.7", 6379, 60000);

                }
            }
        }
        return jedisPool;
    }

    public static void release(Jedis jedis) {
        if (null != jedis) {
            jedis.close();
        }
    }
}
