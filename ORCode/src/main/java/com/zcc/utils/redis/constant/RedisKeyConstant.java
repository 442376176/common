package com.zcc.utils.redis.constant;

/**
 * @author zcc
 * @version 1.0
 * @date 2021/12/9 13:48
 */

public enum RedisKeyConstant {
    USER_GEO_KEY("customerGeo");
    private String key;

    RedisKeyConstant(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }

}
