package com.zcc.jedis.test;

import com.zcc.jedis.utils.ConnectUtil;
import com.zcc.jedis.utils.JedisPoolUtil;
import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.*;

public class JedisTest {
//    public static void main(String[] args) {
//        // 创建Jedis对象
//        Jedis jedis = new Jedis("47.94.231.7",6379);
//        jedis.auth("");
//        // 测试
//        String ping = jedis.ping();
//        System.out.println(ping);
//
//
//    }

    @Test
    public void operateString(){ //String
        Jedis init = ConnectUtil.init();
        Set<String> keys = init.keys("*");
        Iterator<String> iterator = keys.iterator();
//        while (iterator.hasNext()){
//            System.out.println(iterator.next());
//        }
        // 添加数据
        init.set("name","zcc"); // 单个String
        init.mset("age","23","sex","man"); // 多个String
        List<String> mget = init.mget("name", "age", "sex");
        for (String s:mget
             ) {
            System.out.println(s);
        }
    }
    @Test
    public void operateList(){ //List
        Jedis init =  ConnectUtil.init();
        Set<String> keys = init.keys("*");
        Iterator<String> iterator = keys.iterator();
//        while (iterator.hasNext()){
//            System.out.println(iterator.next());
//        }
        // 添加数据
       init.lpush("k1","1","2","3"); // 单个
        List<String> k1 = init.lrange("k1", 0, -1);
        for (String s:k1
        ) {
            System.out.println(s);
        }
    }

    @Test
    public void operateSet(){ // Set
        Jedis init = ConnectUtil.init();
        Set<String> keys = init.keys("*");
        Iterator<String> iterator = keys.iterator();
//        while (iterator.hasNext()){
//            System.out.println(iterator.next());
//        }
        // 添加数据
        init.sadd("k2","1","2","3","1"); // 单个
        Set<String> k2 = init.smembers("k2");
        for (String s:k2
        ) {
            System.out.println(s);
        }
    }

    @Test
    public void operateHash(){ // Set
        Jedis init =  ConnectUtil.init();
        Set<String> keys = init.keys("*");
        Iterator<String> iterator = keys.iterator();
//        while (iterator.hasNext()){
//            System.out.println(iterator.next());
//        }
        // 添加数据
        init.hset("k3","name","zcc"); // 单个
        init.hset("k3","age","23");
        Map<String,String> map = new HashMap<String, String>();
        map.put("name","zxx");
        map.put("age","22");
        init.hset("k4",map);
        System.out.println(init.hget("k3", "name"));
        System.out.println(init.hget("k4", "age"));
    }

    @Test
    public void operateZSet(){ // ZSet
        Jedis init =  ConnectUtil.init();
        Set<String> keys = init.keys("*");
        Iterator<String> iterator = keys.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }
        System.out.println("-----------------------------");
        // 添加数据
        init.zadd("rank",500,"java"); // 单个
        init.zadd("rank",400,"php");
        init.zadd("rank",300,"c#");
        init.zadd("rank",600,"python");
//        System.out.println(init.zpopmax("rank"));
        Iterator<String> rank = init.zrange("rank", 0, -1).iterator();
        while (rank.hasNext()){
            System.out.println(rank.next());
        }

    }







        public static void main(String[] args) {
            JedisPool jedisPool = JedisPoolUtil.getJedisPoolInstance();
//            JedisPool jedisPool2 = JedisPoolUtil.getJedisPoolInstance();

//            System.out.println(jedisPool == jedisPool2);
            Jedis jedis = null;
            try {
                jedis = jedisPool.getResource();
                jedis.auth("123456");
                jedis.set("aa","bb");
                System.out.println(jedis.get("aa"));
            } catch (Exception e) {
                e.printStackTrace();
            }finally{
                JedisPoolUtil.release(jedis);
            }
        }






}
