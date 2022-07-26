package com.zcc.designPattern.singletonDemo;

/**
 * @ProjectName: ORCode
 * @ClassName: com.zcc.designPattern.singleDemo
 * @author: zcc
 * @date: 2022/3/17 16:28
 * @version:

 */
public class Demo1 {
    public static void main(String[] args) {
        Singleton instance = Singleton.getInstance();
        Singleton instance1 = Singleton.getInstance();
        System.out.println(instance.hashCode());
        System.out.println(instance1.hashCode());
    }
}

/**
 *  * @Describe: 静态常量
 *  * 1.构造器私有化
 *  * 2.类的内部对象创建
 *  * 3.向外暴露一个静态的公共方法
 *
 *  优缺点：
 *  写法简单，在类装载的时候就完成实例化。避免线程同步问题
 *  在类装载的时候就完成实例化，没有达到Lazy Loading的效果。如果从始至终从未使用过这个实例就会造成内存的浪费
 *  这种模式可用，可能会造成内存浪费
 */
class Singleton{
    private Singleton() {
    }
    private final static Singleton instance = new Singleton();

    public static Singleton getInstance(){
        return instance;
    }
}