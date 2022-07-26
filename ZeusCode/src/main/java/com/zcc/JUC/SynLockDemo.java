package com.zcc.JUC;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @ProjectName: ORCode
 * @ClassName: com.zcc.JUC
 * @author: zcc
 * @date: 2022/3/4 9:22
 * @version:
 * @Describe: 可重入锁（递归锁）
 */
public class SynLockDemo {


    public synchronized void add(){
        add();
    }

    public static void main(String[] args) {
        // synchronized

        SynLockDemo synLockDemo = new SynLockDemo();
//        synLockDemo.add();

//        Object obj = new Object();
//        new Thread(() -> {
//            synchronized (obj) {
//                System.out.println(Thread.currentThread().getName() + "    外层");
//
//                synchronized (obj) {
//                    System.out.println(Thread.currentThread().getName() + "    中层");
//
//                    synchronized (obj) {
//                        System.out.println(Thread.currentThread().getName() + "    内层");
//                    }
//                }
//            }
//        }, "aa").start();

        // lock
        Lock lock = new ReentrantLock();

        new Thread(()->{
            lock.lock();
            try {
                System.out.println(Thread.currentThread().getName() + "    外层");
                lock.lock();
                try{
                    System.out.println(Thread.currentThread().getName() + "    中层");
                    lock.lock();
                    try{
                        System.out.println(Thread.currentThread().getName() + "    内层");
                        lock.lock();
                    }finally {
//                        lock.unlock();
                    }
                }finally {
//                    lock.unlock();
                }
            }finally {
                lock.unlock();
            }
        },"aa").start();
    }

}
