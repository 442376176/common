package com.zcc.case3;

import org.apache.curator.RetryPolicy;
import org.apache.curator.RetrySleeper;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.retry.ExponentialBackoffRetry;

import java.util.concurrent.*;

/**
 * @ProjectName: zookeeper
 * @ClassName: com.zcc.case3
 * @author: zcc
 * @date: 2022/2/23 10:22
 * @version:
 * @Describe:
 */
public class CuratorLockTest {

    private static ExecutorService executorService = new ThreadPoolExecutor(2, 10, 0, TimeUnit.SECONDS, new LinkedBlockingDeque<>());

    public static void main(String[] args) {
        // 创建分布式锁1
        InterProcessMutex lock1 = new InterProcessMutex(getCuratorFramework(), "/locks");

        // 创建分布式锁2
        InterProcessMutex lock2 = new InterProcessMutex(getCuratorFramework(), "/locks");

        executorService.execute(()->{
            try {
                lock1.acquire();
                System.out.println("线程1 获取到锁");
                lock1.acquire();
                System.out.println("线程1 再次获取到锁");

                Thread.sleep(5000);

                lock1.release();
                System.out.println("线程1 释放锁");
                lock1.release();
                System.out.println("线程1 再次释放锁");
            } catch (Exception e) {
                e.printStackTrace();
            }
            return;
        });
        executorService.execute(()->{
            try {
                lock2.acquire();
                System.out.println("线程2 获取到锁");
                lock2.acquire();
                System.out.println("线程2 再次获取到锁");

                Thread.sleep(5000);

                lock2.release();
                System.out.println("线程2 释放锁");

                lock2.release();
                System.out.println("线程2 再次释放锁");
            } catch (Exception e) {
                e.printStackTrace();
            }
            return;
        });
        executorService.shutdown();

    }

    private static CuratorFramework getCuratorFramework() {
        ExponentialBackoffRetry exponentialBackoffRetry = new ExponentialBackoffRetry(3000, 3);
        CuratorFramework client = CuratorFrameworkFactory.builder().connectString("139.9.94.102:2181,139.9.94.102:2182,139.9.94.102:2183")
                .connectionTimeoutMs(2000)
                .sessionTimeoutMs(2000)
                .retryPolicy(exponentialBackoffRetry)
                .build();

        // 启动客户端
        client.start();

        System.out.println("zookeeper 启动成功");

        return client;
    }
}
