package com.zcc.JUC.delayQueue;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * @ProjectName: ORCode
 * @ClassName: com.zcc.JUC.delayQueue
 * @author: zcc
 * @date: 2022/6/21 15:59
 * @version:
 * @Describe:
 */
public class MyDelay<T> implements Delayed {


    long delayTime; // 延迟时间
    long expire; // 过期时间
    T data;

    public MyDelay(long delayTime, T t) {
        this.delayTime = delayTime;
        // 过期时间 = 当前时间 + 延迟时间
        this.expire = System.currentTimeMillis() + delayTime;
        data = t;
    }

    public MyDelay(T t) {
        this.delayTime = 3000;
        // 过期时间 = 当前时间 + 延迟时间
        this.expire = System.currentTimeMillis() + delayTime;
        data = t;
    }

    /**
     * 剩余时间 = 到期时间 - 当前时间
     */
    @Override
    public long getDelay(TimeUnit unit) {
        return unit.convert(this.expire - System.currentTimeMillis(), TimeUnit.MILLISECONDS);
    }

    /**
     * 优先级规则：两个任务比较，时间短的优先执行
     */
    @Override
    public int compareTo(Delayed o) {
        long f = this.getDelay(TimeUnit.MILLISECONDS) - o.getDelay(TimeUnit.MILLISECONDS);
        return (int) f;
    }


    @Override
    public String toString() {
        return "delayTime=" + delayTime +
                ", expire=" + expire +
                ", data=" + data;
    }
}
