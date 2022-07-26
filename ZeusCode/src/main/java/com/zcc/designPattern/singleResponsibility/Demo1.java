package com.zcc.designPattern.singleResponsibility;

/**
 * @ProjectName: ORCode
 * @ClassName: com.zcc.designPattern.singleResponsibility
 * @author: zcc
 * @date: 2022/2/24 16:05
 * @version:
 * @Describe:
 */
public class Demo1 {

    public static void main(String[] args) {
        Vehicle vehicle = new Vehicle();
        vehicle.run("共享单车");
        vehicle.run("汽车");
        vehicle.run("飞机");
    }


}

/**
 * 交通工具类
 *
 * 方式1
 * 1.在方式1的run方法中，违反了单一职责原则
 * 2.解决的方案非常简单，根据交通工具运行方法不同，分解成不同的类即可
 *
 */
class Vehicle {
    public void run(String param) {
        System.out.println(param + "在路上");
    }
}