package com.zcc.exception;

import java.util.ArrayList;
import java.util.List;

/**
 * @ProjectName: ORCode
 * @ClassName: com.zcc.exception
 * @author: zcc
 * @date: 2022/4/14 13:11
 * @version:
 * @Describe:
 */
public class Test {
    public static void main(String[] args) {
        test1();
        int test = test();
        System.out.println(test);
    }

    public static void test1(){
        List<Integer> s = new ArrayList<>();
        s.add(1);
        s.add(2);
        List<Integer> a = new ArrayList<>();
        a.add(2);
        a.add(3);

        System.out.println(   s.retainAll(a));
        System.out.println(s);
    }
    public static int test(){
        try{
            int[] arr = new int[]{0};
            arr[1] = 0;
        }catch (Exception e){
            throw new RuntimeException(e);
        }
        return 1;
    }
}
