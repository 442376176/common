package com.zcc;

import org.apache.commons.lang3.StringUtils;
import redis.clients.jedis.Jedis;

import java.io.IOException;

/**
 * @Author zcc
 * @Date 2021/8/12 13:52
 * @Describe
 */
public class SecKillRedis {
    public static void main(String[] args) {
        Jedis jedis = new Jedis("127.0.0.1",6379);
        jedis.auth("123456");
        System.out.println(jedis.ping());
        jedis.close();
    }
    // 秒杀过程

    /**
     *
     * @param userId 用户id
     * @param prodid 商品id
     * @return
     * @throws IOException
     */
    public static boolean doSecKill(String userId, String prodid) throws IOException {
        // 非空判断
        if (StringUtils.isNotBlank(userId)&&StringUtils.isNotBlank(prodid)){
            // 连接redis
            Jedis jedis = new Jedis("47.94.231.7",6379);
            jedis.auth("123456");
            // 对商品数量进行监控
            String goodCount = "sk"+prodid+"qt";
            String successUserId = "sk"+prodid+":user";
            // 获取库存  当库存为null时秒杀还未开始
            String s = jedis.get(goodCount);
            if (s==null){
                System.out.println("秒杀未开始，请等待");
                jedis.close();
                return false;
            }
            // 判断用户是否重复秒杀操作
            if (jedis.sismember(successUserId,userId)){
                System.out.println("已秒杀成功，请误重复操作");
                jedis.close();
                return false;
            }
            // 当库存剩余商品数量为0即秒杀结束
            if (Integer.parseInt(s)<=0){
                System.out.println("秒杀已结束，谢谢参与");
                jedis.close();
                return false;
            } else {
//                jedis.watch(goodCount);
//                jedis.multi();
                jedis.decr(goodCount);
                jedis.sadd(successUserId,userId);
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
