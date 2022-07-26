package com.zcc.designPattern.interfaceSegregationPrinciple;

/**
 * @ProjectName: ORCode
 * @ClassName: com.zcc.designPattern.interfaceSegregationPrinciple
 * @author: zcc
 * @date: 2022/2/24 16:41
 * @version:
 * @Describe: 接口隔离原则
 */
public class Demo1 {
    public static void main(String[] args) {

    }

}
// 接口
interface Interface1{
    void method1();
//    void method2();
//    void method3();
//    void method4();
//    void method5();
}
interface Interface2{
//    void method1();
    void method2();
    void method3();
}
interface Interface3{
//    void method1();
    void method4();
    void method5();
}
class A{
    public void depend1(Interface1 interface1){
        interface1.method1();
    }
    public void depend2(Interface2 interface1){
        interface1.method2();
    }
    public void depend3(Interface2 interface1){
        interface1.method3();
    }
}
class B implements Interface1,Interface2{

    @Override
    public void method1() {
        System.out.println("B实现了method1");
    }

    @Override
    public void method2() {
        System.out.println("B实现了method2");
    }

    @Override
    public void method3() {
        System.out.println("B实现了method3");
    }

}
class C{
    public void depend1(Interface1 interface1){
        interface1.method1();
    }
    public void depend2(Interface3 interface1){
        interface1.method4();
    }
    public void depend3(Interface3 interface1){
        interface1.method5();
    }
}
class D implements Interface1,Interface3{

    @Override
    public void method1() {
        System.out.println("D实现了method1");
    }

    @Override
    public void method4() {
        System.out.println("D实现了method4");
    }

    @Override
    public void method5() {
        System.out.println("D实现了method5");
    }
}