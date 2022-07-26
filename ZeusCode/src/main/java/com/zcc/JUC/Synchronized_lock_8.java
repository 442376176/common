package com.zcc.JUC;

import java.util.concurrent.TimeUnit;

/**
 * @ProjectName: ORCode
 * @ClassName: com.zcc.JUC
 * @author: zcc
 * @date: 2022/3/2 17:07
 * @version:
 * @Describe:
 * 1.标准访问，先打印短信还是邮件 短信
 * 2.停4秒在短信方法内，先打印短信还是邮件 短信
 * 3.新增普通的hello方法，是先打短信还是hello hello
 * 4.现在有两部手机，先打印短信还是邮件  邮件
 * 5.两个静态同步方法，1部手机，先打印短信还是邮件 短信
 * 6.两个静态同步方法，2部手机，先打印短信还是邮件 短信
 * 7.1个静态同步方法，1个普通同步方法，1部手机，先打印短信还是邮件   邮件
 * 8.1个静态同步方法，1个普通同步方法，2部手机，先打印短信还是邮件   邮件
 *
 * synchronized实现同步的基础：Java中的每一个对象都可以作为锁
 * 具体表现为3中形式：
 * 对于普通同步方法，锁是当前实例对象
 * 对于静态同步方法，锁是当前类的Class对象
 * 对于同步方法快，锁是synchronized括号里配置的对象
 */
public class Synchronized_lock_8 {
    public static void main(String[] args) {
        Phone phone1 = new Phone();
        Phone phone2 = new Phone();
        new Thread(()->{
            try {
                phone1.sendMSG();

            } catch (Exception e) {
                e.printStackTrace();
            }
        },"AA").start();

        new Thread(()->{
            try {
//                phone2.sendMail();
                phone1.sendMail();
            } catch (Exception e) {
                e.printStackTrace();
            }
        },"BB").start();
    }

}

class Phone {
    public static synchronized void sendMSG() throws Exception {
        TimeUnit.SECONDS.sleep(4);
        System.out.println("-----------------发送信息");
    }

    public synchronized void sendMail() throws Exception {
        System.out.println("-----------------发送邮件");
    }

    public void getHello() {
        System.out.println("-----------------getHello");
    }
}
