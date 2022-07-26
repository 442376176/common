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
