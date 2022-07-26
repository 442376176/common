package com.zcc.JUC.interviewQuestions;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.LinkedTransferQueue;
import java.util.concurrent.TransferQueue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @ProjectName: ORCode
 * @ClassName: com.zcc.JUC.interviewQuestions
 * @author: zcc
 * @date: 2022/5/24 9:32
 * @version:
 * @Describe: 交替打印
 */
public class AlternatePrinting {
    private static char[] num = "123456".toCharArray();
    private static char[] str = "abcdef".toCharArray();
    // 确保打印先后顺序
    private static CountDownLatch countDownLatch = new CountDownLatch(1);
    private static Thread t1, t2;

    static class LockSupportProgramme {
        public static void main(String[] args) {
            t1 = new Thread(() -> {
                for (char c : num) {
                    System.out.println(c);
                    LockSupport.unpark(t2);
                    LockSupport.park(t1);
                }
            }, "t1");

            t2 = new Thread(() -> {
                for (char c : str) {
                    LockSupport.park(t2);
                    System.out.println(c);
                    LockSupport.unpark(t1);
                }
            }, "t1");
            t1.start();
            t2.start();
        }
    }

    static class WaitNotifyProgramme {
        public static void main(String[] args) {
            Object o = new Object();

            new Thread(() -> {
                synchronized (o) {
                    for (char c : num) {
                        countDownLatch.countDown();
                        o.notify();
                        System.out.println(c);
                        try {
                            o.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    o.notify();
                }

            }, "t1").start();

            new Thread(() -> {
                synchronized (o) {
                    for (char c : str) {
                        try {
                            countDownLatch.await();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        o.notify();
                        System.out.println(c);
                        try {
                            o.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                    }
                    o.notify();
                }

            }, "t2").start();
        }
    }

    // TransferQueue 用作交换数据的队列
    static class TransferQueueProgramme {
        private static TransferQueue<Character> transferQueue = new LinkedTransferQueue<>();

        public static void main(String[] args) {
            new Thread(() -> {
                for (char c : num) {
                    countDownLatch.countDown();
                    try {
                        transferQueue.transfer(c);
                        System.out.println(transferQueue.take());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
            new Thread(() -> {
                try {
                    countDownLatch.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                for (char c : str) {

                    try {
                        System.out.println(transferQueue.take());
                        transferQueue.transfer(c);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    }
    // 交替输出ABC 10遍
    static class PrintABCConditionProgramme {
        static Lock lock = new ReentrantLock();
        public static void main(String[] args) {
            Condition c1 = lock.newCondition();
            Condition c2 = lock.newCondition();
            Condition c3 = lock.newCondition();

            new Thread(() -> {
                lock.lock();
                countDownLatch.countDown();
                try {
                    for (int i = 0; i < 10; i++) {
                        System.out.println("A");
                        c2.signal();
                        c1.await();
                    }
                    c2.signal();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {

                    lock.unlock();
                }
            }, "aa").start();
            new Thread(() -> {
                lock.lock();
                try {
                    countDownLatch.await();
                    for (int i = 0; i < 10; i++) {
                        System.out.println("B");
                        c3.signal();
                        c2.await();
                    }
                    c3.signal();
                } catch (InterruptedException e) {

                } finally {

                    lock.unlock();
                }
            }, "bb").start();
            new Thread(() -> {
                lock.lock();
                try {
                    countDownLatch.await();

                    for (int i = 0; i < 10; i++) {
                        System.out.println("C");
                        c1.signal();
                        c3.await();
                    }
                    c1.signal();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }, "cc").start();
        }
    }
}
