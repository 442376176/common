package com.zcc.JUC;

import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * @ProjectName: ORCode
 * @ClassName: com.zcc.thread
 * @author: zcc
 * @date: 2022/3/1 10:40
 * @version:
 * @Describe:ABA问题的解决 AtomicStampedReference
 */
public class ABADemo {

    static AtomicReference<Integer> atomicReference = new AtomicReference<>(100);
    static AtomicStampedReference<Integer> atomicReferences = new AtomicStampedReference<>(100, 1);

    public static void main(String[] args) throws InterruptedException {
        System.out.println("=======以下是ABA问题的产生=========");
        new Thread(() -> {
            atomicReference.compareAndSet(100, 101);
            atomicReference.compareAndSet(101, 100);
        }, "t1").start();
        new Thread(() -> {
            try {
                // 保证线程完成ABA操作
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            atomicReference.compareAndSet(100, 2022);
            System.out.println(Thread.currentThread().getName() + "修改了值" + atomicReference.get());
        }, "t2").start();

        Thread.sleep(2000);
        System.out.println("=======以下是ABA问题的解决=========");
        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "当前版本号" + atomicReferences.getStamp());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            boolean b = atomicReferences.compareAndSet(100, 101, 1, 2);
            if (b) {
                System.out.println(Thread.currentThread().getName() + "修改了值" + atomicReferences.getReference());
            }
            System.out.println("当前版本号" + atomicReferences.getStamp());
            boolean b1 = atomicReferences.compareAndSet(101, 100, 2, 3);
            if (b1) {
                System.out.println(Thread.currentThread().getName() + "修改了值" + atomicReferences.getReference());
            }
        }, "t3").start();

        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "当前版本号" + atomicReferences.getStamp());
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            boolean b = atomicReferences.compareAndSet(100, 2022, 1, 2);
            System.out.println(Thread.currentThread().getName() + "是否修改成功？" + b + "当前实际值为：" + atomicReferences.getReference());
        }, "t4").start();

    }

}
