package com.zcc.utils.lock;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @ProjectName: ORCode
 * @ClassName: com.zcc.utils.lock
 * @author: zcc
 * @date: 2022/6/14 9:05
 * @version:
 * @Title: 用来标记接口需要接入请求锁
 * （需确保已引入分布式锁的实现）
 *
 * @Desc
 * lockKey默认为所有请求参数jsonStr的md5值（可以根据lockKeyGenerator更改生成策略），如果最终得到的参数为空，则加锁无效
 * 下面几种情况会导致参数为空、加锁无效：
 * 1、方法签名未申明任何请求参数
 * 2、方法申明了参数，但客户端传过来的所有参数均为空值
 * 3、方法参数只有一个HttpServletRequest，但未申明{@link #requiredRequestAttrs}、{@link #requiredHeaders()}的其中一个，换句话说还是没有参数作为lockKey
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ApiLock {

    /**
     * 获取锁等待时间（毫秒）
     */
    long waitMills();

    /**
     * 锁自动过期时间（毫秒）
     */
    long expireMills();

    /**
     * 声明当前接口需从HttpServletRequest上下文中获取的参数(request.getAttribute(attr))
     * （只支持从方法参数HttpServletRequest中获取，如果填入该值则会获取并参与lock key的拼接）
     */
    String[] requiredRequestAttrs() default {};

    /**
     * 声明当前接口需从请求头中获取的参数(request.getHeader(header))
     * （只支持从方法参数HttpServletRequest中获取，如果填入该值则会获取并参与lock key的拼接）
     */
    String[] requiredHeaders() default {};

    /**
     * 指定lockKey生成器类型，默认用ApiLockKeyDefaultGenerateStrategy，可自定义实现
     */
    Class<? extends ApiLockKeyGenerateStrategy> lockKeyGenerateStrategy() default ApiLockKeyDefaultGenerateStrategy.class;

}
