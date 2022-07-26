package com.zcc.utils.send.UPush.Android;

import lombok.Data;

/**
 * @author zcc
 * @version 1.0
 * @date 2021/11/24 11:26
 */
@Data
// 必填，JSON格式，具体消息内容(Android最大为1840B)
public class APlayLoad {
    // 必填，消息类型: notification(通知)、message(消息)
    private String display_type;
    // 必填，消息体
    private ABody body;
    // 可选，JSON格式，用户自定义key-value。
    // 可以配合消息到达后，打开App/URL/Activity使用
    private AExtra extra;


}
