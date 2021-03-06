package com.zcc.utils.excelExport.v1;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
/**
 * @author zcc
 * @date 2021/07/05
 */

/**
 * 测试Annotation
 */

public class GetAnnotationValue {


    /**
     * 获取类的所有方法上的注解值
     *
     * @param clazz           类名
     * @param AnnotationClass 注解类
     * @return
     */
    public static Map<String, Object> getAllMethodAnnotationValue(Class clazz, Class AnnotationClass) throws Exception {
        //获取方法上的注解值
        Map<String, Object> map = new HashMap<>();
        Method[] methods = clazz.getDeclaredMethods();
        if (methods != null) {
            for (Method method : methods) {
                Map<String, Object> value = new HashMap<>();
                Annotation annotation = method.getAnnotation(AnnotationClass);
                if (annotation == null)
                    continue;
                Method[] me = annotation.annotationType().getDeclaredMethods();
                for (Method meth : me) {
                    if (!meth.invoke(annotation, null).equals("")) {
                        value.put(meth.getName(), meth.invoke(annotation, null));
                    }
                }
                map.put(method.getName(), value);
            }
        }
        return map;
    }

    /**
     * 获取某个方法上的注解值
     *
     * @param method          方法名
     * @param AnnotationClass 注解类
     * @return
     */
    public static Map<String, Object> getMethodAnnotationValue(Class clazz, String method, Class AnnotationClass) throws Exception {
        //获取方法上的注解值
        Map<String, Object> map = new HashMap<>();
        Method declaredMethod = clazz.getDeclaredMethod(method);
        if (declaredMethod != null) {
            Annotation annotation = declaredMethod.getAnnotation(AnnotationClass);
            if (annotation == null)
                return null;
            Method[] me = annotation.annotationType().getDeclaredMethods();
            for (Method meth : me) {
                if (!meth.isAccessible()) {
                    meth.setAccessible(true);
                }
                if (!meth.invoke(annotation, null).equals("")) {
                    map.put(meth.getName(), meth.invoke(annotation, null));
                }
            }
        }
        return map;
    }

    /**
     * 获取类上注解值
     *
     * @param clazz           类
     * @param AnnotationClass 注解类
     * @return
     * @throws Exception
     */
    public static Map<String, Object> getClassAnnotationValue(Class clazz, Class AnnotationClass) throws Exception {
        Map<String, Object> map = new HashMap<>();
        //获取类上的注解值
        Annotation clazzAnnotation = clazz.getAnnotation(AnnotationClass);
        Method[] me = clazzAnnotation.annotationType().getDeclaredMethods();
        for (Method meth : me) {
            if (!meth.isAccessible()) {
                meth.setAccessible(true);
            }
            if (!meth.invoke(clazzAnnotation, null).equals("")) {
                map.put(meth.getName(), meth.invoke(clazzAnnotation, null));
            }
        }
        return map;
    }

    /**
     * 获取类上所有字段上的注解值
     *
     * @param clazz
     * @param AnnotationClass
     * @return
     * @throws Exception
     */
    public static Map<String, Map<String, String>> getFieldAnnotationValue(Class clazz, Class AnnotationClass) throws Exception {
        Map<String,  Map<String, String>> map = new HashMap<>();

        //获取字段上的注解值
        Object o = clazz.newInstance();
        Field[] field = clazz.getDeclaredFields();
        if (field != null) {
            for (Field fie : field) {
                Map<String, String> value = new HashMap<>();
                if (!fie.isAccessible()) {
                    /**
                     * isAccessible()值为 true 则指示反射的对象在使用时应该取消 Java 语言访问检查。值为 false 则指示反射的对象应该实施 Java 语言访问检查。
                     * 实际上setAccessible是启用和禁用访问安全检查的开关,并不是为true就能访问为false就不能访问
                     * 由于JDK的安全检查耗时较多.所以通过setAccessible(true)的方式关闭安全检查就可以达到提升反射速度的目的
                     */
                    fie.setAccessible(true);
                }
                Annotation annotation = fie.getAnnotation(AnnotationClass);
                if (annotation != null) {
                    Method[] meth = annotation.annotationType().getDeclaredMethods();
                    for (Method me : meth) {
                        if (!me.isAccessible()) {
                            me.setAccessible(true);
                        }
                        //给字段重新赋值
//                        fie.set(o, me.invoke(o, null));
                        if (!me.invoke(annotation, null).equals(""))
                            value.put(me.getName(), me.invoke(annotation, null).toString());
                    }
                    map.put(fie.getName(), value);
                }
            }
        }
        return map;
    }

    /**
     * 获取类上某个字段上的注解值
     *
     * @param clazz
     * @param AnnotationClass
     * @return
     * @throws Exception
     */
    public static Map<String, String> getFieldAnnotationValue(Class clazz, String field, Class AnnotationClass) throws Exception {
        Map<String, String> map = new HashMap<>();
        //获取字段上的注解值
        Object o = clazz.newInstance();
        Field declaredField = clazz.getDeclaredField(field);
        if (field != null) {
            if (!declaredField.isAccessible()) {
                declaredField.setAccessible(true);
            }
            Annotation annotation = declaredField.getAnnotation(AnnotationClass);
            Method[] meth = annotation.annotationType().getDeclaredMethods();
            for (Method me : meth) {
                if (!me.isAccessible()) {
                    me.setAccessible(true);
                }
                //给字段重新赋值
//                        fie.set(o, me.invoke(o, null));
                if (!me.invoke(annotation, null).equals(""))
                    map.put(me.getName(), me.invoke(annotation, null).toString());
            }
        }
        return map;
    }
}