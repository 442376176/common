package com.zcc.JUC.interviewQuestions;

/**
 * @ProjectName: ORCode
 * @ClassName: com.zcc.JUC.interviewQuestions
 * @author: zcc
 * @date: 2022/5/27 13:22
 * @version:
 * @Describe:
 */
public class BankDemo {

    static class Account {
        private String name;
        private double balance;

        public synchronized void set(String name, double balance) {
            this.name = name;
            SleepHelper.sleep(2);
            this.balance = balance;
        }

        public synchronized double getBalance(String name){return this.balance;}

        public static void main(String[] args) {
            Account a = new Account();
            new Thread(()->a.set("张三",100.0)).start();
            SleepHelper.sleep(1);

            System.out.println(a.getBalance("张三"));
            SleepHelper.sleep(1);
            System.out.println(a.getBalance("张三"));
        }
    }


}
