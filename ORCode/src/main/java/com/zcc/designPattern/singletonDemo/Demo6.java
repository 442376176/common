package com.zcc.designPattern.singletonDemo;

/**
 * @ProjectName: ORCode
 * @ClassName: com.zcc.designPattern.singletonDemo
 * @author: zcc
 * @date: 2022/3/17 17:11
 * @version:
 * @Describe:双重检查 比较推荐
 * 线程安全；延迟加载(懒加载)；效率较高
 */
public class Demo6 {
    public static void main(String[] args) {
        Singleton5 instance = Singleton5.getInstance();
        Singleton5 instance1 = Singleton5.getInstance();
        System.out.println(instance.hashCode());
        System.out.println(instance1.hashCode());
    }
}

class Singleton5 {
    private static volatile Singleton5 singleton5;

    private Singleton5() {
    }


    public static Singleton5 getInstance() { //

        if (singleton5 == null) {
            synchronized (Singleton5.class) {
                if (singleton5 == null) {
                    singleton5 = new Singleton5();
                }
            }
        }
        return singleton5;
    }
}
