package com.zcc.utils.regex;

/**
 * @ProjectName: ZeusCode
 * @ClassName: com.zcc.utils.regex
 * @author: zcc
 * @date: 2022/8/16 18:45
 * @version:
 * @Describe:
 */
public class RegexPatterns {

    /**
     * 手机号正则
     */
    public static final String PHONE_REGEX = "^1([38][0-9]|4[579]|5[0-3,5-9]|6[6]|7[0135678]|9[89])\\d{8}$";
    /**
     * 邮箱正则
     */
    public static final String EMAIL_REGEX = "^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$";
    /**
     * 用户名正则
     */
    public static final String USERNAME_REGEX = "^\\w{4,32}$";
    /**
     * 数字、26个英文字母或者下划线组成
     * 文件名称正则
     */
    public static final String FILENAME_REGEX = "^\\w+$";
    /**
     * 汉字英文数字 正则
     * 长度 1-20
     */
    public static final String RESO_NAME_REGEX = "^[\\u4e00-\\u9fa5_a-zA-Z0-9]{1,20}$";


}
