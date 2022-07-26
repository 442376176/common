package com.zcc.JUC;

import java.util.concurrent.TimeUnit;

/**
 * @ProjectName: ORCode
 * @ClassName: com.zcc.JUC
 * @author: zcc
 * @date: 2022/3/4 9:59
 * @version:
 * @Describe:
 */
public class DeadlockDemo {

    public static void main(String[] args) {
        Object a = new Object();
        Object b = new Object();

        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (a) {
                System.out.println(Thread.currentThread().getName() + "获取锁A");
                synchronized (b) {
                    System.out.println(Thread.currentThread().getName() + "占有锁A，试图获取锁B");
                }
            }

        }, "1").start();

        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (b) {
                System.out.println(Thread.currentThread().getName() + "获取锁B");
                synchronized (a) {
                    System.out.println(Thread.currentThread().getName() + "占有锁B，试图获取锁A");
                }
            }

        }, "2").start();

    }
}
