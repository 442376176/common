package com.zcc.JUC;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @ProjectName: ORCode
 * @ClassName: com.zcc.JUC
 * @author: zcc
 * @date: 2022/3/2 10:21
 * @version:
 * @Describe:线程间定制化通信
 */
public class CommunicateOfThreadByCustomized {
    public static void main(String[] args) {
        ShareResource shareResource = new ShareResource();
        for (int i = 1; i <= 10; i++) {
            int finalI = i;
            new Thread(()->{
                shareResource.print5(finalI);
            },"aa").start();
            new Thread(()->{
                shareResource.print10(finalI);
            },"bb").start();
            new Thread(()->{
                shareResource.print15(finalI);
            },"cc").start();
        }
    }
}

class ShareResource {
    private int index = 1;
    private Lock lock = new ReentrantLock();

    private Condition c1 = lock.newCondition();
    private Condition c2 = lock.newCondition();
    private Condition c3 = lock.newCondition();

    /**
     * 打印五次
     *
     * @param loop 循环第几次
     */
    public void print5(int loop) {
        lock.lock();
        try {
            // 判断
            while (index != 1) {
                c1.await();
            }
            for (int i = 0; i < 5; i++) {
                System.out.println(Thread.currentThread().getName() + "::" + index + "第"+ loop+"波" );
            }
            // 修改标志位
            index++;
            // 通知
            c2.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    /**
     * 打印五次
     *
     * @param loop 循环第几次
     */
    public void print10(int loop) {
        lock.lock();
        try {
            // 判断
            while (index != 2) {
                c2.await();
            }
            for (int i = 0; i < 10; i++) {
                System.out.println(Thread.currentThread().getName() + "::" + index + "第"+ loop+"波" );

            }
            // 修改标志位
            index++;
            // 通知
            c3.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    /**
     * 打印十五次
     *
     * @param loop 循环第几次
     */
    public void print15(int loop) {
        lock.lock();
        try {
            // 判断
            while (index != 3) {
                c3.await();
            }
            for (int i = 0; i < 15; i++) {
                System.out.println(Thread.currentThread().getName() + "::" + index + "第"+ loop+"波" );

            }
            // 修改标志位
            index = 1;
            // 通知
            c1.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

}