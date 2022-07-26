package com.zcc.utils.send.UPush.Android;

import lombok.Data;

/**
 * @author zcc
 * @version 1.0
 * @date 2021/11/24 11:28
 */
@Data
public class AChannelProperties {
    //系统弹窗，只有display_type=notification时有效，表示华为、小米、oppo、vivo、魅族的设备离线时走系统通道下发时打开指定页面acitivity的完整包路径。
    private String channel_activity;
    // 小米channel_id，具体使用及限制请参考小米推送文档 https://dev.mi.com/console/doc/detail?pId=2086
    private String xiaomi_channel_id;
    // vivo消息分类：0运营消息，1系统消息，需要到vivo申请，具体使用及限制参考[vivo消息推送分类功能说明]https://dev.vivo.com.cn/documentCenter/doc/359
    private String vivo_classification;
    // 可选， android8以上推送消息需要新建通道，否则消息无法触达用户。push sdk 6.0.5及以上创建了默认的通道:upush_default，消息提交厂商通道时默认添加该通道。如果要自定义通道名称或使用私信，请自行创建通道，推送消息时携带该参数具体可参考[oppo通知通道适配] https://open.oppomobile.com/wiki/doc#id=10289
    private String oppo_channel_id;
    //可选，应用入口Activity类全路径,主要用于华为通道角标展示。具体使用可参考[华为角标使用说明]https://developer.umeng.com/docs/67966/detail/272597
    private String main_activity;


}
