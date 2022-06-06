package com.zcc.JUC.interviewQuestions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @ProjectName: ORCode
 * @ClassName: com.zcc.JUC.interviewQuestions
 * @author: zcc
 * @date: 2022/5/31 11:03
 * @version:
 * @Describe:
 */
public class WithVolatile{
    volatile List list = Collections.synchronizedList(new ArrayList<>());
    public void add(Object o){list.add(o);}
    public int size(){return list.size();}
    /* 1.有问题 不睡就停不了
    public static void main(String[] args){
        WithVolatile t = new WithVolatile();
        new Thread(()->{
            for(int i = 0; i < 10; i++){
                t.add(new Object());
                System.out.println("add "+i);
                SleepHelper.sleepNano(1);
            }
        },"add1").start();
        new Thread(()->{
            while(true){
                if (t.size() == 5){
                    break;
                }
            }
            System.out.println("结束");
        },"size").start();
    }*/
    public static void main(String[] args) {
        WithVolatile t = new WithVolatile();
        Object o = new ArrayList<>();
        new Thread(()->{
            synchronized (o){
                for(int i = 0; i < 10; i++){
                    t.add(new Object());
                    System.out.println("add "+i);
                }
            }

        },"add1").start();

        new Thread(()->{
            synchronized (o){

                while(true){
                    if (t.size() == 5){
                        break;
                    }
                }
                System.out.println("结束");
            }
           o.notify();

        },"size").start();
    }
}
