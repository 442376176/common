package com.zcc.utils.send.UPush.IOS;

import lombok.Data;

/**
 * @author zcc
 * @version 1.0
 * @date 2021/11/24 13:23
 */
@Data
// 当content-available=1时(静默推送)，可选; 否则必填
// 可为字典类型和字符串类型
public class IAlert {
    private String title;
    private String subtitle;
    private String body;
}
