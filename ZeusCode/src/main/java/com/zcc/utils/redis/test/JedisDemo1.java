package com.zcc.utils.redis.test;

import com.zcc.utils.RandomUtil;
import org.junit.Test;
import redis.clients.jedis.Jedis;

import java.util.List;
import java.util.Set;

/**
 * @author zcc
 * @version 1.0
 * @date 2021/12/8 16:23
 */
public class JedisDemo1 {

    public Jedis getJedis(){
        return new Jedis("127.0.0.1", 6379);
    }
    public static void main(String[] args) {
        // 創建對象
        Jedis jedis = new Jedis("127.0.0.1", 6379);
        // 測試
        System.out.println(jedis.ping());
    }
    // 操作key
    @Test
    public void demo1(){
        Jedis jedis = getJedis();
        Set<String> keys = jedis.keys("*");
        keys.forEach(item-> System.out.println(item));
        System.out.println(jedis.get("str"));
    }

    @Test
    public void demo2(){
        Jedis jedis = getJedis();
        jedis.set("str","1231231");

        System.out.println(jedis.get("str"));
        jedis.expire("str",10);
        jedis.mset("k1","v1","k2","v2");
        List<String> mget = jedis.mget("k1", "k2");
        mget.forEach(item-> System.out.println(item));
        RandomUtil.getRandomNumber();
    }

    @Test
    public void demo3(){
        Jedis jedis = getJedis();
//        Map<String, GeoCoordinate> map = new HashMap<>();
//        GeoCoordinate 上海 = new GeoCoordinate(121.47,31.23);
//        GeoCoordinate 重慶 = new GeoCoordinate(106.50,29.53);
//        GeoCoordinate 深圳 = new GeoCoordinate(114.05,22.52);
//        GeoCoordinate 北京 = new GeoCoordinate(116.38,39.90);
//        map.put("上海",上海);
//        map.put("重慶",重慶);
//        map.put("深圳",深圳);
//        map.put("北京",北京);

//       jedis.geoadd("customerGeo",121.47,31.23,"上海");
//        jedis.geoadd("customerGeo",106.50,29.53,"重慶");
//        jedis.geoadd("customerGeo",114.05,22.52,"深圳");
//        jedis.geoadd("customerGeo",116.38,39.90,"北京");
        System.out.println(jedis.geopos("customerGeo", "上海"));

    }
}
