package com.zcc.JUC;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @ProjectName: ORCode
 * @ClassName: com.zcc.JUC
 * @author: zcc
 * @date: 2022/3/4 13:39
 * @version:
 * @Describe:
 */
public class CountDownLatchDemo {
    public static AtomicInteger count = new AtomicInteger(0);

    public static void main(String[] args) throws InterruptedException {
        // 创建CountDownLatch对象，设置初始值
        CountDownLatch countDownLatch = new CountDownLatch(6);

        for (int i = 0; i < 6; i++) {
            int finalI = i;
            new Thread(() -> {
//                count.compareAndSet(finalI-1 , finalI);
                countDownLatch.countDown();
                System.out.println(Thread.currentThread().getName() + "离开了教室" + countDownLatch.getCount());
//                System.out.println(Thread.currentThread().getName() + "离开了教室"+count.get());
            }, i + "").start();
        }


        countDownLatch.await();
        System.out.println("关门");

    }
}

class Student {
    public void leave() {
        System.out.println(Thread.currentThread().getName() + "离开了教室");
    }
}
