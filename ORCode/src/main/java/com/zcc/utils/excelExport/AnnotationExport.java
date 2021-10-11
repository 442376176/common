package com.zcc.utils.excelExport;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author zcc
 * @version 1.0
 * @date 2021/8/31 15:16
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface AnnotationExport {
    String index() default  "";
    String columnName() default "";
}
