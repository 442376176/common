package com.zcc.test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SuperTest extends Date {
    private static final long serialVersionUID = 1L;
    private void test(){
        System.out.println(super.getClass().getName());
    }

    public static void main(String[]args){
        List<String> listA = new ArrayList<String>();
        List<String> listB = new ArrayList<String>();
        listA.add("A");
        listA.add("B");
        listB.add("d");
        listB.add("C");
        listA.retainAll(listB);
        System.out.println(listA);
    }
}
    