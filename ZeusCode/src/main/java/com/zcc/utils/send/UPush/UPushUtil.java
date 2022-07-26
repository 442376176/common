package com.zcc.utils.send.UPush;


import com.alibaba.fastjson.JSON;
import com.zcc.utils.MD5Utils;
import com.zcc.utils.RPC.RestTemplateUtils;
import com.zcc.utils.send.UPush.Android.AndroidPushJson;
import com.zcc.utils.send.UPush.IOS.IOSPushJson;
import org.springframework.http.HttpHeaders;

/**
 * @author zcc
 * @version 1.0
 * @date 2021/11/24 11:14
 */
public class UPushUtil {


    private String url = "https://app-h5.app.zqtong.com/";

    /**
     * 安卓推送
     *
     * @param json
     * @param url
     * @param masterSecret
     * @return
     * @throws Exception
     */
    public static ResData androidPush(AndroidPushJson json, String url, String masterSecret) throws Exception {
        // 生成签名
        String body = JSON.toJSONString(json);
        String mySign = MD5Utils.md52(("POST" + "http://msg.umeng.com/api/send" + body + masterSecret).getBytes("utf8"));
        return RestTemplateUtils.postRequestBeanUPush(url, "/api/send?sign=" + mySign, null, json, ResData.class);


    }

    /**
     * ios推送
     *
     * @param json
     * @param url
     * @param masterSecret
     * @return
     * @throws Exception
     */
    public static ResData iosPush(IOSPushJson json, String url, String masterSecret) throws Exception {
        // 生成签名
        String body = JSON.toJSONString(json);
        String mySign = MD5Utils.md52("POST" + "http://msg.umeng.com/api/send" + body + masterSecret);
        HttpHeaders headers = new HttpHeaders();
        headers.set("User-Agent","Mozilla/5.0");
        return RestTemplateUtils.postRequestBeanUPush(url, "/api/send?sign=" + mySign, null, json, ResData.class);
    }


}
