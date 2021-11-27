package com.zcc.utils.send.EMail;

/**
 * @author zcc
 * @version 1.0
 * @date 2021/11/27 19:12
 */
public enum EMailAddr {
    WY_163_MAIL_POP3("pop.163.com", "110"),
    WY_163_MAIL_SMTP("smtp.163.com", "25"),
    WY_126_MAIL_POP3("pop.126.com", "110"),
    WY_126_MAIL_SMTP("smtp.126.com", "25"),
    YD_139_MAIL_POP3("POP.139.com", "110"),
    YD_139_MAIL_SMTP("SMTP.139.com", "25"),
    QQ_MAIL_POP3("pop.qq.com", "110"),
    QQ_MAIL_SMTP("smtp.qq.com", "25"),
    QQ_QY_MAIL_POP3("pop.exmail.qq.com", "995"),
    QQ_QY_MAIL_SMTP("smtp.exmail.qq.com", "587/465"),
    G_MAIL_POP3("pop.gmail.com", "995"),
    G_MAIL_SMTP("smtp.gmail.com", "587"),
    TX_FOX_MAIL_POP3("POP.foxmail.com", "110"),
    TX_FOX_MAIL_SMTP("SMTP.foxmail.com", "110"),
    SINA_MAIL_POP3("pop3.sina.com.cn", "110"),
    SINA_MAIL_SMTP("smtp.sina.com.cn", "25"),
    SINA_VIP_MAIL_POP3("pop3.sina.vip.com", "110"),
    SINA_VIP_MAIL_SMTP("smtp.sina.vip.com", "25");


    private String host;
    private String port;

    EMailAddr(String host, String port) {
        this.host = host;
        this.port = port;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }
}
