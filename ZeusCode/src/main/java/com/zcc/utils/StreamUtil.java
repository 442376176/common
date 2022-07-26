package com.zcc.utils;

import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author zcc
 * @version 1.0
 * @date 2022/1/18 16:09
 */
public class StreamUtil {


    /**
     * 塞数据
     *
     * @param target
     * @param resource
     */
    public static <T, R, U, K> void setField(List<T> target, R resource, BiConsumer<T, R> consumer, Function<T, K> functionT, Function<U, K> functionR) {

        if (resource instanceof List) {
            Map<K, R> listMap = (Map<K, R>) ((List) resource).stream().collect(Collectors.groupingBy(functionR));
            target.forEach(item -> {
                K id = functionT.apply(item);
                if (listMap.containsKey(id)) {
                    consumer.accept(item, listMap.get(id));
                }
            });
        }
    }



    public static <T, R> Map<R, List<T>> getMap(List<T> list, Function<T, R> function) throws Exception {
        Map<R, List<T>> map = list
                .stream()
                .collect(Collectors.groupingBy(item -> function.apply(item)));
        return map;
    }

    public static <T, R> List<R> getField(List<T> list, Function<T, R> function) {
        List<R> collect = list
                .stream()
                .map(function)
                .distinct()
                .collect(Collectors.toList());
        return collect;
    }


    public static <T, R> void getCopyList(List<T> listA, List<R> listB, Class<R> rClass) throws Exception {
        for (T t : listA) {
            R r = rClass.newInstance();
            BeanUtils.copyProperties(t, r);
            listB.add(r);
        }
    }


    public <T> boolean notNull(T t) throws Exception {
        if (t instanceof String) {
            if (t == "" || t == null)
                return false;
            return true;
        }
        return Objects.nonNull(t);
    }

    public <T,R> List<T> filter(Function<T, R> function, Function<R,Boolean> compare, List<T> list)throws Exception{
        return list.stream()
                .filter(item->compare.apply(function.apply(item)))
                .collect(Collectors.toList());
    }

//    public <T,R> List<T> getList(Function<T, R> function, Function<R,Boolean> compare, List<T> list)throws Exception{
//        return list.stream()
//                .filter(item->compare.apply(function.apply(item)))
//                .
//
//    }

}
