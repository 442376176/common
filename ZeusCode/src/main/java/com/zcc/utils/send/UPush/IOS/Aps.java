package com.zcc.utils.send.UPush.IOS;

import lombok.Data;


/**
 * @author zcc
 * @version 1.0
 * @date 2021/11/24 13:20
 */
@Data
// 必填，严格按照APNs定义来填写
public class Aps {
    // 当content-available=1时(静默推送)，可选; 否则必填
    // 可为字典类型和字符串类型
    private IAlert alert;
    // "badge": xx,    // 可选
    private String badge;
    //    "sound":"xx",    // 可选
    private String sound;
    // "content-available":1    // 可选，代表静默推送
    private String content_available = "1";
    //  "category":"xx",    // 可选，注意: iOS8才支持该字段
    private String category;
    // "interruption-level": "active" //可选，消息的打扰级别，iOS15起支持，四个选项"passive", "active", "time-sensitive", "critical"
    private String interruption_level = "active";

}
