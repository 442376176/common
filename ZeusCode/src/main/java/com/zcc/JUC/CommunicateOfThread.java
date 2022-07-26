package com.zcc.JUC;

import org.junit.Test;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @ProjectName: ORCode
 * @ClassName: com.zcc.JUC
 * @author: zcc
 * @date: 2022/3/1 17:32
 * @version:
 * @Describe:
 */
public class CommunicateOfThread {

    public static void main(String[] args) {
        A a = new A();
        new Thread(() -> {
            try {
                for (int i = 0; i < 20; i++) {
                    a.increment();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "aa").start();
        new Thread(() -> {
            try {
                for (int i = 0; i < 20; i++) {
                    a.subtract();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "bb").start();
        new Thread(() -> {
            try {
                for (int i = 0; i < 20; i++) {
                    a.increment();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "cc").start();
        new Thread(() -> {
            try {
                for (int i = 0; i < 20; i++) {
                    a.subtract();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "dd").start();
    }

    @Test
    public void testLock() {
        B b = new B();
        new Thread(() -> {
            try {
                for (int i = 0; i < 20; i++) {
                    b.increment();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "aa").start();
        new Thread(() -> {
            try {
                for (int i = 0; i < 20; i++) {
                    b.subtract();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "bb").start();
        new Thread(() -> {
            try {
                for (int i = 0; i < 20; i++) {
                    b.increment();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "cc").start();
        new Thread(() -> {
            try {
                for (int i = 0; i < 20; i++) {
                    b.subtract();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "dd").start();
    }
}

class A {
    private int count = 0;

    synchronized void increment() throws InterruptedException {
        while (count != 0) {
            this.wait(); // 等待
        }
        count++; // 操作
        System.out.println(Thread.currentThread().getName() + "::" + count);
        this.notifyAll(); // 通知
    }

    synchronized void subtract() throws InterruptedException {
        while (count != 1) {
            this.wait();
        }
        count--;
        System.out.println(Thread.currentThread().getName() + "::" + count);
        this.notifyAll();
    }
}

class B {
    private int count = 0;
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    void increment() throws InterruptedException {
        // 上锁
        lock.lock();
        try {
            while (count != 0) {
                // 等待
                condition.await();
            }
            count++; // 操作
            System.out.println(Thread.currentThread().getName() + "::" + count);
            condition.signalAll();
        } finally {
            // 解锁
            lock.unlock();
        }
    }

    void subtract() throws InterruptedException {
        lock.lock();
        try {
            while (count != 1) {
                condition.await();
            }
            count--; // 操作
            System.out.println(Thread.currentThread().getName() + "::" + count);
            condition.signalAll();
        } finally {
            lock.unlock();
        }

    }
}