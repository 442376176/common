package com.zcc.utils.send.EMail;

import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * @author zcc
 * @version 1.0
 * @date 2021/11/27 19:02
 */
public class SendEMailUtils {



    /**
     * 发送邮件
     *
     * @param host
     * @param from
     * @param username
     * @param empowerCode 授权码
     * @param recipients  接收人 多个以逗号分隔
     * @param subject     标题
     * @param content     内容
     */
    public static void sendSMTPEmail(String host,
                                     String from,
                                     String username,
                                     String empowerCode,
                                     String recipients,
                                     String subject,
                                     String content) {
        // 设置连接属性
        Properties props = new Properties();
        props.setProperty("mail.host", host);
        props.setProperty("mail.smtp.auth", "true");

        // 设置登录认证
        Authenticator authenticator = new Authenticator() {
            @Override
            public PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, empowerCode);
            }
        };

        // 获取会话
        Session session = Session.getDefaultInstance(props, authenticator);
        // 打印调试日志
//        session.setDebug(true);
        Message message = new MimeMessage(session);
        try {
            // 设置发送人/接收人
            message.setFrom(new InternetAddress(from));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipients));
            //message.setRecipients(RecipientType.TO,InternetAddress.parse("测试的接收的邮件多个以逗号隔开"));
            try {
                // 设置内容格式
                message.setSubject(subject);
                message.setContent(content, "text/html;charset=UTF-8");
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

}
