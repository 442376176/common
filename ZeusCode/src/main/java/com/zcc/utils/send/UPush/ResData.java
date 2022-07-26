package com.zcc.utils.send.UPush;

import lombok.Data;

/**
 * @author zcc
 * @version 1.0
 * @date 2021/11/24 16:38
 */
@Data
public class ResData {

    // 当"ret"为"SUCCESS"时，包含参数如下:
    // 单播类消息(type为unicast、listcast、customizedcast且不带file_id)返回
    private String msg_id;
    // 任务类消息(type为broadcast、groupcast、filecast、customizedcast且file_id不为空)返回：
    private String task_id;
    // 当"ret"为"FAIL"时，包含参数如下:
    // 错误码，详见附录I
    private String error_code;
    // 错误信息
    private String error_msg;

}
