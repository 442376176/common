package com.zcc.designPattern.singletonDemo;

/**
 * @ProjectName: ORCode
 * @ClassName: com.zcc.designPattern.singletonDemo
 * @author: zcc
 * @date: 2022/3/17 18:30
 * @version:
 * @Describe:
 */
public class TestRunTime {
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        // 饿汉式写法
        Runtime a = Runtime.getRuntime();
        Runtime b = Runtime.getRuntime();
        Runtime c = Runtime.getRuntime();
        System.out.println(a.hashCode());
    }
}
