package com.zcc.java8.date;

import java.time.Instant;
import java.util.Date;

/**
 * @ProjectName: ZeusCode
 * @ClassName: com.zcc.java8.date
 * @author: zcc
 * @date: 2022/9/23 15:53
 * @version:
 * @Describe:
 */
public class TestLocalDate {

    // 由于我们的java.time包是基于纳秒计算的,所以我们Instant也可以精确到纳秒级别
    /**
     *
     * Instant 瞬时 时间线上的一个瞬时点
     *  静态属性：
     *  EPOCH 1970-01-01T00：00：00Z时代瞬间。
     *  MAX: 000000000-12-31T23:59:59.999999999Z
     *  MIN：1000000000-01-01T00:00Z
     *·
     *  静态方法：
     *  now()：
     *
         * 我们使用Instant类来调用其中的now()静态方法就可以得到这个Instant类的实例化对象
         * 我们使用now()方法获取的是本初子午线上对应的标准时间
     */
    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        Date from = Date.from(Instant.now());
        System.out.println(from);
    }


}
