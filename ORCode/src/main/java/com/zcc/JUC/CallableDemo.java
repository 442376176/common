package com.zcc.JUC;


import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

/**
 * @ProjectName: ORCode
 * @ClassName: com.zcc.JUC
 * @author: zcc
 * @date: 2022/3/4 10:39
 * @version:
 * @Describe: CallableDemo
 */
public class CallableDemo {
    public static void main(String[] args) throws Exception {
        System.out.println(Thread.currentThread().getName());
        FutureTask<Integer> future0 = new FutureTask<>(new S(),20);
        FutureTask<Integer> future1 = new FutureTask<>(new D());
        FutureTask<Integer> future2 = new FutureTask<>(()->{
            System.out.println(Thread.currentThread().getName());
            return 1024;
        } );

        new Thread(future0).start();
        new Thread(future1).start();
        new Thread(future2).start();

        while(!future1.isDone()){
            System.out.println("waiting......");
        }
        System.out.println(future1.get());
        System.out.println(future0.get());
        while(!future0.isDone()){
            System.out.println("waiting......");
        }


    }

}
class S implements Runnable{

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName()+"Runnable接口实现");
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}


class D implements Callable<Integer> {

    @Override
    public Integer call() throws Exception {
        System.out.println(Thread.currentThread().getName()+"Callable接口实现");
        return 2048;
    }
}

