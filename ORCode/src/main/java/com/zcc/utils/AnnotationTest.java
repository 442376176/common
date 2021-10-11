package com.zcc.utils;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
/**
 * @author zcc
 * @date 2021/07/05
 */

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.METHOD,ElementType.LOCAL_VARIABLE,ElementType.TYPE})
public @interface AnnotationTest {
    String getUserName() default  "";
    String getAddress() default  "";
    String color() default "";
}
