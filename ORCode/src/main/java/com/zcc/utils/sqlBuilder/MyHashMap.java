package com.zcc.utils.sqlBuilder;

import java.util.*;

/**
 * @author zcc
 * @date 2021/07/05
 */

//定义一个HashMapSon类，它继承HashMap类
public class MyHashMap<K, V> extends HashMap<K, V> {

    private static final long serialVersionUID = -5894887960346129860L;

    // 重写HashMapSon类的toString()方法
    @Override
    public String toString() {
        Set<Entry<K, V>> keyset = this.entrySet();
        Iterator<Entry<K, V>> i = keyset.iterator();
        if (!i.hasNext())
            return "";
        StringBuffer buffer = new StringBuffer();
        for (; ; ) {
            Map.Entry<K, V> me = i.next();
            K key = me.getKey();
            V value = me.getValue();
            if (key.toString().contains("in")) {
                buffer.append(key.toString() + "(");
                if (value instanceof List) {
                    for (int j = 0; j < ((List<?>) value).size(); j++) {
                        if (((List<?>) value).get(j) instanceof String) {
                            buffer.append("'").append(((List<?>) value).get(j)).append("'").append(",");
                        } else {
                            buffer.append(((List<?>) value).get(j)).append(",");
                        }
                    }
                    buffer.deleteCharAt(buffer.lastIndexOf(",")).append(")").append(" ").append("and").append(" ");;
                }
            } else if (key.toString().contains("like")) {
                buffer.append(key.toString()).append("'%").append(value).append("%'").append(" ").append("and").append(" ");;
            } else if (key.toString().contains("<")||key.toString().contains(">") || key.toString().contains("between")){
                buffer.append(key.toString()).append(value).append(" ").append("and").append(" ");;
            } else {
                buffer.append(key.toString() + "=");
                if (value instanceof String) {
                    buffer.append("'").append(value.toString()).append("'").append(" ").append("and").append(" ");
                } else {
                    buffer.append(value.toString()).append(" ").append("and").append(" ");
                }
            }
            if (!i.hasNext())
                return buffer.toString().substring(0, buffer.toString().length() - 5);
        }
    }



}
