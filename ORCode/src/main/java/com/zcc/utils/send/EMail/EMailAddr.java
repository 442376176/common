package com.zcc.utils.send.EMail;

/**
 * @author zcc
 * @version 1.0
 * @date 2021/11/27 19:12
 */
public enum EMailAddr {
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
