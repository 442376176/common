package com.zcc.utils.send.UPush.IOS;

import lombok.Data;

/**
 * @author zcc
 * @version 1.0
 * @date 2021/11/24 11:56
 */
@Data
public class IOSPushJson {
    // 必填，应用唯一标识
    private String appkey;
    // 必填，时间戳，10位或者13位均可，时间戳有效期为10分钟
    private String timestamp;
    /**
     * "type":"xx",    // 必填，消息发送类型,其值可以为:
     * unicast-单播
     * listcast-列播，要求不超过500个device_token
     * filecast-文件播，多个device_token可通过文件形式批量发送
     * broadcast-广播
     * groupcast-组播，按照filter筛选用户群,请参照filter参数
     * customizedcast，通过alias进行推送，包括以下两种case:
     * -alias:对单个或者多个alias进行推送
     * -file_id:将alias存放到文件后，根据file_id来推送
     */
    private String type;

    // "device_tokens":"xx",
    // 当type=unicast时,必填,表示指定的单个设备
    // 当type=listcast时,必填,要求不超过500个,以英文逗号分隔
    private String device_tokens;
    // "alias_type":"xx",
    // 当type=customizedcast时,必填
    // alias的类型, alias_type可由开发者自定义,开发者在SDK中调用setAlias(alias, alias_type)时所设置的alias_type
    private String alias_type;
    // "alias":"xx",
    // 当type=customizedcast时,选填(此参数和file_id二选一)
    // 开发者填写自己的alias,要求不超过500个alias,多个alias以英文逗号间隔
    // 在SDK中调用setAlias(alias, alias_type)时所设置的alias
    private String alias;
    //   "file_id":"xx",
    // 当type=filecast时，必填，file内容为多条device_token，以回车符分割
    // 当type=customizedcast时，选填(此参数和alias二选一)
    // file内容为多条alias，以回车符分隔。注意同一个文件内的alias所对应的alias_type必须和接口参数alias_type一致
    // 使用文件播需要先调用文件上传接口获取file_id，参照"文件上传"
    private String file_id;
    //  "filter":{},
    // 当type=groupcast时，必填，用户筛选条件，如用户标签、渠道等，参考附录G
    // filter的内容长度最大为3000B
    private String filter;
    // 必填，JSON格式，具体消息内容(iOS最大为2012B)
    private IPlayLoad payload;
    // 可选，发送策略
    private IPolicy policy;
    // 可选，正式/测试模式。默认为true
    // 测试模式只对“广播”、“组播”类消息生效，其他类型的消息任务（如“文件播”）不会走测试模式
    // 测试模式只会将消息发给测试设备。测试设备需要到web上添加

    // 可选，发送消息描述，建议填写接口
    private String description;

}
