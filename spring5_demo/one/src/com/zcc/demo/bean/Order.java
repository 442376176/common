package com.zcc.demo.bean;

/**
 * @author zcc
 * @version 1.0
 * @date 2022/2/9 13:45
 */
public class Order {

    private String name;
    private String address;

    public Order(String name, String address) {
        this.name = name;
        this.address = address;
    }

    @Override
    public String toString() {
        return "Order{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
