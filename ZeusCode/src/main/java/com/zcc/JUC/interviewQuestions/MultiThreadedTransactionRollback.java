package com.zcc.JUC.interviewQuestions;

import lombok.SneakyThrows;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * @ProjectName: ORCode
 * @ClassName: com.zcc.JUC.interviewQuestions
 * @author: zcc
 * @date: 2022/5/24 13:17
 * @version:
 * @Describe: 多线程事务回滚（高效）
 */
public class MultiThreadedTransactionRollback {
    static class One {
        static class MyTask extends Thread {

            private String name;
            private int timeSeconds;
            private boolean isSuccess;


            public MyTask(String name, int timeSeconds, boolean isSuccess) {
                this.name = name;
                this.timeSeconds = timeSeconds;
                this.isSuccess = isSuccess;
            }

            @SneakyThrows
            @Override
            public void run() {
                SleepHelper.sleep(timeSeconds);
                System.out.println(name + (isSuccess ? "执行结束" : "执行失败"));
            }
        }

        public static void main(String[] args) {
            Thread t1 = new MyTask("t1", 3, true);
            Thread t2 = new MyTask("t2", 5, true);
            Thread t3 = new MyTask("t3", 1, false);
            t1.start();
            t2.start();
            t3.start();
        }
    }

    static class Two {


        static class MyTask extends Thread {

            private String name;
            private int timeSeconds;
            private boolean isSuccess;
            private Result result;


            public MyTask(String name, int timeSeconds, boolean isSuccess) {
                this.name = name;
                this.timeSeconds = timeSeconds;
                this.isSuccess = isSuccess;
                this.result = Result.EXECUTE;
            }

            @Override
            public void run() {
                SleepHelper.sleep(timeSeconds);
                System.out.println(name + (isSuccess ? "执行结束" : "执行失败"));
                if (isSuccess) {
                    result = Result.SUCCESS;
                } else {
                    result = Result.FAILED;
                }
            }
        }

        public static void main(String[] args) {
            MyTask t1 = new MyTask("t1", 3, true);
            MyTask t2 = new MyTask("t2", 5, true);
            MyTask t3 = new MyTask("t3", 1, false);
            List<MyTask> list = new ArrayList<>();
            list.add(t1);
            list.add(t2);
            list.add(t3);
            list.forEach(MyTask::start);
            for (; ; ) { // 盲等待 消耗CPU性能
                for (MyTask task : list) {
                    if (Result.FAILED.equals(task.result)) {
                        System.exit(0);
                    }
                }
            }
        }
    }

    static class Three {


        static class Boss extends Thread {

            private List<Worker> workers = new ArrayList<>();

            public void addWorker(Worker worker) {
                workers.add(worker);
            }

            @Override
            public void run() {
                workers.forEach(Worker::start);
            }

            public void end(Worker worker) {
                if (Result.FAILED.equals(worker.result)) {
                    System.exit(0);
                }
            }
        }

        static class Worker extends Thread {

            private String name;
            private int timeSeconds;
            private boolean isSuccess;
            private Result result;
            private Boss boss;


            public Worker(String name, int timeSeconds, boolean isSuccess, Boss boss) {
                this.name = name;
                this.timeSeconds = timeSeconds;
                this.isSuccess = isSuccess;
                this.result = Result.EXECUTE;
                this.boss = boss;
            }

            @Override
            public void run() {
                SleepHelper.sleep(timeSeconds);
                System.out.println(name + (isSuccess ? "执行结束" : "执行失败"));
                result = isSuccess ? Result.SUCCESS : Result.FAILED;
                boss.end(this);
            }
        }

        public static void main(String[] args) {
            Boss boss = new Boss();
            Worker t1 = new Worker("t1", 3, true, boss);
            Worker t2 = new Worker("t2", 5, true, boss);
            Worker t3 = new Worker("t3", 1, false, boss);
            boss.addWorker(t1);
            boss.addWorker(t2);
            boss.addWorker(t3);
            boss.start();
        }
    }

    enum Result {
        EXECUTE, SUCCESS, FAILED, CANCELLED
    }

    static class Four {

        // 统一管理者
        static class Boss extends Thread {
            private List<Worker> workers = new ArrayList<>();

            public void addWorker(Worker worker) {
                this.workers.add(worker);
            }

            @Override
            public void run() {
                workers.forEach(Worker::start);
            }

            public void end(Worker worker) {
                if (Result.FAILED.equals(worker.result)) {
                    cancel(worker);
                }

            }

            private void cancel(Worker worker) {
                workers.stream().filter(item -> worker != item)
                        .forEach(Worker::cancel);
            }
        }

        static class Worker extends Thread {

            private String name;
            private int timeSeconds;
            private boolean isSuccess;
            private Result result;
            private Boss boss;


            public Worker(String name, int timeSeconds, boolean isSuccess, Boss boss) {
                this.name = name;
                this.timeSeconds = timeSeconds;
                this.isSuccess = isSuccess;
                this.result = Result.EXECUTE;
                this.boss = boss;
            }

            public void cancel() {
                // todo
                this.interrupt();
            }

            @Override
            public void run() {
                SleepHelper.sleep(timeSeconds);
                result = isSuccess ? Result.SUCCESS : Result.FAILED;
                boss.end(this);
            }
        }

        public static void main(String[] args) {
            Boss boss = new Boss();
            Worker t1 = new Worker("t1", 3, true, boss);
            Worker t2 = new Worker("t2", 5, true, boss);
            Worker t3 = new Worker("t3", 1, false, boss);
            boss.addWorker(t1);
            boss.addWorker(t2);
            boss.addWorker(t3);
            boss.start();
        }
    }

    static class FinalCompletableFuture {
        static List<MyTask> myTasks = new ArrayList<>();

        static class MyTask {

            private String name;
            private int timeSeconds;
            private boolean isSuccess;
            private Result result;
            private boolean cancelling = false;
            volatile boolean cancelled = false;

            public MyTask(String name, int timeSeconds, boolean isSuccess) {
                this.name = name;
                this.timeSeconds = timeSeconds*1000;
                this.isSuccess = isSuccess;
                this.result = Result.EXECUTE;

            }

            public Result runTask() {
                int interval = 100;
                int total = 0;
                try {
                    for (; ; ) {
                        Thread.sleep(interval);
                        if (cancelling) {
                            continue;
                        }
                        total += interval;
                        if (total >= timeSeconds) {
                            result = isSuccess?Result.SUCCESS:Result.FAILED;
                            if (isSuccess){
                                System.out.println(name + " -------- end");
                                break;
                            }else {
                                System.out.println(name + " -------- fail");
                                return result;

                            }

                        }
                        if (cancelled) {
                            return Result.CANCELLED;
                        }
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                return result;
            }

            public void cancel(){
                cancelling = true;
                synchronized (this){ // 执行一次取消
                    System.out.println(name  + "------cancelling");
                    SleepHelper.sleepMilli(50);
                    System.out.println(name + "------cancelled");
                }
                cancelling = false;
                cancelled = true;
            }

        }

        public static void main(String[] args) throws InterruptedException {
            MyTask t1 = new MyTask("t1", 3, true);
            MyTask t2 = new MyTask("t2", 5, true);
            MyTask t3 = new MyTask("t3", 1, false);
            myTasks.add(t3);
            myTasks.add(t2);
            myTasks.add(t1);

            myTasks.forEach(task ->
                CompletableFuture.supplyAsync(() -> task.runTask())
                        .thenAccept(result -> callback(result,task)).join()
            );
            TimeUnit.SECONDS.sleep(10);

        }

        public static Result callback(Result result, MyTask task) {
            if (Result.FAILED.equals(result)) {
                myTasks.stream().filter(item -> item != task)
                        .forEach(MyTask::cancel);
            }
            return result;
        }
    }
}
