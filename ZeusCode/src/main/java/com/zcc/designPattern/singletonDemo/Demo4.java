package com.zcc.designPattern.singletonDemo;

/**
 * @ProjectName: ORCode
 * @ClassName: com.zcc.designPattern.singletonDemo
 * @author: zcc
 * @date: 2022/3/17 16:52
 * @version:
 * @Describe: 懒汉式(线程安全 同步方法) 不推荐
 */
public class Demo4 {
    public static void main(String[] args) {
        Singleton3 instance = Singleton3.getInstance();
        Singleton3 instance1 = Singleton3.getInstance();
        System.out.println(instance.hashCode());
        System.out.println(instance1.hashCode());
    }
}

class Singleton3 {
    private static Singleton3 singleton3;
    private Singleton3(){}
    public static synchronized Singleton3 getInstance(){ // 速度慢 效率过低
        if (singleton3==null){
            singleton3 = new Singleton3();
        }
        return singleton3;
    }
}

