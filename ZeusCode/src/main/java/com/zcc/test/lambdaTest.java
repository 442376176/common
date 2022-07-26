package com.zcc.test;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;

/**
 * @author zcc
 * @version 1.0
 * @date 2022/1/19 9:35
 */
public class lambdaTest {



    @Test
    public void a(){
        List<Integer> list = new ArrayList<>();
//        for (int i = 0; i < 100 ; i++) {
//            list.add(i);
//        }
        Optional<Integer> reduce = list.stream().filter(item -> item.compareTo(1) == 1)
                .reduce(add);
//        reduce.orElseGet()
        Integer integer = reduce.orElse(10);
        System.out.println(integer);
    }
    private BinaryOperator<Integer> add = (a,b)-> a+b;
    private Consumer<Integer> reduce = (a)-> a--;
//    private Supplier<Integer> supplier = (a)-> a++;
}
