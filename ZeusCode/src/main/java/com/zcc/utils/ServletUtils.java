package com.zcc.utils;


import com.zcc.utils.sqlBuilder.Convert;
import com.zcc.utils.sqlBuilder.StringUtils;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * 客户端工具类
 *
 * @author 马云亮
 */
public class ServletUtils {
    /**
     * 定义移动端请求的所有可能类型
     */
    private final static String[] agent = {"Android", "iPhone", "iPod", "iPad", "Windows Phone", "MQQBrowser"};

    /**
     * 获取String参数
     */
    public static String getParameter(String name) {
        return getRequest().getParameter(name);
    }

    /**
     * 获取String参数
     */
    public static String getParameter(String name, String defaultValue) {
        return Convert.toStr(getRequest().getParameter(name), defaultValue);
    }

    /**
     * 获取Integer参数
     */
    public static Integer getParameterToInt(String name) {
        return Convert.toInt(getRequest().getParameter(name));
    }

    /**
     * 获取Integer参数
     */
    public static Integer getParameterToInt(String name, Integer defaultValue) {
        return Convert.toInt(getRequest().getParameter(name), defaultValue);
    }

    /**
     * 获取request
     */
    public static HttpServletRequest getRequest() {
        return getRequestAttributes().getRequest();
    }

    /**
     * 获取response
     */
    public static HttpServletResponse getResponse() {
        return getRequestAttributes().getResponse();
    }

    /**
     * 获取session
     */
    public static HttpSession getSession() {
        return getRequest().getSession();
    }

    public static ServletRequestAttributes getRequestAttributes() {
        RequestAttributes attributes = RequestContextHolder.getRequestAttributes();
        return (ServletRequestAttributes) attributes;
    }

    /**
     * 将字符串渲染到客户端
     *
     * @param response 渲染对象
     * @param string   待渲染的字符串
     * @return null
     */
    public static String renderString(HttpServletResponse response, String string) {
        try {
            response.setContentType("application/json");
            response.setCharacterEncoding("utf-8");
            response.getWriter().print(string);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 是否是Ajax异步请求
     *
     * @param request
     */
    public static boolean isAjaxRequest(HttpServletRequest request) {
        String accept = request.getHeader("accept");
        if (accept != null && accept.indexOf("application/json") != -1) {
            return true;
        }

        String xRequestedWith = request.getHeader("X-Requested-With");
        if (xRequestedWith != null && xRequestedWith.indexOf("XMLHttpRequest") != -1) {
            return true;
        }

        String uri = request.getRequestURI();
        if (StringUtils.inStringIgnoreCase(uri, ".json", ".xml")) {
            return true;
        }

        String ajax = request.getParameter("__ajax");
        if (StringUtils.inStringIgnoreCase(ajax, "json", "xml")) {
            return true;
        }
        return false;
    }

    /**
     * 判断User-Agent 是不是来自于手机
     */
    public static boolean checkAgentIsMobile(String ua) {
        boolean flag = false;
        if (!ua.contains("Windows NT") || (ua.contains("Windows NT") && ua.contains("compatible; MSIE 9.0;"))) {
            // 排除 苹果桌面系统
            if (!ua.contains("Windows NT") && !ua.contains("Macintosh")) {
                for (String item : agent) {
                    if (ua.contains(item)) {
                        flag = true;
                        break;
                    }
                }
            }
        }
        return flag;
    }

//    /**
//     * 获取 请求头里面的companyId
//     *
//     * @return
//     */
//    public static String getCompanyId() {
//        HttpServletRequest request = getRequest();
//        String header = request.getHeader(HeaderConstant.COMPANY_ID);
//        return header;
//    }
//
//    /**
//     * 获取 请求头里面的companyId
//     *
//     * @return
//     */
//    public static String getbindId() {
//        HttpServletRequest request = getRequest();
//        String header = request.getHeader(HeaderConstant.BIND_ID);
//        return header;
//    }
//
//    /**
//     * 获取 请求头里面的tenantId
//     *
//     * @return
//     */
//    public static String getTenantId() {
//        HttpServletRequest request = getRequest();
//        String header = request.getHeader(HeaderConstant.TENANT_ID);
//        return header;
//    }
//
//    /**
//     * 获取 请求头里面的tenantUserId
//     *
//     * @return
//     */
//    public static String getTenantLoginUserId() {
//        HttpServletRequest request = getRequest();
//        String header = request.getHeader(HeaderConstant.TENANT_USER_ID);
//        return header;
//    }
//
//    /**
//     * 获取 请求头里面的buidings
//     *
//     * @return
//     */
//    public static String getTenantUserId() {
//        HttpServletRequest request = getRequest();
//        String header = request.getHeader(HeaderConstant.BUILDING_IDS);
//        return header;
//    }
//
//    /**
//     * 获取 请求头里面的buidings
//     *
//     * @return
//     */
//    public static String getBuildIds() {
//        HttpServletRequest request = getRequest();
//        String header = request.getHeader(HeaderConstant.BUILDING_IDS);
//        return header;
//    }
//
//    /**
//     * 获取 请求头里面的 用户id
//     *
//     * @return
//     */
//    public static String getUserId() {
//        HttpServletRequest request = getRequest();
//        String header = request.getHeader(HeaderConstant.USER_ID);
//        return header;
//    }
//
//    /**
//     * 获取 平台用户id
//     *
//     * @return
//     */
//    public static String getDetpID() {
//        HttpServletRequest request = getRequest();
//        String header = request.getHeader(HeaderConstant.DEPARTMENT_USER_ID);
//        return header;
//    }
//
//    /**
//     * 获取 请求头里面的 系统id
//     *
//     * @return
//     */
//    public static String getSystemId() {
//        HttpServletRequest request = getRequest();
//        String header = request.getHeader(HeaderConstant.SYSTEM_ID);
//        return header;
//    }
//
//    /**
//     * 获取当前页
//     *
//     * @return
//     */
//    public static Integer getPageNo() {
//        return getParameterToInt(PageConstant.PAGE_NO, 1);
//    }
//
//    /**
//     * 获取每页数量
//     *
//     * @return
//     */
//    public static Integer getPageSize() {
//        return getParameterToInt(PageConstant.PAGE_SIZE, 10);
//    }
//
//    /**
//     * 获取token
//     *
//     * @return
//     */
//    public static String getToken() {
//        HttpServletRequest request = getRequest();
//        return request.getHeader(HeaderConstant.X_ACCESS_TOKEN);
//    }
//
//    /**
//     * 设置请求头
//     *
//     * @return
//     */
//    public static HttpHeaders setHeader() {
//        HttpHeaders header = new HttpHeaders();
//        header.add("X-Access-Token", getToken());
//        return header;
//    }
}
