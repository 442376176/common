package com.zcc.designPattern.singleResponsibility;

/**
 * @ProjectName: ORCode
 * @ClassName: com.zcc.designPattern.singleResponsibility
 * @author: zcc
 * @date: 2022/2/24 16:05
 * @version:
 * @Describe:
 */
public class Demo2 {

    public static void main(String[] args) {
        RoadVehicle roadVehicle = new RoadVehicle();
        WaterVehicle waterVehicle = new WaterVehicle();
        SkyVehicle skyVehicle = new SkyVehicle();
        roadVehicle.run("汽车");
        waterVehicle.run("船");
        skyVehicle.run("飞机");
    }


}

/**
 * 交通工具类
 * 方案2
 *
 * 1.遵守了单一职责
 * 2.但是这样改动很大，同事修改客户端
 * 3.改进：直接修改Vehicle类，改动代码会比较少
 */
class RoadVehicle {
    public void run(String param) {
        System.out.println(param + "在路上");
    }
}
class WaterVehicle {
    public void run(String param) {
        System.out.println(param + "在海里");
    }
}
class SkyVehicle {
    public void run(String param) {
        System.out.println(param + "在天上");
    }
}