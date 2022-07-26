package com.zcc.utils.send.UPush.Android;

import lombok.Data;

/**
 * @author zcc
 * @version 1.0
 * @date 2021/11/24 11:39
 */
@Data
// 必填，消息体
public class ABody {
    // 当display_type=message时，body的内容只需填写custom字段
    // 当display_type=notification时，body包含如下参数:


    // 必填，通知标题
    private String title;
    // 必填，通知文字描述
    private String text;
    // 自定义通知图标:"icon":"xx",
    // 可选，状态栏图标ID，R.drawable.[smallIcon]，
    // 如果没有，默认使用应用图标
    // 图片要求为24*24dp的图标，或24*24px放在drawable-mdpi下
    // 注意四周各留1个dp的空白像素
    private String icon;
    // 可选，通知栏拉开后左侧图标ID，R.drawable.[largeIcon]
    // 图片要求为64*64dp的图标
    // 可设计一张64*64px放在drawable-mdpi下
    // 注意图片四周留空，不至于显示太拥挤
    private String largeIcon;
    // 可选，通知栏大图标的URL链接。该字段的优先级大于largeIcon
    // 厂商通道消息，目前只支持华为，链接需要以https开头不符合此要求则通过华为通道下发时不展示该图标。[华为推送](https://developer.huawei.com/consumer/cn/doc/development/HMSCore-References-V5/https-send-api-0000001050986197-V5#ZH-CN_TOPIC_0000001050986197__section165641411103315 "华为推送")搜索“图片”即可找到关于该参数的说明
    // 该字段要求以http或者https开头，图片建议不大于100KB。
    private String img;
    // 消息下方展示大图，支持自有通道消息展示
    // 厂商通道展示大图目前仅支持小米,要求图片为固定876*324px,仅处理在友盟推送后台上传的图片。如果上传的图片不符合小米的要求，则通过小米通道下发的消息不展示该图片，其他要求请参考小米推送文档[小米富媒体推送](https://dev.mi.com/console/doc/detail?pId=1278#_3_3 "小米富媒体推送")
    private String expand_image;
    // 自定义通知声音:
    // 可选，通知声音，R.raw.[sound]
    // 如果该字段为空，采用SDK默认的声音，即res/raw/下的
    // umeng_push_notification_default_sound声音文件。如果SDK默认声音文件不存在，则使用系统默认Notification提示音
    private String sound;
    // 自定义通知样式
    // 可选，默认为0，用于标识该通知采用的样式。使用该参数时
    // 开发者必须在SDK里面实现自定义通知栏样式
    // 角标，当前只支持华为厂商通道
    private String builder_id;
    //可选，没有默认值。角标设置数字，范围为1-99。如果设置的值不在此区间该参数值将被忽略，需配合main_activity使用，具体说明参考main_activity
    // 通知到达设备后的提醒方式(注意，"true/false"为字符串):
    private String badge = "5";
    // 可选，收到通知是否震动，默认为"true"
    private String play_vibrate = "true";
    // "play_lights":"true/false",    // 可选，收到通知是否闪灯，默认为"true"
    private String play_lights = "true";
    // 可选，收到通知是否发出声音，默认为"true"
    private String play_sound = "true";
    //点击"通知"的后续行为(默认为打开app):
    // "after_open":"xx",
    // 可选，默认为"go_app"，值可以为:
        // "go_app":打开应用
        // "go_url":跳转到URL
        // "go_activity":打开特定的activity
        // "go_custom":用户自定义内容
    private String after_open;
    // 当after_open=go_url时，必填
    // 通知栏点击后跳转的URL，要求以http或者https开头
    private String url;
    //"activity":"xx",
    // 当after_open=go_activity时，必填。
    private String activity;
    // 当display_type=message时,必填
    // "custom":"xx"/{},
    // 当display_type=notification且after_open=go_custom时，必填
    // 用户自定义内容，可以为字符串或者JSON格式。
    private String custom;
}
