package com.zcc.designPattern.dependReversel;

/**
 * @ProjectName: ORCode
 * @ClassName: com.zcc.designPattern.dependReversel
 * @author: zcc
 * @date: 2022/3/7 16:28
 * @version:
 * @Describe: 依赖倒转原则demo
 */
public class Demo1 {

    public static void main(String[] args) {
        Person.receive(new Email());
        Person.receive(new WeiXin());
    }

}

interface IReceiver {
    String getInfo();
}

class Email implements IReceiver{
    public String getInfo() {
        return "电子邮件信息： hello world";
    }
}
class WeiXin implements IReceiver{
    public String getInfo() {
        return "微信消息： hello world";
    }
}

/**
 * 完成Person接受信息功能
 * 方式1分析：
 * 1.实现较为简单
 * 2.如果获取的对象是微信 短信的话 要新增类 和 相应的接收方法
 */
class Person {

    public static void receive(IReceiver receiver) {
        System.out.println(receiver.getInfo());
    }

}
