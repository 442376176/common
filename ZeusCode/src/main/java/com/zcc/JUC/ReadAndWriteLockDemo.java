package com.zcc.JUC;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @ProjectName: ORCode
 * @ClassName: com.zcc.JUC
 * @author: zcc
 * @date: 2022/3/7 9:14
 * @version:
 * @Describe: 读写锁演示
 */
class MyCatch {
    public volatile Map<String, Object> map = new HashMap<>();
    // 创建读写锁对象
    private ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    public void put(String key, Object value) {
        readWriteLock.writeLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + "  正在写操作" + key);

            // 暂停
            TimeUnit.MICROSECONDS.sleep(300);
            map.put(key, value);

            System.out.println(Thread.currentThread().getName() + "已经写完了" + key);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            readWriteLock.writeLock().unlock();
        }
    }

    public Object get(String key){
        readWriteLock.readLock().lock();
        Object obj = null;
        try {

            System.out.println(Thread.currentThread().getName() + "正在获取数据" + key);

            // 暂停
            TimeUnit.MICROSECONDS.sleep(300);
            obj = map.get(key);

            System.out.println(Thread.currentThread().getName() + "获取到了数据" + key + "::" + obj);


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            readWriteLock.readLock().unlock();
        }
        return obj;
    }

}

public class ReadAndWriteLockDemo {
    /**
     * 未加锁
     *
     * @param args
     */
    public static void main(String[] args) throws InterruptedException {
        MyCatch myCatch = new MyCatch();
        // 创建线程放数据
        for (int i = 1; i < 6; i++) {
            final int m = i;
            new Thread(() -> {
                try {
                    myCatch.put(m + "", m);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }, String.valueOf(i)).start();
        }
//        TimeUnit.SECONDS.sleep(2);
        // 创建线程取数据
        for (int i = 1; i < 6; i++) {
            final int m = i;
            new Thread(() -> {
                try {
                    myCatch.get(m + "");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }, String.valueOf(i)).start();
        }
    }

    @Test
    public void test() {

    }
}
