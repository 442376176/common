package com.zcc.segmentTree;

/**
 * @author zcc
 * @version 1.0
 * @date 2021/12/15 17:25
 */
public interface Merger<E> {
    E merge(E a , E b);
}
