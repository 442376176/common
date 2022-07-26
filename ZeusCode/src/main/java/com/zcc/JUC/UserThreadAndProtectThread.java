package com.zcc.JUC;

/**
 * @ProjectName: ORCode
 * @ClassName: com.zcc.JUC
 * @author: zcc
 * @date: 2022/3/1 15:00
 * @version:
 * @Describe:
 */
public class UserThreadAndProtectThread {
    public static void main(String[] args) {
        Thread a = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "::" + Thread.currentThread().isDaemon());
            while (true) {

            }
        }, "aa");

        a.start();
        a.setDaemon(true);
        System.out.println(Thread.currentThread().getName() + "::" + Thread.currentThread().isDaemon() + "执行结束");
    }
}
