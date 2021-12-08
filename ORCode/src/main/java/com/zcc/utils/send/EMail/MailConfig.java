package com.zcc.utils.send.EMail;

import org.junit.Test;

import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * @author zcc
 * @version 1.0
 * @date 2021/11/27 19:09
 */
public class MailConfig {
    /**
     * 网易163邮箱（mail.163.com）:
     * <p>
     * POP3服务器地址:pop.163.com（端口：110）
     * <p>
     * SMTP服务器地址:smtp.163.com（端口：25）
     * <p>
     * <p>
     * <p>
     * 网易126邮箱（mail.126.com）：
     * <p>
     * POP3服务器地址:pop.126.com（端口：110）
     * <p>
     * SMTP服务器地址:smtp.126.com（端口：25）
     * <p>
     * <p>
     * <p>
     * 移动139邮箱（mail.10086.cn）：
     * <p>
     * POP3服务器地址：POP.139.com（端口：110）
     * <p>
     * SMTP服务器地址：SMTP.139.com(端口：25)
     * <p>
     * <p>
     * <p>
     * 腾讯QQ邮箱（mail.qq.com）：
     * <p>
     * POP3服务器地址：pop.qq.com（端口：110）
     * <p>
     * SMTP服务器地址：smtp.qq.com （端口：25）
     * <p>
     * <p>
     * <p>
     * 腾讯QQ企业邮箱（exmail.qq.com） ：
     * <p>
     * POP3服务器地址：pop.exmail.qq.com （SSL启用 端口：995）
     * <p>
     * SMTP服务器地址：smtp.exmail.qq.com（SSL启用 端口：587/465）
     * <p>
     * <p>
     * <p>
     * 谷歌Gmail邮箱（mail.google.com）：
     * <p>
     * POP3服务器地址:pop.gmail.com（SSL启用 端口：995）
     * <p>
     * SMTP服务器地址:smtp.gmail.com（SSL启用 端口：587）
     * <p>
     * <p>
     * <p>
     * 腾讯Foxmail邮箱（mail.qq.com）：
     * <p>
     * POP3服务器地址:POP.foxmail.com（端口：110）
     * <p>
     * SMTP服务器地址:SMTP.foxmail.com（端口：25）
     * <p>
     * <p>
     * <p>
     * 新浪sina邮箱（mail.sina.com.cn）:
     * <p>
     * POP3服务器地址:pop3.sina.com.cn（端口：110）
     * <p>
     * SMTP服务器地址:smtp.sina.com.cn（端口：25）
     * <p>
     * <p>
     * <p>
     * 新浪sinaVIP邮箱（mail.sina.com.cn）：
     * <p>
     * POP3服务器:pop3.vip.sina.com （端口：110）
     * <p>
     * SMTP服务器:smtp.vip.sina.com （端口：25）
     * <p>
     * <p>
     * <p>
     * 搜狐sohu邮箱（mail.sohu.com）:
     * <p>
     * POP3服务器地址:pop3.sohu.com（端口：110）
     * <p>
     * SMTP服务器地址:smtp.sohu.com（端口：25）
     * <p>
     * <p>
     * <p>
     * 雅虎yahoo邮箱（login.yahoo.com）:
     * <p>
     * POP3服务器地址:pop.mail.yahoo.com
     * <p>
     * SMTP服务器地址:smtp.mail.yahoo.com
     * <p>
     * <p>
     * <p>
     * 雅虎yahoo.com.cn邮箱（login.yahoo.com）:
     * <p>
     * POP3服务器地址:pop.mail.yahoo.com.cn（端口：995）
     * <p>
     * SMTP服务器地址:smtp.mail.yahoo.com.cn（端口：587  ）
     * <p>
     * <p>
     * <p>
     * 微软HotMail邮箱（mail.live.com） ：
     * <p>
     * POP3服务器地址：pop3.live.com （端口：995）
     * <p>
     * SMTP服务器地址：smtp.live.com （端口：587）
     * <p>
     * <p>
     * <p>
     * 263.net:
     * <p>
     * POP3服务器地址:pop3.263.net（端口：110）
     * <p>
     * SMTP服务器地址:smtp.263.net（端口：25）
     * <p>
     * <p>
     * <p>
     * 263.net.cn:
     * <p>
     * POP3服务器地址:pop.263.net.cn（端口：110）
     * <p>
     * SMTP服务器地址:smtp.263.net.cn（端口：25）
     * <p>
     * <p>
     * <p>
     * x263.net:
     * <p>
     * POP3服务器地址:pop.x263.net（端口：110）
     * <p>
     * SMTP服务器地址:smtp.x263.net（端口：25）
     * <p>
     * <p>
     * <p>
     * 21cn.com:
     * <p>
     * POP3服务器地址:pop.21cn.com（端口：110）
     * <p>
     * SMTP服务器地址:smtp.21cn.com（端口：25）
     * <p>
     * <p>
     * <p>
     * china.com:
     * <p>
     * POP3服务器地址:pop.china.com（端口：110）
     * <p>
     * SMTP服务器地址:smtp.china.com（端口：25）
     * <p>
     * <p>
     * tom.com:
     * <p>
     * POP3服务器地址:pop.tom.com（端口：110）
     * <p>
     * SMTP服务器地址:smtp.tom.com（端口：25）
     * <p>
     * <p>
     * etang.com:
     * <p>
     * POP3服务器地址:pop.etang.com
     * <p>
     * SMTP服务器地址:smtp.etang.com
     */
    private String host = EMailAddr.WY_163_MAIL_SMTP.getHost();     //邮件服务器主机host,目前只支持SMTP协议(163或qq)  网易默认是：smtp.163.com
    private String port = EMailAddr.WY_163_MAIL_SMTP.getPort();      //端口号
    private String from = "eazylinkservice@163.com";
    private String username = "eazylinkservice@163.com";
    private String s = "UCWXKLFRQIJJKJUC";


    public  void sendEmail(String someone, String subject, String content) {
        // 设置属性
        Properties props = new Properties();
        props.setProperty("mail.host", host);
        props.setProperty("mail.smtp.auth", "true");
        // 连接认证信息
        Authenticator authenticator = new Authenticator() {
            @Override
            public PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, s);
            }
        };
        // 获取会话
        Session session = Session.getDefaultInstance(props, authenticator);
        session.setDebug(true);
        // 填充信息
        Message message = new MimeMessage(session);
        try {
            // 发送方
            message.setFrom(new InternetAddress(from));
            // 接收方
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(someone));
            //message.setRecipients(RecipientType.TO,InternetAddress.parse("测试的接收的邮件多个以逗号隔开"));
            try {
                // 标题
                message.setSubject(subject);
                // 内容 格式
                message.setContent(content, "text/html;charset=UTF-8");
                // 发送
                Transport.send(message);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (AddressException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void tsetemail() {
        String content = "Hello,This is a test email!!!!";

        sendEmail("1518983927@qq.com", "标题", content);
    }



}
