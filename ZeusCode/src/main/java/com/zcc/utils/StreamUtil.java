package com.zcc.utils;

import org.springframework.beans.BeanUtils;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @ProjectName: building-lease
 * @ClassName: com.eazytec.utils
 * @author: zcc
 * @date: 2022/8/16 20:27
 * @version:
 * @Describe:
 */
public class StreamUtil {

    public static <T, R, U, K> void setField(Collection<T> target, R resource, BiConsumer<T, R> consumer, Function<T, K> functionT, Function<U, K> functionR) {
        Objects.requireNonNull(resource);
        if (resource instanceof Collection) {
            Map<K, R> listMap = (Map<K, R>) ((Collection) resource).stream().collect(Collectors.groupingBy(functionR));
            target.forEach(item -> {
                K id = functionT.apply(item);
                if (listMap.containsKey(id)) {
                    consumer.accept(item, listMap.get(id));
                }
            });
        }
    }


    public static <T, R> Map<R, List<T>> getMap(Collection<T> collection, Function<T, R> function) {
        if (collection.isEmpty() || Objects.isNull(collection)) {
            return null;
        }
        Map<R, List<T>> map = collection
                .stream()
                .collect(Collectors.groupingBy(item -> function.apply(item)));
        return map;
    }

    public static <T, R> List<R> getField(Collection<T> collection, Function<T, R> function) {
        if (collection.isEmpty() || Objects.isNull(collection)) {
            return null;
        }
        List<R> collect = collection
                .stream()
                .map(function)
                .distinct()
                .collect(Collectors.toList());
        return collect;
    }

    public static <T, R> List<R> getField(Collection<T> collection, Function<T, R> function, Predicate<T> predicate) {
        if (collection.isEmpty() || Objects.isNull(collection)) {
            return null;
        }
        List<R> collect = collection
                .stream()
                .filter(predicate)
                .map(function)
                .distinct()
                .collect(Collectors.toList());
        return collect;
    }


    public static <T, R> List<R> getNestField(Collection<T> collection, Function<T, Stream<R>> function) {
        if (collection.isEmpty() || Objects.isNull(collection)) {
            return null;
        }
        List<R> collect = collection
                .stream()
                .flatMap(function)
                .distinct()
                .collect(Collectors.toList());
        return collect;
    }

    public static <T, R> List<R> getNestField(Collection<T> collection, Function<T, Stream<R>> function, Predicate<T> predicate) {
        if (collection.isEmpty() || Objects.isNull(collection)) {
            return null;
        }
        List<R> collect = collection
                .stream()
                .filter(predicate)
                .flatMap(function)
                .distinct()
                .collect(Collectors.toList());
        return collect;
    }



    public static <T, R> void getCopyList(Collection<T> collectionA, Collection<R> collectionB, Class<R> rClass) throws Exception {
        if (collectionA.isEmpty() || Objects.isNull(collectionA)) {
            throw new NullPointerException();
        }
        for (T t : collectionA) {
            R r = rClass.newInstance();
            BeanUtils.copyProperties(t, r);
            collectionB.add(r);
        }
    }

    public static <T> List<T> filter(Collection<T> collection, Predicate<T> predicate) {
        if (collection.isEmpty() || Objects.isNull(collection)) {
            return null;
        }
        return collection.stream()
                .filter(item -> predicate.test(item))
                .collect(Collectors.toList());
    }


    public static <T> List<T> strToList(String str, Function<String, T> function) {
        if (str.isEmpty() || str == null) {
            return null;
        }
        return Arrays.stream(str.split(",")).distinct().map(function).collect(Collectors.toList());
    }

    public static <T> String collectionToStr(Collection<T> collection) {
        if (collection.isEmpty() || collection == null) {
            return null;
        }
        return collection.stream().distinct().map(String::valueOf).collect(Collectors.joining(","));
    }

    public static <T> boolean isIntersection(Collection<T> a, Collection<T> b) {
        return a.stream().anyMatch(item -> b.contains(item));
    }

    public static <T> boolean isContains(Collection<T> a, Collection<T> b) {
        return b.stream().allMatch(item -> a.contains(item));
    }


}
