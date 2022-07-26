package com.zcc.JUC.interviewQuestions;

import lombok.SneakyThrows;

/**
 * @ProjectName: ORCode
 * @ClassName: com.zcc.JUC.interviewQuestions
 * @author: zcc
 * @date: 2022/5/24 9:19
 * @version:
 * @Describe: 哲学家吃饭问题 5人 5根筷子 一个人拿到两个根筷子才能吃饭
 *              1. 形成死锁 5个人都吃不了饭
 *              2. 解决死锁 5个人都能吃饭
 */
public class PhilosopherEat {

    static class Chopsticks {
    }

    static class Philosopher extends Thread {
        private Chopsticks left, right;
        private int index;
        private String name;

        public Philosopher(Chopsticks left, Chopsticks right, int index, String name) {
            this.left = left;
            this.right = right;
            this.index = index;
            this.name = name;
        }

        @SneakyThrows
        @Override
        public void run() {
            if (index%2==0){
                synchronized (left) {
                    Thread.sleep(100);
                    synchronized (right) {
                        System.out.println(index + "号 吃完了");
                    }
                }
            }else {
                synchronized (right) {
                    Thread.sleep(100);
                    synchronized (left) {
                        System.out.println(index + "号 吃完了");
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        Chopsticks chopsticks1 = new Chopsticks();
        Chopsticks chopsticks2 = new Chopsticks();
        Chopsticks chopsticks3 = new Chopsticks();
        Chopsticks chopsticks4 = new Chopsticks();
        Chopsticks chopsticks5 = new Chopsticks();
        Philosopher philosopher1 = new Philosopher(chopsticks1, chopsticks2, 1, "1");
        Philosopher philosopher2 = new Philosopher(chopsticks2, chopsticks3, 2, "2");
        Philosopher philosopher3 = new Philosopher(chopsticks3, chopsticks4, 3, "3");
        Philosopher philosopher4 = new Philosopher(chopsticks4, chopsticks5, 4, "4");
        Philosopher philosopher5 = new Philosopher(chopsticks5, chopsticks1, 5, "5");

        philosopher1.start();
        philosopher2.start();
        philosopher3.start();
        philosopher4.start();
        philosopher5.start();

    }

}
