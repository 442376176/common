package com.zcc.utils.lock;

/**
 * @ProjectName: ORCode
 * @ClassName: com.zcc.utils.lock
 * @author: zcc
 * @date: 2022/6/14 9:08
 * @version:
 * @Describe:
 */

import com.alibaba.fastjson.JSON;
import com.zcc.utils.MD5Utils;

import java.util.Map;

/**
 * lockKey默认生成策略
 */
public class ApiLockKeyDefaultGenerateStrategy implements ApiLockKeyGenerateStrategy {

    @Override
    public String generateKey(String prefix, Map<String, Object> methodNotNullArgsMap) {
        // 拿到所有参数后，lockKey的生成逻辑可以自定义实现
        return prefix + MD5Utils.md52(JSON.toJSONString(methodNotNullArgsMap));
    }
}
