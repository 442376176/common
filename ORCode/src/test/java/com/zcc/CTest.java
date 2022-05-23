package com.zcc;

import java.util.ArrayList;
import java.util.List;

class CTest {
    public static void main(String[] args) {
        ArrayList<Integer> integers = new ArrayList<Integer>() {{
            add(1);
            add(1);
            add(1);
            add(2);
        }};
        List<Integer> integers1 = integers.subList(1, integers.size());
        System.out.println(integers1);
    }
}