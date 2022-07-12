package com.zcc.JUC.delayQueue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;

/**
 * @ProjectName: ORCode
 * @ClassName: com.zcc.JUC.delayQueue
 * @author: zcc
 * @date: 2022/6/21 16:00
 * @version:
 * @Describe:
 */
public class DelayQueueDemo {


    static BlockingQueue<Delayed> queue = new DelayQueue();

    public static void main(String[] args) throws InterruptedException {
        queue.add(new MyDelay("第三次添加任务"));
        queue.add(new MyDelay("第四次添加任务"));
        queue.add(new MyDelay("第一次添加任务"));
        queue.add(new MyDelay("第二次添加任务"));

        while (!queue.isEmpty()) {
            Delayed delayed = queue.take();
            System.out.println(delayed);
        }
    }

}
