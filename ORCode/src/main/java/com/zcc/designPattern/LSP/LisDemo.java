package com.zcc.designPattern.LSP;

/**
 * @ProjectName: ORCode
 * @ClassName: com.zcc.designPattern
 * @author: zcc
 * @date: 2022/3/8 10:29
 * @version:
 * @Describe: 里氏代换原则
 */
public class LisDemo {
    public static void main(String[] args) {
        A a = new A();
        System.out.println("11-3="+a.func1(11,3));
        System.out.println("1-8="+a.func1(1,8));

        System.out.println("------------------------------");

        B b = new B();
        System.out.println("11-3="+b.func1(11,3));
        System.out.println("1-8="+b.func1(1,8));
        System.out.println("11+3+9="+b.func2(11,3));
    }
}



class A {
    public int func1(int a, int b) {
        return a - b;
    }
}

class B extends A {
    public int func1(int a, int b) {
        return a + b;
    }

    public int func2(int a, int b) {
        return func1(a, b) + 9;
    }
}

class Base{

}

class C extends Base{
    public int func1(int a, int b) {
        return a - b;
    }
}

class D extends Base{

    // 组合关系
    private C a = new C();
    public int func1(int a, int b) {
        return a + b;
    }

    public int func2(int a, int b) {
        return func1(a, b) + 9;
    }
    public int func3(int x,int y){
        return a.func1(x,y);
    }
}
