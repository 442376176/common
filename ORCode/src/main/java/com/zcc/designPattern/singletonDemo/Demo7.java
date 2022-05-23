package com.zcc.designPattern.singletonDemo;

/**
 * @ProjectName: ORCode
 * @ClassName: com.zcc.designPattern.singletonDemo
 * @author: zcc
 * @date: 2022/3/17 17:11
 * @version:
 * @Describe:静态内部类 推荐使用
 * （JVM类装载是线程安全）
 * 外层装载时，静态内部类不会装载
 * 调用方法getInstance时，静态内部类会去装载，只装载一次
 * 线程安全；
 * 延迟加载(懒加载)；
 * 效率较高
 */
public class Demo7 {
    public static void main(String[] args) {
        Singleton6 instance = Singleton6.getInstance();
        Singleton6 instance1 = Singleton6.getInstance();
        System.out.println(instance.hashCode());
        System.out.println(instance1.hashCode());
    }
}

class Singleton6 {
    private static volatile Singleton6 singleton6;

    private Singleton6() {
    }

    private static class SingletonInstance {
        private static final Singleton6 INSTANCE = new Singleton6();
    }

    public static Singleton6 getInstance() {

        return SingletonInstance.INSTANCE;
    }
}
