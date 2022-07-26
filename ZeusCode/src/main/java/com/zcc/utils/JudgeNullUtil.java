package com.zcc.utils;


import java.util.Collection;
import java.util.Map;
import java.util.Objects;
import java.util.function.Predicate;

/**
 * @ProjectName: ORCode
 * @ClassName: com.zcc.utils
 * @author: zcc
 * @date: 2022/3/31 13:08
 * @version:
 * @Describe: 判空工具类
 */
public class JudgeNullUtil {
    private static Predicate<Object> isNull = Objects::isNull;
    private static Predicate<Object> isNotNull = Objects::nonNull;
    private static Predicate<Collection> zeroSizeCollection = collection -> collection.size() == 0;
    private static Predicate<String> zeroLength = str -> str.length() == 0;
    private static Predicate<Map> zeroSizeMap = Map::isEmpty;
    private static Predicate<Collection> collectionIsNull = collection -> isNull.test(collection) || zeroSizeCollection.test(collection);
    private static Predicate<String> stringIsNull = str -> isNull.test(str) || zeroLength.test(str);
    private static Predicate<Map> mapIsNull = map -> isNull.test(map) || zeroSizeMap.test(map);
    private static Predicate<Collection> collectionIsNotNull = collection -> collectionIsNull.negate().test(collection);
    private static Predicate<String> stringIsNotNull = str -> stringIsNull.negate().test(str);
    private static Predicate<Map> mapIsNotNull = map -> mapIsNull.negate().test(map);

    public static <T> boolean isNotNull(T t) {
        if (t instanceof Collection) {
            Collection collection = (Collection) t;
            return collectionIsNotNull.test(collection);
        }
        if (t instanceof String) {
            String str = (String) t;
            return stringIsNotNull.test(str);
        }
        if (t instanceof Map) {
            Map map = (Map) t;
            return mapIsNotNull.test(map);
        }
        return isNotNull.test(t);
    }

    public static <T> boolean isNull(T t) {
        if (t instanceof Collection) {
            Collection collection = (Collection) t;
            return collectionIsNull.test(collection);
        }
        if (t instanceof String) {
            String str = (String) t;
            return stringIsNull.test(str);
        }
        if (t instanceof Map) {
            Map map = (Map) t;
            return mapIsNull.test(map);
        }
        return isNull.test(t);
    }
}
