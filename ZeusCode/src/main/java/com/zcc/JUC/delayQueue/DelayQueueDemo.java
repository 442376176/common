package com.zcc.JUC.delayQueue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.DelayQueue;

/**
 * @ProjectName: ORCode
 * @ClassName: com.zcc.JUC.delayQueue
 * @author: zcc
 * @date: 2022/6/21 16:00
 * @version:
 * @Describe:
 */
public class DelayQueueDemo {


    static BlockingQueue<MyDelay> queue = new DelayQueue();
    static ThreadLocal threadLocal = new ThreadLocal();


    public static void main(String[] args) throws InterruptedException {
        queue.add(new MyDelay("第三次添加任务"));
        queue.add(new MyDelay("第四次添加任务"));
        queue.add(new MyDelay("第一次添加任务"));
        queue.add(new MyDelay("第二次添加任务"));

        while (!queue.isEmpty()) {
            MyDelay delayed = queue.take();
           try{
               int s = 1/0;
           }catch (Exception e){
               threadLocal.set(delayed.getData());
               String o = (String)threadLocal.get();
               System.out.println(o);
           }
            System.out.println(delayed);
        }

        while(true){

        }
    }

}
