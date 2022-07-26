package com.zcc.utils.sqlBuilder;

import com.zcc.utils.GetAnnotationValue;

import javax.persistence.Table;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


/**
 * @author zcc
 * @date 2021/07/05
 */

public class BeanUtils {
    /**
     *  条件判空
     * @param t
     * @param <T>
     * @return
     * @throws Exception
     */
    public static  <T>Map<String,Object> isNotNullField (T t)throws Exception{
        Map<String,Object> map = new MyHashMap<>();
        Class<?> aClass = t.getClass();
//        Method[] methods = aClass.getMethods();
        List<Method> methods = new ArrayList<>();
        Field[] fields = aClass.getDeclaredFields();
        List<Field> collect = Arrays.stream(fields).filter(e -> !("serialVersionUID".equals(e.getName()))).collect(Collectors.toList());
        for (Field fi:collect
             ) {
            methods.add( aClass.getMethod("get"+ fi.getName().substring(0,1).toUpperCase()+fi.getName().substring(1,fi.getName().length())));
        }
        for (Method me:methods
        ) {
            if (!me.isAccessible()) {
                me.setAccessible(true);
            }
            Object invoke = me.invoke(t,null);
            if (!isEmpty(invoke)) {
                map.put(StringUtils.humpToLine2(StringUtils.getField(me.getName())),invoke);
            }
        }
        return map;
    }

    public static boolean isEmpty(Object obj) {
        if (obj==null) {
            return true;
        }
        if (obj=="") {
            return true;
        }
        return false;
    }

    /**
     *  查询字段加别名 别名为属性名
     * @param clazz
     * @return
     * @throws Exception
     */
    public static  Map<String,Object> AllField (Class clazz)throws Exception{
        Map<String,Object> map = new MyHashMap<>();
        Map<String, Object> fieldAnnotationValue = GetAnnotationValue.getClassAnnotationValue(clazz, Table.class);
        Object name = fieldAnnotationValue.get("name");
//        Method[] methods = aClass.getMethods();
//        List<Method> methods = new ArrayList<>();
        Field[] fields = clazz.getDeclaredFields();
        List<Field> collect = Arrays.stream(fields).filter(e -> !("serialVersionUID".equals(e.getName()))).collect(Collectors.toList());
        for (Field fi:collect
        ) {
            map.put(name.toString()+"."+StringUtils.humpToLine2(fi.getName()),fi.getName());
//            methods.add( aClass.getMethod("get"+ fi.getName().substring(0,1).toUpperCase()+fi.getName().substring(1,fi.getName().length())));
        }
        return map;
    }

}
