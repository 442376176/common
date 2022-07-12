package com.zcc.utils.lock;

import com.zcc.utils.RPC.BizException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.DefaultParameterNameDiscoverer;
import org.springframework.core.ParameterNameDiscoverer;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @ProjectName: ORCode
 * @ClassName: com.zcc.utils.lock
 * @author: zcc
 * @date: 2022/6/14 9:17
 * @version:
 * @Describe:
 */
@Aspect
@Component
public class ApiLockAspectPoint {

    private static final Logger LOG = LoggerFactory.getLogger(ApiLockAspectPoint.class);

    private static final String EXCLUDE_JAVA_BEAN_FILED = "serialVersionUID";

    private Map<Class<? extends ApiLockKeyGenerateStrategy>, ApiLockKeyGenerateStrategy> apiLockKeyGenerateStrategyMap = new ConcurrentHashMap<>();

    @Autowired
//    private DistributedLock redisLock; //分布式锁实现

    // 切所有controller包下的类方法
    @Pointcut("execution(* com.xx..*.controller..*.*(..))")
    public void execute(){}

    @Around("execute()")
    public Object around(ProceedingJoinPoint thisJoinPoint) throws Throwable {
        // 拿方法上注解，为空则不处理
        MethodSignature signature = (MethodSignature) thisJoinPoint.getSignature();
        ApiLock annotation = signature.getMethod().getAnnotation(ApiLock.class);
        if (annotation == null) {
            return thisJoinPoint.proceed();
        }

        // 拿方法中所有非空的参数，如果参数为空值或NULL则不处理
        String methodName = thisJoinPoint.getSignature().getName();
        Map<String, Object> methodNotNullArgsMap = getMethodNotNullArgsMap(thisJoinPoint, annotation);
        if (null == methodNotNullArgsMap || methodNotNullArgsMap.isEmpty()) {
            LOG.error("[{}] ==> there are no non-null arguments!", methodName);
            return thisJoinPoint.proceed();
        }

        // 根据指定策略拿lockKey
        Class<? extends ApiLockKeyGenerateStrategy> handlerClass = annotation.lockKeyGenerateStrategy();
        ApiLockKeyGenerateStrategy keyGenerator;
        if (apiLockKeyGenerateStrategyMap.containsKey(handlerClass)) {
            keyGenerator = apiLockKeyGenerateStrategyMap.get(handlerClass);
        } else {
            keyGenerator = handlerClass.newInstance();
            apiLockKeyGenerateStrategyMap.put(handlerClass, keyGenerator);
        }
        String lockKey = keyGenerator.generateKey(getDefaultPrefix(thisJoinPoint), methodNotNullArgsMap);

        // 加锁、执行方法
        Object lock = null;
        try {
            LOG.info("[{}]==> tryApiLock", methodName);
//            lock = redisLock.tryLock(lockKey, annotation.waitMills(), annotation.expireMills());
            if (lock == null) {
                throw new BizException("操作过于频繁");
            }
            LOG.info("[{}]==> api lock process start", methodName);
            return thisJoinPoint.proceed();
        } finally {
            if (lock != null) {
//                redisLock.unlock(lock);
                LOG.info("[{}]==> api lock process completed", methodName);
            }
        }
    }

    private String getDefaultPrefix(ProceedingJoinPoint thisJoinPoint) {
        return thisJoinPoint.getTarget().getClass().getName() + "#" + thisJoinPoint.getSignature().getName() + "-";
    }

    /**
     * 获取方法参数列表
     *
     * @param joinPoint
     * @param annotation
     * @return
     * @throws ClassNotFoundException
     * @throws NoSuchMethodException
     */
    private Map<String, Object> getMethodNotNullArgsMap(ProceedingJoinPoint joinPoint, ApiLock annotation) {
        Object[] args = joinPoint.getArgs();
        ParameterNameDiscoverer pnd = new DefaultParameterNameDiscoverer();
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        String[] parameterNames = pnd.getParameterNames(method);
        if (parameterNames == null) {
            return null;
        }

        // 将所有参数丢到map中
        Map<String, Object> resultParamMap = new HashMap<>();
        for (int i = 0; i < parameterNames.length; i++) {
            Object obj = args[i];
            if (obj == null) {
                continue;
            }
            // 如果参数类型是HttpServletRequest，判断一下是否需获取attr、header
            if (obj instanceof HttpServletRequest) {
                HttpServletRequest request = (HttpServletRequest) obj;
                checkAndFillReqAttrIfNecessary(resultParamMap, annotation, request);
                checkAndFillHeaderParamsIfNecessary(resultParamMap, annotation, request);
            } else {
                resultParamMap.put(parameterNames[i], obj);
            }
        }
        if (resultParamMap.isEmpty()) {
            return null;
        }

        // 考虑参数可能封装多层的情况，以及其中可能出现Entry的key或value为空值，需要remove掉这些Entry
        // 防止空参数导致生成重复的key，从而导致升级成一把全局锁
        RemoveNullEntryUtil.removeNullEntry(resultParamMap);
        return resultParamMap;
    }

    private void checkAndFillReqAttrIfNecessary(Map<String, Object> paramMap, ApiLock annotation, HttpServletRequest request) {
        if (annotation.requiredRequestAttrs() != null) {
            String[] requestAttrs = annotation.requiredRequestAttrs();
            for (String k : requestAttrs) {
                Object val = request.getAttribute(k);
                if (null == val) {
                    LOG.error("==> request attr:{} not exist", k);
                    continue;
                }
                paramMap.put(k, val);
            }
        }
    }

    private void checkAndFillHeaderParamsIfNecessary(Map<String, Object> paramMap, ApiLock annotation, HttpServletRequest request) {
        if (annotation.requiredHeaders() != null) {
            String[] headers = annotation.requiredHeaders();
            for (String k : headers) {
                String h = request.getHeader(k);
                if (null == h || "".equals(h)) {
                    LOG.error("==> header:{} not exist", k);
                    continue;
                }
                paramMap.put(k, h);
            }
        }
    }

    static class RemoveNullEntryUtil {

        /**
         * 移除map中空key或者value空值
         * @param map
         */
        private static void removeNullEntry(Map<String, Object> map){
            removeNullOrEmptyKey(map);
            removeNullOrEmptyValue(map);
        }

        /**
         * 移除map的key为空值的entry
         * @param map
         * @return
         */
        private static void removeNullOrEmptyKey(Map<String, Object> map){
            Set<String> set = map.keySet();
            Iterator<String> iterator = set.iterator();
            while (iterator.hasNext()) {
                Object obj = iterator.next();
                if (isObjectNullOrEmpty(obj)) {
                    iterator.remove();
                }
            }
        }

        /**
         * 移除map中的value为空的entry
         * @param map
         * @return
         */
        private static void removeNullOrEmptyValue(Map<String, Object> map){
            Set<String> set = map.keySet();
            Iterator iterator = set.iterator();
            while (iterator.hasNext()) {
                Object obj = iterator.next();
                Object value = map.get(obj);
                if (isObjectNullOrEmpty(value)) {
                    iterator.remove();
                }
            }
        }

        private static boolean isObjectNullOrEmpty(Object obj) {
            // 参考org.springframework.util.ObjectUtils.isEmpty(java.lang.Object)
            if(obj == null){
                return true;
            }
            if (isPrimitive(obj.getClass())) {
                return false;
            }
            if (obj instanceof Optional) {
                return !((Optional) obj).isPresent();
            }
            if (obj instanceof CharSequence) {
                return ((CharSequence) obj).length() == 0;
            }
            if (obj instanceof Collection) {
                return ((Collection) obj).isEmpty();
            }
            if (obj instanceof Map) {
                return ((Map) obj).isEmpty();
            }
            if (obj.getClass().isArray()) {
                return Array.getLength(obj) == 0;
            }
            if (obj.getClass().getPackage().getName().startsWith("java.math")) {
                // 能转换为math包下的类对象说明有值
                return false;
            }
            return isEmptyJavaBean(obj);
        }

        private static boolean isPrimitive(Class<?> clazz) {
            try {
                if (clazz.isPrimitive()) {
                    return true;
                }
                return ((Class<?>) clazz.getField("TYPE").get(null)).isPrimitive();
            } catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
                return false;
            }
        }

        private static boolean isEmptyJavaBean(Object object) {
            Class clazz = object.getClass();
            Field fields[] = clazz.getDeclaredFields();
            boolean flag = true;
            for(Field field : fields){
                boolean hasChangeAccessFlag = false;
                if (!field.isAccessible()) {
                    field.setAccessible(true);
                    hasChangeAccessFlag = true;
                }
                Object fieldValue = null;
                String fieldName = field.getName();
                if (EXCLUDE_JAVA_BEAN_FILED.equals(fieldName)) {
                    // 忽略序列号字段
                    continue;
                }
                try {
                    fieldValue = field.get(object);
                } catch (IllegalAccessException e) {
                    // 实际上前面已经确保了属性可以访问，所以不会抛该异常
                    LOG.error("==> get field err:", e);
                }
                if (hasChangeAccessFlag) {
                    field.setAccessible(false);
                }
                // 递归判断字段是否为空值，有任意一个字段有值则跳出循环
                if (!isObjectNullOrEmpty(fieldValue)) {
                    flag = false;
                    break;
                }
            }
            return flag;
        }
    }

}