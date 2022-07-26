package com.zcc.test;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author zcc
 * @version 1.0
 * @date 2021/11/23 17:09
 */
public class MultithreadingTest {
    /**
     * 任务类
     */
    static class Task {

        private int id;

        public Task(int id) {
            this.id = id;
        }

        public void start() {
            System.out.println(Thread.currentThread().getName() + ": start to handle task " + id);

            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    /**
     * 生产者
     */
    static class TaskProducer {

        public static List<Task> produce(int count) {
            List<Task> tasks = new LinkedList<Task>();

            for (int i = 0; i < count; i++) {
                tasks.add(new Task(i + 1));
            }

            return tasks;
        }

    }

    static class Strategy4 {

        public static void main(String[] args) {
            List<Task> tasks = TaskProducer.produce(1000);
            handleTasks(tasks, 10);
            System.out.println("All finished");
        }

        /**
         * 线程池配置
         * @param tasks
         * @param threadCount
         */
        public static void handleTasks(List<Task> tasks, int threadCount) {
            try {
                ExecutorService executor = Executors.newFixedThreadPool(threadCount);

                for (Task task : tasks) {
                    executor.submit(new TaskHandler(task));
                }

                executor.shutdown();
                executor.awaitTermination(60, TimeUnit.SECONDS);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        /**
         * 执行器
         */
        public static class TaskHandler implements Runnable {

            private Task task;

            public TaskHandler(Task task) {
                this.task = task;
            }

            public void run() {
                task.start();
            }

        }

    }

}
