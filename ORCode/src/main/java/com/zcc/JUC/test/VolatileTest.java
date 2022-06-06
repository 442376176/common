package com.zcc.JUC.test;

import com.zcc.JUC.interviewQuestions.SleepHelper;

import java.util.concurrent.TimeUnit;

/**
 * @ProjectName: ORCode
 * @ClassName: com.zcc.JUC.test
 * @author: zcc
 * @date: 2022/5/30 9:27
 * @version:
 * @Describe:
 */
public class VolatileTest {


    static class A{
        volatile boolean running = true;
        void m(){
            System.out.println(" m start");
            while (running){
                SleepHelper.sleepMilli(10);
            }
            System.out.println(" m end");
        }

        public static void main(String[] args) {
            A a = new A();
            new Thread(a::m,"t1").start();
            try {
                TimeUnit.SECONDS.sleep(1);
            }catch (InterruptedException exception){
                exception.printStackTrace();
            }
            a.running = false;
        }
    }


}
