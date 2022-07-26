package com.zcc.utils.lock;

import java.util.Map;

/**
 * @ProjectName: ORCode
 * @ClassName: com.zcc.utils.lock
 * @author: zcc
 * @date: 2022/6/14 9:05
 * @version:
 * @Describe:
 */

public interface ApiLockKeyGenerateStrategy {
    String generateKey(String prefix, Map<String, Object> params);
}
