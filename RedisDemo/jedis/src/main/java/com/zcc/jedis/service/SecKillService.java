package com.zcc.jedis.service;

import com.zcc.jedis.utils.JedisPoolUtil;
import org.apache.commons.lang3.StringUtils;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.Transaction;

import java.io.IOException;
import java.util.List;

public class SecKillService {

    public static boolean doSecKill(String userId, String prodid) throws IOException {
        // 非空判断
        if (StringUtils.isNotBlank(userId) && StringUtils.isNotBlank(prodid)) {
            // 连接redis
//            Jedis jedis = new Jedis("47.94.231.7", 6379);
//
            JedisPool jedisPoolInstance = JedisPoolUtil.getJedisPoolInstance();
            Jedis jedis = jedisPoolInstance.getResource();
            jedis.auth("123456");
            // 对商品数量进行监控
            String goodCount = "sk" + prodid + "qt";
            String successUserId = "sk" + prodid + ":user";
            // 获取库存  当库存为null时秒杀还未开始
            String s = jedis.get(goodCount);
            if (s == null) {
                System.out.println("秒杀未开始，请等待");
                jedis.close();
                return false;
            }
            // 判断用户是否重复秒杀操作
            if (jedis.sismember(successUserId, userId)) {
                System.out.println("已秒杀成功，请误重复操作");
                jedis.close();
                return false;
            }
            // 当库存剩余商品数量为0即秒杀结束
            if (Integer.parseInt(s) <= 0) {
                System.out.println("秒杀已结束，谢谢参与");
                jedis.close();
                return false;
            } else {
                // 监视商品数量
                jedis.watch(goodCount);
                // 使用事务
                Transaction multi = jedis.multi();
                // 组队操作
                multi.decr(goodCount);
                multi.sadd(successUserId, userId);
                // 执行命令队列
                List<Object> exec = multi.exec();
                if (null==exec || 0==exec.size()) {
                    System.out.println("秒杀失败");
                    jedis.close();
                    return false;
                }
                System.out.println("秒杀成功，谢谢参与");
                jedis.close();
                return true;
            }
            // 秒杀操作
            // 库存-1
            // 添加成功用户至秒杀成功清单
        }
        return true;
    }
}
