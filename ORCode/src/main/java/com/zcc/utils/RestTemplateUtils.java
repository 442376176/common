package com.zcc.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zcc.utils.send.UPush.UPushRes;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author yangjianfei
 * @ClassName: RestTemplateUtils
 * @description:
 */
public class RestTemplateUtils {

    public static RestTemplate getInstance(String charset) {
        RestTemplate restTemplate = new RestTemplate();
        SimpleClientHttpRequestFactory httpComponentsClientHttpRequestFactory =
                (SimpleClientHttpRequestFactory) restTemplate.getRequestFactory();
        httpComponentsClientHttpRequestFactory.setConnectTimeout(10000);
        httpComponentsClientHttpRequestFactory.setReadTimeout(10000);
        List<HttpMessageConverter<?>> list = restTemplate.getMessageConverters();
        for (HttpMessageConverter<?> httpMessageConverter : list) {
            if (httpMessageConverter instanceof StringHttpMessageConverter) {
                ((StringHttpMessageConverter) httpMessageConverter).setDefaultCharset(Charset.forName(charset));
                break;
            }
        }
        return restTemplate;
    }


    /**
     * @param prefix  前缀域名
     * @param suffix  后缀路由
     * @param headers 头部参数
     * @param params  路径参数
     * @Expanation: get请求 返回List
     * @Author 朱志鹏
     * @Return List(T) 返回类型
     * @Date 2021/8/16 下午9:51
     */
    public static <T> List<T> getRequestList(String prefix, String suffix, HttpHeaders headers, HashMap<String, Object> params, Class<T> aClass) throws BuildingLeaseServiceException {
        ResponseEntity<JSONObject> exchange = getRequest(prefix, suffix, headers, params);
        if (exchange != null && exchange.getBody() != null) {
            ResultData resultData = JSON.toJavaObject(exchange.getBody(), ResultData.class);
            //返回失败抛出异常
            if (!"0".equals(resultData.getCode())) {
                throw new BuildingLeaseServiceException(resultData.getCode(), resultData.getMsg());
            }

            Object data = resultData.getData();
            if (data != null) {
                List<T> list = JSON.parseArray(JSON.toJSONString(data), aClass);
                return list;
            }
        }
        return new ArrayList<T>();
    }

    /**
     * @param prefix  前缀域名
     * @param suffix  后缀路由
     * @param headers 头部参数
     * @param params  路径参数
     * @Expanation: get请求 返回对象
     * @Author 朱志鹏
     * @Return (T) 返回类型
     * @Date 2021/8/16 下午9:51
     */
    public static <T> T getRequestBean(String prefix, String suffix, HttpHeaders headers, HashMap<String, Object> params, Class<T> aClass) throws BuildingLeaseServiceException {
        ResponseEntity<JSONObject> exchange = getRequest(prefix, suffix, headers, params);
        if (exchange != null && exchange.getBody() != null) {
            ResultData resultData = JSON.toJavaObject(exchange.getBody(), ResultData.class);
            //返回失败抛出异常
            if (!"0".equals(resultData.getCode())) {
                throw new BuildingLeaseServiceException(resultData.getCode(), resultData.getMsg());
            }
            Object data = resultData.getData();
            if (data != null) {
                T t = JSON.parseObject(JSON.toJSONString(data), aClass);
                return t;
            }
        }
        return null;
    }

    /**
     * @param prefix  前缀域名
     * @param suffix  后缀路由
     * @param headers 头部参数
     * @param params  路径参数
     * @Expanation: get请求 返回Map
     * @Author 朱志鹏
     * @Return Map 返回Map
     * @Date 2021/8/16 下午9:51
     */
    public static Map getRequestMap(String prefix, String suffix, HttpHeaders headers, HashMap<String, Object> params) throws BuildingLeaseServiceException {
        ResponseEntity<JSONObject> exchange = getRequest(prefix, suffix, headers, params);
        if (exchange != null && exchange.getBody() != null) {
            ResultData resultData = JSON.toJavaObject(exchange.getBody(), ResultData.class);
            Object data = resultData.getData();
            //返回失败抛出异常
            if (!"0".equals(resultData.getCode())) {
                throw new BuildingLeaseServiceException(resultData.getCode(), resultData.getMsg());
            }
            if (data != null) {
                HashMap hashMap = JSON.parseObject(JSON.toJSONString(data), HashMap.class);
                return hashMap;
            }
        }
        return new HashMap();
    }

    /**
     * @param prefix  前缀域名
     * @param suffix  后缀路由
     * @param headers 头部参数
     * @param body    对象参数
     * @param aClass  需要返回的类型
     * @Expanation: post请求 返回list
     * @Author 朱志鹏
     * @Return List (T)  返回类型
     * @Date 2021/8/16 下午9:51
     */
    public static <T> List<T> postRequestList(String prefix, String suffix, HttpHeaders headers, Object body, Class<T> aClass) throws BuildingLeaseServiceException {
        ResponseEntity<JSONObject> exchange = postRequest(prefix, suffix, headers, body);
        if (exchange != null && exchange.getBody() != null) {
            ResultData resultData = JSON.toJavaObject(exchange.getBody(), ResultData.class);
            Object data = resultData.getData();
            //返回失败抛出异常
            if (!"0".equals(resultData.getCode())) {
                throw new BuildingLeaseServiceException(resultData.getCode(), resultData.getMsg());
            }
            if (data != null) {
                List<T> list = JSON.parseArray(JSON.toJSONString(data), aClass);
                return list;
            }
        }
        return new ArrayList<T>();
    }

    /**
     * @param prefix  前缀域名
     * @param suffix  后缀路由
     * @param headers 头部参数
     * @param body    对象参数
     * @param aClass  需要返回的类型
     * @Expanation: post请求 返回单对象
     * @Author 朱志鹏
     * @Return (T) 返回类型
     * @Date 2021/8/16 下午9:51
     */
    public static <T> T postRequestBean(String prefix, String suffix, HttpHeaders headers, Object body, Class<T> aClass) throws BuildingLeaseServiceException {
        ResponseEntity<JSONObject> exchange = postRequest(prefix, suffix, headers, body);
        if (exchange != null && exchange.getBody() != null) {
            ResultData resultData = JSON.toJavaObject(exchange.getBody(), ResultData.class);
            //返回失败抛出异常信息
            if (!"0".equals(resultData.getCode())) {
                throw new BuildingLeaseServiceException(resultData.getCode(), resultData.getMsg());
            }
            Object data = resultData.getData();
            if (data != null) {
                T t = JSON.parseObject(JSON.toJSONString(data), aClass);
                return t;
            }
        }
        return null;
    }


    /**
     * @param prefix  前缀域名
     * @param suffix  后缀路由
     * @param headers 头部参数
     * @param body    对象参数
     * @Expanation: post请求 无指定返回类型
     * @Author 朱志鹏
     * @Return ResultData 返回类型
     * @Date 2021/8/16 下午9:51
     */
    public static ResultData postRequestVoid(String prefix, String suffix, HttpHeaders headers, Object body) throws BuildingLeaseServiceException {
        ResponseEntity<JSONObject> exchange = postRequest(prefix, suffix, headers, body);
        if (exchange != null && exchange.getBody() != null) {
            ResultData resultData = JSON.toJavaObject(exchange.getBody(), ResultData.class);
            //返回失败抛出异常信息
            if (!"0".equals(resultData.getCode())) {
                throw new BuildingLeaseServiceException(resultData.getCode(), resultData.getMsg());
            }
            return resultData;
        }
        return null;
    }

    /**
     * @param prefix  前缀域名
     * @param suffix  后缀路由
     * @param headers 头部参数
     * @param body    对象参数
     * @Expanation: post请求 返回Map
     * @Author 朱志鹏
     * @Return Map 返回Map
     * @Date 2021/8/16 下午9:51
     */
    public static Map postRequestMap(String prefix, String suffix, HttpHeaders headers, Object body) throws BuildingLeaseServiceException {
        ResponseEntity<JSONObject> exchange = postRequest(prefix, suffix, headers, body);
        if (exchange != null && exchange.getBody() != null) {
            ResultData resultData = JSON.toJavaObject(exchange.getBody(), ResultData.class);
            //返回失败抛出异常信息
            if (!"0".equals(resultData.getCode())) {
                throw new BuildingLeaseServiceException(resultData.getCode(), resultData.getMsg());
            }
            if (resultData.getData() != null) {
                Object data = resultData.getData();
                HashMap map = JSON.parseObject(JSON.toJSONString(data), HashMap.class);
                return map;
            }
        }
        return new HashMap();
    }


    /***
     * post
     * @param prefix 前缀域名
     * @param suffix 后缀路由
     * @param headers 头部参数
     * @param body 对象参数
     * @return
     */
    private static ResponseEntity<JSONObject> postRequest(String prefix, String suffix, HttpHeaders headers, Object body) {
        RestTemplate instance = getInstance("UTF-8");
        if (!suffix.startsWith("/")) {
            suffix = "/" + suffix;
        }
        StringBuilder url = new StringBuilder();
        url.append(prefix).append(suffix);
        if (headers == null)
            headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        ResponseEntity<JSONObject> exchange = instance.exchange(url.toString(), HttpMethod.POST, new HttpEntity<>(JSON.toJSONString(body), headers), JSONObject.class);
        System.out.println(exchange);
        return exchange;
    }

    /**
     * @param prefix  前缀域名
     * @param suffix  后缀路由
     * @param headers 头部参数
     * @param params  路径参数
     * @Expanation: get
     * @Author 朱志鹏
     * @Date 2021/8/16 下午9:51
     */
    private static ResponseEntity<JSONObject> getRequest(String prefix, String suffix, HttpHeaders headers, HashMap<String, Object> params) {
        RestTemplate instance = getInstance("UTF-8");
        if (!suffix.startsWith("/")) {
            suffix = "/" + suffix;
        }

        if (headers == null)
            headers = new HttpHeaders();

        StringBuilder url = new StringBuilder();
        url.append(prefix).append(suffix);

        if (params != null && params.size() > 0) {
            String param = params.entrySet().stream().map(m -> m.getKey() + "=" + replace(m.getValue().toString())).collect(Collectors.joining("&"));
            url.append("?").append(param);
        }
        return instance.exchange(url.toString(), HttpMethod.GET, new HttpEntity<>(headers), JSONObject.class);
    }

    private static String replace(String value) {

        return value.replace("[", "").replace("]", "").replaceAll(" ", "");
    }

    /**
     * @param prefix  前缀域名
     * @param suffix  后缀路由
     * @param headers 头部参数
     * @param params  路径参数
     * @Expanation: get请求 返回List
     * @Author 朱志鹏
     * @Return List(T) 返回类型
     * @Date 2021/8/16 下午9:51
     */
    public static <T> List<T> getRequestListBiz(String prefix, String suffix, HttpHeaders headers, HashMap<String, Object> params, Class<T> aClass) throws BuildingLeaseServiceException {
        ResponseEntity<JSONObject> exchange = getRequest(prefix, suffix, headers, params);
        if (exchange != null && exchange.getBody() != null) {
            BizRes resultData = getData(exchange);
            Object data = resultData.getData();
            if (data != null) {
                List<T> list = JSON.parseArray(JSON.toJSONString(data), aClass);
                return list;
            }
        }
        return new ArrayList<T>();
    }

    /**
     * @param prefix  前缀域名
     * @param suffix  后缀路由
     * @param headers 头部参数
     * @param params  路径参数
     * @Expanation: get请求 返回对象
     * @Author 朱志鹏
     * @Return (T) 返回类型
     * @Date 2021/8/16 下午9:51
     */
    public static <T> T getRequestBeanBiz(String prefix, String suffix, HttpHeaders headers, HashMap<String, Object> params, Class<T> aClass) throws BuildingLeaseServiceException {
        ResponseEntity<JSONObject> exchange = getRequest(prefix, suffix, headers, params);
        if (exchange != null && exchange.getBody() != null) {
            BizRes resultData = getData(exchange);
            Object data = resultData.getData();
            if (data != null) {
                T t = JSON.parseObject(JSON.toJSONString(data), aClass);
                return t;
            }
        }
        return null;
    }

    /**
     * @param prefix  前缀域名
     * @param suffix  后缀路由
     * @param headers 头部参数
     * @param params  路径参数
     * @Expanation: get请求 返回Map
     * @Author 朱志鹏
     * @Return Map 返回Map
     * @Date 2021/8/16 下午9:51
     */
    public static Map getRequestMapBiz(String prefix, String suffix, HttpHeaders headers, HashMap<String, Object> params) throws BuildingLeaseServiceException {
        ResponseEntity<JSONObject> exchange = getRequest(prefix, suffix, headers, params);
        if (exchange != null && exchange.getBody() != null) {
            BizRes resultData = getData(exchange);
            Object data = resultData.getData();
            //返回失败抛出异常
            if (! "200".equals(resultData.getStatus().toString())) {
                throw new BuildingLeaseServiceException(resultData.getStatus().toString(), resultData.getMsg());
            }
            if (data != null) {
                HashMap hashMap = JSON.parseObject(JSON.toJSONString(data), HashMap.class);
                return hashMap;
            }
        }
        return new HashMap();
    }

    /**
     * @param prefix  前缀域名
     * @param suffix  后缀路由
     * @param headers 头部参数
     * @param body    对象参数
     * @param aClass  需要返回的类型
     * @Expanation: post请求 返回list
     * @Author 朱志鹏
     * @Return List (T)  返回类型
     * @Date 2021/8/16 下午9:51
     */
    public static <T> List<T> postRequestListBiz(String prefix, String suffix, HttpHeaders headers, Object body, Class<T> aClass) throws BuildingLeaseServiceException {
        ResponseEntity<JSONObject> exchange = postRequest(prefix, suffix, headers, body);
        if (exchange != null && exchange.getBody() != null) {
            BizRes resultData = getData(exchange);
            Object data = resultData.getData();
            //返回失败抛出异常
            if (!"0".equals(resultData.getStatus())) {
                throw new BuildingLeaseServiceException(resultData.getStatus().toString(), resultData.getMsg());
            }
            if (data != null) {
                List<T> list = JSON.parseArray(JSON.toJSONString(data), aClass);
                return list;
            }
        }
        return new ArrayList<T>();
    }

    /**
     * @param prefix  前缀域名
     * @param suffix  后缀路由
     * @param headers 头部参数
     * @param body    对象参数
     * @param aClass  需要返回的类型
     * @Expanation: post请求 返回单对象
     * @Author 朱志鹏
     * @Return (T) 返回类型
     * @Date 2021/8/16 下午9:51
     */
    public static <T> T postRequestBeanBiz(String prefix, String suffix, HttpHeaders headers, Object body, Class<T> aClass) throws BuildingLeaseServiceException {
        ResponseEntity<JSONObject> exchange = postRequest(prefix, suffix, headers, body);
        if (exchange != null && exchange.getBody() != null) {
            BizRes resultData = getData(exchange);
            Object data = resultData.getData();
            if (data != null) {
                T t = JSON.parseObject(JSON.toJSONString(data), aClass);
                return t;
            }
        }
        return null;
    }


    /**
     * @param prefix  前缀域名
     * @param suffix  后缀路由
     * @param headers 头部参数
     * @param body    对象参数
     * @Expanation: post请求 无指定返回类型
     * @Author 朱志鹏
     * @Return ResultData 返回类型
     * @Date 2021/8/16 下午9:51
     */
    public static BizRes postRequestVoidBiz(String prefix, String suffix, HttpHeaders headers, Object body) throws BuildingLeaseServiceException {
        ResponseEntity<JSONObject> exchange = postRequest(prefix, suffix, headers, body);
        if (exchange != null && exchange.getBody() != null) {
            return getData(exchange);
        }
        return null;
    }
    /**
     * @param prefix  前缀域名
     * @param suffix  后缀路由
     * @param headers 头部参数
     * @param params    对象参数
     * @Expanation: post请求 无指定返回类型
     * @Author 朱志鹏
     * @Return ResultData 返回类型
     * @Date 2021/8/16 下午9:51
     */
    public static BizRes getRequestVoidBiz(String prefix, String suffix, HttpHeaders headers, HashMap<String, Object> params) throws BuildingLeaseServiceException {
        ResponseEntity<JSONObject> exchange = getRequest(prefix, suffix, headers, params);
        if (exchange != null && exchange.getBody() != null) {
            return getData(exchange);
        }
        return null;
    }
    /**
     * @param prefix  前缀域名
     * @param suffix  后缀路由
     * @param headers 头部参数
     * @param body    对象参数
     * @Expanation: post请求 返回Map
     * @Author 朱志鹏
     * @Return Map 返回Map
     * @Date 2021/8/16 下午9:51
     */
    public static Map postRequestMapBiz(String prefix, String suffix, HttpHeaders headers, Object body) throws BuildingLeaseServiceException {
        ResponseEntity<JSONObject> exchange = postRequest(prefix, suffix, headers, body);
        if (exchange != null && exchange.getBody() != null) {
            BizRes resultData = getData(exchange);
            if (resultData.getData() != null) {
                Object data = resultData.getData();
                HashMap map = JSON.parseObject(JSON.toJSONString(data), HashMap.class);
                return map;
            }
        }
        return new HashMap();
    }


    protected static BizRes getData(ResponseEntity<JSONObject> exchange) throws BuildingLeaseServiceException {
        BizRes resultData = JSON.toJavaObject(exchange.getBody(), BizRes.class);
        //返回失败抛出异常
        if (200 != resultData.getStatus()) {
            throw new BuildingLeaseServiceException(resultData.getStatus().toString(), resultData.getMsg());
        }
        return resultData;
    }


    /**
     * @param prefix  前缀域名
     * @param suffix  后缀路由
     * @param headers 头部参数
     * @param body    对象参数
     * @param aClass  需要返回的类型
     * @Expanation: post请求 返回单对象
     * @Author 朱志鹏
     * @Return (T) 返回类型
     * @Date 2021/8/16 下午9:51
     */
    public static <T> T postRequestBeanUPush(String prefix, String suffix, HttpHeaders headers, Object body, Class<T> aClass) throws BuildingLeaseServiceException {
        ResponseEntity<JSONObject> exchange = postRequest(prefix, suffix, headers, body);
        if (exchange != null && exchange.getBody() != null) {
            UPushRes resultData = JSON.toJavaObject(exchange.getBody(), UPushRes.class);
            //返回失败抛出异常
            if ("false".equals(resultData.getRet())) {
                throw new BuildingLeaseServiceException(resultData.getData().getError_code(), resultData.getData().getError_code());
            }
            Object data = resultData.getData();
            if (data != null) {
                T t = JSON.parseObject(JSON.toJSONString(data), aClass);
                return t;
            }
        }
        return null;
    }

    /**
     * @param prefix  前缀域名
     * @param suffix  后缀路由
     * @param headers 头部参数
     * @param params  路径参数
     * @Expanation: get请求 返回对象
     * @Author zcc
     * @Return (T) 返回类型
     * @Date 2021/11/24 16:51
     */
    public static <T> T postRequestBeanUPush(String prefix, String suffix, HttpHeaders headers, HashMap<String, Object> params, Class<T> aClass) throws BuildingLeaseServiceException {
        ResponseEntity<JSONObject> exchange = getRequest(prefix, suffix, headers, params);
        if (exchange != null && exchange.getBody() != null) {
            UPushRes resultData = JSON.toJavaObject(exchange.getBody(), UPushRes.class);
            //返回失败抛出异常
            if ("false".equals(resultData.getRet())) {
                throw new BuildingLeaseServiceException(resultData.getData().getError_code(), resultData.getData().getError_code());
            }
            Object data = resultData.getData();
            if (data != null) {
                T t = JSON.parseObject(JSON.toJSONString(data), aClass);
                return t;
            }
        }
        return null;
    }

    public static void main(String[] args) throws Exception{
        for (int i = 0; i < 5; i++) {
            System.out.println(getInstance("UTF-8")
                    .getForObject("http://localhost:8085/consume/test/feign",String.class));
        }
    }

}
