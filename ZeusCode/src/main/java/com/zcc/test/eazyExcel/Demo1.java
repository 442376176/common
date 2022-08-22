package com.zcc.test.eazyExcel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class  Demo1{
    public static void main(String[] args) {
        List<String> a = new ArrayList<>();
        List<String> b = new ArrayList<>();

        a.add("1");
        a.add("2");
        a.add("3");

        List<String> strings = Arrays.asList("2", "3", "4");

        b.add("2");
        b.add("3");
        b.add("4");

        System.out.println(a.retainAll(strings));
        System.out.println(a);
    }
}