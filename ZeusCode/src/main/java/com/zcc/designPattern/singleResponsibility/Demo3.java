package com.zcc.designPattern.singleResponsibility;

/**
 * @ProjectName: ORCode
 * @ClassName: com.zcc.designPattern.singleResponsibility
 * @author: zcc
 * @date: 2022/2/24 16:05
 * @version:
 * @Describe:
 */
public class Demo3 {

    public static void main(String[] args) {
        Vehicle1 vehicle = new Vehicle1();
        vehicle.runWater("轮船");
        vehicle.runLoad("汽车");
        vehicle.runSky("飞机");
    }


}

/**
 * 交通工具类
 * <p>
 * 方式3
 * 1.这种修改方法没有对原来的类做大的修改，知识增加方法
 * 2.这里虽然没有在类级别上遵守单一职责原则，但是在方法级别仍然遵守单一职责原则
 */
class Vehicle1 {
    public void runLoad(String param) {
        System.out.println(param + "在路上");
    }

    public void runSky(String param) {
        System.out.println(param + "在天上");
    }

    public void runWater(String param) {
        System.out.println(param + "在水里");
    }
}