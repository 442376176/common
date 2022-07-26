package com.zcc.JUC.test;

import com.zcc.JUC.interviewQuestions.SleepHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.LongAdder;

/**
 * @ProjectName: ORCode
 * @ClassName: com.zcc.JUC.test
 * @author: zcc
 * @date: 2022/5/30 13:13
 * @version:
 * @Describe:
 */
public class AtomicXXX {
   static AtomicInteger count = new AtomicInteger(0);
   static long count1= 0L;
   static LongAdder count2 = new LongAdder();
    void m(){
        for (int i = 0; i < 1000; i++) {
            count.incrementAndGet();
        }
    }
     void n(){

        for (int i = 0; i < 1000; i++) {
            synchronized(this){
                count1++;
            }
        }
    }
    void s(){
        for (int i = 0; i < 1000; i++) {
            count2.increment();
        }
    }

    public static void main(String[] args) {
        AtomicXXX xxx = new AtomicXXX();
        List<Thread> threads = new ArrayList<>();
        List<Thread> threads1 = new ArrayList<>();
        List<Thread> threads2 = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            threads.add(new Thread(xxx::m,"thread-"+i));
        }
//        for (int i = 0; i < 10; i++) {
//            threads1.add(new Thread(xxx::n,"thread-"+i));
//        }
//        for (int i = 0; i < 10; i++) {
//            threads2.add(new Thread(xxx::s,"thread-"+i));
//        }
        long l = System.currentTimeMillis();
        threads.forEach(Thread::start);
//        threads2.forEach(Thread::start);
//        threads1.forEach(Thread::start);
        SleepHelper.sleep(2);
        System.out.println(count.get());
    }

}
