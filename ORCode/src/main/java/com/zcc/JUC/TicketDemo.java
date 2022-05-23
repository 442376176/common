package com.zcc.JUC;


import org.junit.Test;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @ProjectName: ORCode
 * @ClassName: com.zcc.JUC
 * @author: zcc
 * @date: 2022/3/1 16:53
 * @version:
 * @Describe:
 */
public class TicketDemo {

    public static void main(String[] args) {
        TicketA ticket = new TicketA();

        new Thread(() -> {
            for (int i = 0; i < 20; i++) {
                ticket.subtract();
            }
        }, "1").start();
        new Thread(() -> {
            for (int i = 0; i < 20; i++) {
                ticket.subtract();
            }
        }, "2").start();
        new Thread(() -> {
            for (int i = 0; i < 20; i++) {
                ticket.subtract();
            }
        }, "3").start();

    }

    @Test
    public void testLock() {
        TicketB ticket = new TicketB();

        new Thread(() -> {
            for (int i = 0; i < 20; i++) {
                ticket.subtract();
            }
        }, "1").start();
        new Thread(() -> {
            for (int i = 0; i < 20; i++) {
                ticket.subtract();
            }
        }, "2").start();
        new Thread(() -> {
            for (int i = 0; i < 20; i++) {
                ticket.subtract();
            }
        }, "3").start();
    }

}

class TicketA {
    private int count = 30;

    /**
     * 买票
     */
    public synchronized void subtract() {
        if (count == 0) {
            System.out.println(Thread.currentThread().getName() + "票卖完了");
            return;
        } else {
            count--;
            System.out.println(Thread.currentThread().getName() + "卖出了1张票");
        }
    }
}

class TicketB {
    private int count = 30;


    private final Lock lock = new ReentrantLock(true);

    /**
     * 买票
     */
    public void subtract() {
        lock.lock();
        try {
            if (count == 0) {
                System.out.println(Thread.currentThread().getName() + "票卖完了");
                return;
            } else {
                count--;
                System.out.println(Thread.currentThread().getName() + "卖出了1张票");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }


    }
}
