package com.zcc.designPattern.singletonDemo;

/**
 * @ProjectName: ORCode
 * @ClassName: com.zcc.designPattern.singletonDemo
 * @author: zcc
 * @date: 2022/3/17 16:52
 * @version:
 * @Describe: 懒汉式(线程不安全)
 */
public class Demo3 {
    public static void main(String[] args) {
        Singleton2 instance = Singleton2.getInstance();
        Singleton2 instance1 = Singleton2.getInstance();
        System.out.println(instance.hashCode());
        System.out.println(instance1.hashCode());
    }
}

class Singleton2 {
    private static Singleton2 singleton2;
    private Singleton2(){}
    public static Singleton2 getInstance(){
        if (singleton2==null){ // 多线程有问题
            singleton2 = new Singleton2();
        }
        return singleton2;
    }
}
