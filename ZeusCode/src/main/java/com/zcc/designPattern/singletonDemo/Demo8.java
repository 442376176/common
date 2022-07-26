package com.zcc.designPattern.singletonDemo;

/**
 * @ProjectName: ORCode
 * @ClassName: com.zcc.designPattern.singletonDemo
 * @author: zcc
 * @date: 2022/3/17 17:26
 * @version:
 * @Describe:枚举类 推荐使用
 * 1.线程安全
 * 2.防止反序列化重新创建对象
 */
public class Demo8 {
    public static void main(String[] args) {
        Singleton7 instance = Singleton7.INSTANCE;
        Singleton7 instance1 = Singleton7.INSTANCE;
        System.out.println(instance.hashCode());
        System.out.println(instance);
        instance.method();
    }

}
enum Singleton7{
    INSTANCE;
    public void method(){
        System.out.println("ok");
    }
}