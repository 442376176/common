package com.zcc.utils;

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
        if (collection == null || collection.isEmpty()) {
            return null;
        }
        Map<R, List<T>> map = collection
                .stream()
                .collect(Collectors.groupingBy(item -> function.apply(item)));
        return map;
    }

    public static <T, R, V> Map<R, List<V>> getMap(Collection<T> collection, Function<T, R> function, Function<T, V> getField) {
        if (collection == null || collection.isEmpty()) {
            return null;
        }
        Map<R, List<V>> map = collection
                .stream()
                .collect(Collectors.groupingBy(t -> function.apply(t), Collectors.mapping(t -> getField.apply(t), Collectors.toList())));
        return map;
    }

    public static <T, R> List<R> getField(Collection<T> collection, Function<T, R> function) {
        if (collection == null || collection.isEmpty()) {
            return null;
        }
        List<R> collect = collection
                .stream()
                .map(function)
                .distinct()
                .collect(Collectors.toList());
        return collect;
    }

    public static <T, R,K> List<R> getOtherTypeField(Collection<T> collection, Function<T, K> functionA,Function<K, R> functionB) {
        if (collection == null || collection.isEmpty()) {
            return null;
        }
        List<R> collect = collection
                .stream()
                .map(item->functionB.apply(functionA.apply(item)))
                .distinct()
                .collect(Collectors.toList());
        return collect;
    }

    public static <T, R> List<R> getField(Collection<T> collection, Function<T, R> function, Predicate<T> predicate) {
        if (collection == null || collection.isEmpty()) {
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
        if (collection == null || collection.isEmpty()) {
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
        if (collection == null || collection.isEmpty()) {
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
    public static <T> List<T> filter(Collection<T> collection, Predicate<T> predicate) {
        if (collection == null || collection.isEmpty()) {
            return null;
        }
        return collection.stream()
                .filter(item -> predicate.test(item))
                .collect(Collectors.toList());
    }


    public static <T> List<T> strToList(String str, Function<String, T> function) {
        if (str == null || str.isEmpty()) {
            return null;
        }
        return Arrays.stream(str.split(",")).distinct().map(function).collect(Collectors.toList());
    }

    public static <T> String collectionToStr(Collection<T> collection) {
        if (collection == null || collection.isEmpty()) {
            return null;
        }
        return collection.stream().distinct().map(String::valueOf).collect(Collectors.joining(","));
    }

    public static <T> boolean isIntersection(Collection<T> a, Collection<T> b) {
        if (a == null || a.isEmpty() || b == null || b.isEmpty()) {
            return Boolean.FALSE;
        }
        return a.stream().anyMatch(item -> b.contains(item));
    }

    public static <T> boolean isContains(Collection<T> a, Collection<T> b) {
        if (a == null || a.isEmpty() || b == null || b.isEmpty()) {
            return Boolean.FALSE;
        }
        return b.stream().allMatch(item -> a.contains(item));
    }


}
