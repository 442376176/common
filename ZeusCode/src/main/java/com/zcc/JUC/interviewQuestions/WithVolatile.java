package com.zcc.JUC.interviewQuestions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @ProjectName: ORCode
 * @ClassName: com.zcc.JUC.interviewQuestions
 * @author: zcc
 * @date: 2022/5/31 11:03
 * @version:
 * @Describe:
 */
public class WithVolatile {
    volatile List list = Collections.synchronizedList(new ArrayList<>());

    public void add(Object o) {
        list.add(o);
    }

    public int size() {
        return list.size();
    }

    /* 1.有问题 不睡就停不了
    public static void main(String[] args){
        WithVolatile t = new WithVolatile();
        new Thread(()->{
            for(int i = 0; i < 10; i++){
                t.add(new Object());
                System.out.println("add "+i);
                SleepHelper.sleepNano(1);
            }
        },"add1").start();
        new Thread(()->{
            while(true){
                if (t.size() == 5){
                    break;
                }
            }
            System.out.println("结束");
        },"size").start();
    }*/
    static WithVolatile t = new WithVolatile();
    static CountDownLatch downLatch = new CountDownLatch(1);
    static CountDownLatch downLatch1 = new CountDownLatch(1);
    static Lock lock = new ReentrantLock();

    /*wait notify*/
//    public static void main(String[] args) {
//
//        Object o = new ArrayList<>();
//        new Thread(() -> {
//            System.out.println(Thread.currentThread().getName() + "获取锁");
//            synchronized (o) {
//                System.out.println(Thread.currentThread().getName() + "启动");
//                for (int i = 0; i < 10; i++) {
//                    t.add(new Object());
//                    System.out.println("add " + i);
//                    if (i == 4) {
//                        o.notify();
//                        try {
//                            o.wait();
//                        } catch (InterruptedException exception) {
//                            exception.printStackTrace();
//                        }
//                    }
////                    SleepHelper.sleep(1);
//                }
//                o.notify();
//            }
//
//        }, "add1").start();
//
//        new Thread(() -> {
//            System.out.println(Thread.currentThread().getName() + "启动");
//            synchronized (o) {
//                System.out.println(Thread.currentThread().getName() + "获取锁");
//                if (t.size() != 5) {
//                    o.notify();
//                    try {
//                        o.wait();
////                        SleepHelper.sleep(1);
//                    } catch (InterruptedException exception) {
//                        exception.printStackTrace();
//                    }
//                }
//                System.out.println("结束");
//                o.notify();
//            }
//        }, "size").start();
//    }
    /*countDownLatch*/
//public static void main(String[] args) {
//    new Thread(()->{
//
//            for(int i = 0; i < 10; i++){
//                t.add(new Object());
//                System.out.println("add "+i);
//            if (i==4){
//                downLatch.countDown();
//                try {
//                    downLatch1.await();
//                } catch (InterruptedException exception) {
//                    exception.printStackTrace();
//                }
//            }
////            SleepHelper.sleepNano(1);
//
//        }
//
//
//    },"add1").start();
//    new Thread(()->{
//            try {
//                downLatch.await();
//            } catch (InterruptedException exception) {
//                exception.printStackTrace();
//            }
//            System.out.println("结束");
//            downLatch1.countDown();
//
//
//    },"size").start();
//}
    /*LockSupport*/
    static Thread thread, thread1, thread2;

    public static void main(String[] args) {


        thread = new Thread(() -> {
                    for (int i = 0; i < 10; i++) {
                        t.add(new Object());
                        System.out.println("add " + i);
                        if (t.size() == 5) {
                            LockSupport.unpark(thread1);
                            LockSupport.park();
                        }
                    }
                }, "add1");
        thread.start();

        thread1 = new Thread(() -> {
            LockSupport.park();
            System.out.println("结束");
            LockSupport.unpark(thread);
        }, "size");
        thread1.start();

    }
}
