package com.zcc.designPattern.singletonDemo;

/**
 * @ProjectName: ORCode
 * @ClassName: com.zcc.designPattern.singletonDemo
 * @author: zcc
 * @date: 2022/3/17 16:52
 * @version:
 * @Describe: 懒汉式(线程不安全 同步代码块) 不能使用！！！
 */
public class Demo5 {
    public static void main(String[] args) {
        Singleton4 instance = Singleton4.getInstance();
        Singleton4 instance1 = Singleton4.getInstance();
        System.out.println(instance.hashCode());
        System.out.println(instance1.hashCode());
    }
}

class Singleton4 {
    private static Singleton4 singleton4;

    private Singleton4() {
    }


    public static Singleton4 getInstance() { // 速度慢 效率过低

        if (singleton4 == null) {
            synchronized (Singleton4.class) {
                singleton4 = new Singleton4();
            }

        }

        return singleton4;
    }
}

