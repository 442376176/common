package com.zcc.utils.redis;

import com.googlecode.javaewah.EWAHCompressedBitmap;
import org.roaringbitmap.RoaringBitmap;
import org.springframework.util.DigestUtils;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;

import java.io.*;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Base64;
import java.util.function.Consumer;

/**
 * @author zcc
 * @version 1.0
 * @date 2021/12/8 16:01
 */
public class RedisUtils {

    //三种map的初始研究
    public static void main(String[] args) throws IOException {
        bitMap();
        javaEWAH();
        roadingBitMap();
    }

    /**
     * 普通bitMap使用
     */
    public static void bitMap(){
        ArrayList<String> values = new ArrayList<>();
        for (int i = 0; i < 100000; i++) {
            values.add(getMD5(String.valueOf(i)));
        }

        Jedis jedis = new Jedis("127.0.0.1",6379);
//        jedis.auth("971005");
        try {
            int maxValue=0;
            Pipeline pipelined = jedis.pipelined();
            for (String arg : values) {
                int offset = arg.hashCode() & 0x7FFFFFFF;
                maxValue=offset>maxValue?offset:maxValue;
                pipelined.setbit("audience_id", offset, true);
            }
            System.err.println(maxValue);
            pipelined.sync();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (jedis != null) jedis.close();
        }
        System.out.println("成功 bitmap"); System.out.println("成功");
    }

    /**
     * maven依赖
     *    <dependency>
     *       <groupId>org.roaringbitmap</groupId>
     *       <artifactId>RoaringBitmap</artifactId>
     *       <version>0.8.1</version>
     *    </dependency>
     * roadingBitMap咆哮位图的使用
     *   map之间运算 https://www.liangzl.com/get-article-detail-148556.html
     * @throws IOException
     */
    public static void roadingBitMap() throws IOException {
        int size=1000;
        int[] offsets = new int[size];
        for (int i = 0; i < size; i++) {
            int offset = getMD5(String.valueOf(i)).hashCode() & 0x7FFFFFFF;
            offsets[i] = offset;
        }

        RoaringBitmap roaringBitmap = new RoaringBitmap();
        roaringBitmap.add(offsets);

        // 遍历输出
        roaringBitmap.forEach((Consumer<? super Integer>) i -> System.out.println(i));

        int roaringMapSize = roaringBitmap.serializedSizeInBytes();
        ByteBuffer buffer = ByteBuffer.allocate(roaringMapSize);
        roaringBitmap.serialize(buffer);
        // 将RoaringBitmap的数据转成字节数组,这样就可以直接存入数据库了,数据库字段类型BLOB
        byte[] bitmapData = buffer.array();

        Jedis jedis = new Jedis("localhost",6379);
        jedis.auth("root");
        // Base64编码后存入Redis
        String encodeString = Base64.getEncoder().encodeToString(bitmapData);
        jedis.set("roaringMap", encodeString);

        String roaringMapStr  = jedis.get("roaringMap");
        //反序列化
        byte[] decode = Base64.getDecoder().decode(roaringMapStr);

        RoaringBitmap deseRoaringBitmap = new RoaringBitmap();
        deseRoaringBitmap.deserialize(new DataInputStream(new ByteArrayInputStream(decode)));
        System.out.println("roaringMap (recovered) : " + deseRoaringBitmap);

        //map 元素大小
        System.out.println(deseRoaringBitmap.getLongCardinality());

        //向rr中添加1、2、3、1000四个数字
        RoaringBitmap rr1 = RoaringBitmap.bitmapOf(1,2,3,1000);
        RoaringBitmap rr2 = RoaringBitmap.bitmapOf(3,4,5,1000);
        //求交集
        RoaringBitmap intersection = RoaringBitmap.and(rr1, rr2);
        System.out.println(intersection);
    }

    /**
     *  谷歌 javaEWAH bitMap
     *  <dependency>
     *      <groupId>com.googlecode.javaewah</groupId>
     *      <artifactId>JavaEWAH</artifactId>
     *      <version>1.1.6</version>
     *  </dependency>
     * todo 如何动态添加元素
     * EWAHCompressedBitmap计算: https://blog.csdn.net/a5678110/article/details/102048463
     *
     */
    public static void javaEWAH(){
        int[] offsets = new int[100000];
        for (int i = 0; i < 100000; i++) {
            int offset = getMD5(String.valueOf(i)).hashCode() & 0x7FFFFFFF;
            offsets[i] = offset;
        }

        EWAHCompressedBitmap ewahBitmap = EWAHCompressedBitmap.bitmapOf(offsets);

        //序列化与反序列化
        try {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ewahBitmap.serialize(new DataOutputStream(bos));
            byte[] bout = bos.toByteArray();

            Jedis jedis = new Jedis("localhost",6379);
            jedis.auth("root");
            //序列化
            String encodeString = Base64.getEncoder().encodeToString(bout);
            jedis.set("ewahBitmap", encodeString);

            String ewahBitmapStr  = jedis.get("ewahBitmap");
            //反序列化
            byte[] decode = Base64.getDecoder().decode(ewahBitmapStr);
            EWAHCompressedBitmap deseEwahBitmap = new EWAHCompressedBitmap();
            deseEwahBitmap.deserialize(new DataInputStream(new ByteArrayInputStream(decode)));
            System.out.println("ewahBitmap (recovered) : " + deseEwahBitmap);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //盐，用于混交md5
    static final String slat = "&%5123***&&%%$$#@";

    /**
     * spring框架生成md5
     * @param str
     * @return
     */
    public static String getMD5(String str) {
        String base = str +"/"+slat;
        String md5 = DigestUtils.md5DigestAsHex(base.getBytes());
        return md5;
    }
}
