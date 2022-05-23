package com.zcc.designPattern.singletonDemo;

/**
 * @ProjectName: ORCode
 * @ClassName: com.zcc.designPattern.singletonDemo
 * @author: zcc
 * @date: 2022/3/17 16:41
 * @version:
 * @Describe: 静态代码块
 */
public class Demo2 {
    public static void main(String[] args) {
        Singleton1 instance = Singleton1.getInstance();
        Singleton1 instance1 = Singleton1.getInstance();
        System.out.println(instance.hashCode());
        System.out.println(instance1.hashCode());
    }
}

class Singleton1 {

    private Singleton1() {
    }

    private static Singleton1 singleton1;

    static {
        singleton1 = new Singleton1();
    }

    public static Singleton1 getInstance() {
        return singleton1;
    }
}
