package com.zcc.JUC;

import java.util.concurrent.CyclicBarrier;

/**
 * @ProjectName: ORCode
 * @ClassName: com.zcc.JUC
 * @author: zcc
 * @date: 2022/3/4 14:18
 * @version:
 * @Describe: 七颗龙珠召唤神龙
 */
public class CyclicBarrierDemo {
    // 创建固定值
    private static final int LZ = 7;

    public static void main(String[] args) {
        // 创建CyclicBarrier

        CyclicBarrier cyclicBarrier = new CyclicBarrier(LZ, () -> {
            System.out.println("*******集齐七颗龙珠召唤神龙");
        });

        // 集齐龙珠过程
        for (int i = 1; i <= 7; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + "星龙被收集到了");

                // 等待
                try {
                    cyclicBarrier.await();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }, String.valueOf(i)).start();
        }
    }

}
