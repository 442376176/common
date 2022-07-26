package com.zcc.utils.send.UPush.IOS;

import lombok.Data;

/**
 * @author zcc
 * @version 1.0
 * @date 2021/11/24 13:18
 */
@Data
public class IPlayLoad {
    // 必填，严格按照APNs定义来填写
    private Aps aps;
    private String key;
}
