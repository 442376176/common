package com.zcc.utils;


import com.zcc.utils.sqlBuilder.StringUtils;
import org.mybatis.spring.mapper.MapperFactoryBean;
import tk.mybatis.mapper.entity.Example;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.*;
import java.util.stream.Collectors;

public abstract class BaseService<T> {

    MapperFactoryBean s = new MapperFactoryBean();

    private Type type;
    private Class<T> clazz;
    //    private Map<String,Object> property = new HashMap<>();
//    private Map<String,Object> method= new HashMap<>();
    private List<Method> methods = new ArrayList<>();
    private Field[] fields;
    private Class<T> mapper;


    @SuppressWarnings("unchecked")
    public void init() throws Exception { // 获取泛型类型

        /*
        ParameterizedType 的几个主要方法:
         */
//        Type[] getActualTypeArguments(); //返回表示此类型实际类型参数的 Type 对象的数组。
//        获取泛型中的实际类型，可能会存在多个泛型，例如Map<K,V>,所以会返回Type[]数组:
//        值得注意的是，无论<>中有几层嵌套(List<Map<String,Integer>)，getActualTypeArguments()方法永远都是脱去最外层的<>(也就是List<>)，
//        将<>号内的内容（Map<String,Integer>）返回；
//        我们经常遇到的List<T>，通过getActualTypeArguments()方法，得到的返回值是TypeVariableImpl对象，也就是TypeVariable类型




        //
//        Type getOwnerType();//返回 Type 对象，表示此类型是其成员之一的类型。

        //getGenericSuperclass()获得带有泛型的父类
        Type superClass = getClass().getGenericSuperclass();
        //ParameterizedType参数化类型，即泛型
        //getActualTypeArguments获取参数化类型的数组，泛型可能有多个
        this.type = ((ParameterizedType) superClass).getActualTypeArguments()[0];
        if (this.type instanceof ParameterizedType) {
            this.clazz = (Class<T>) ((ParameterizedType) this.type).getRawType();
        } else {
            this.clazz = (Class<T>) this.type;
        }
//        methods = clazz.getMethods();
        fields = clazz.getDeclaredFields();
        for (Field s : fields
        ) {
            methods.add(clazz.getDeclaredMethod("get" +
                    StringUtils.substring(s.getName(), 0, 1).toUpperCase() +
                    StringUtils.substring(s.getName(), 1)));
        }
        String[] split = clazz.getName().split("\\.");
        List<String> collect = Arrays.stream(split).limit(2).collect(Collectors.toList());
        StringBuffer buffer = new StringBuffer();
        collect.forEach(e->buffer.append(e+"."));
        buffer.append("mapper."+split[split.length-1]+"Mapper");
        mapper = (Class<T>)Class.forName(buffer.toString());
        T t = mapper.newInstance();
    }


    public List<T> page(T t) throws Exception{
        List<T> list = new ArrayList<>();
        Example e = new Example(t.getClass());
        Map<String, Object> map = new HashMap<>();
        methods.forEach(method -> {
            Object invoke = null;
            try {
                invoke = method.invoke(t);
            } catch (Exception ex) {
                ex.getStackTrace();
            }
            if (invoke != null) {
                map.put(StringUtils.substring(method.getName(),3,4).toLowerCase()+
                        StringUtils.substring(method.getName(), 4), invoke);
            }
        });
        Example.Criteria criteria = e.createCriteria();
//        Iterator<String> iterator = map.keySet().iterator();
        Iterator<Map.Entry<String, Object>> iterator = map.entrySet().iterator();
//        System.out.println(iterator.hasNext());
        while (iterator.hasNext()){
//            Map.Entry<String, Object> next = iterator.next();
//            String key = next.getKey();
//            Object value = next.getValue();
//            System.out.println(key+value.toString()+"123123123");
            criteria.andEqualTo(iterator.next().getKey(),map.get(iterator.next()));
        }
        Method selectByExample = mapper.getClass().getMethod("selectByExample", e.getClass());
//        T t1 = mapper.newInstance();
        for (Map.Entry<String,Object> entry:map.entrySet()
             ) {
            criteria.andEqualTo(entry.getKey(),entry.getValue());
        }

        Class<T> exampleMapper = (Class<T>)Class.forName("tk.mybatis.mapper.common.example.SelectByExampleMapper");
        Class<T> mapperFactoryBean = (Class<T>)Class.forName("tk.mybatis.spring.mapper.MapperFactoryBean");
        mapperFactoryBean.getMethod("getObject");
        Method[] methods = exampleMapper.getMethods();
        Class<? super T> superclass = mapper.getSuperclass();
        System.out.println(mapper.newInstance());
//        list = (List<T>)methods[0].invoke(e);
        list = (List<T>)selectByExample.invoke(mapper,e);
        return list;
    }

    ;

    public abstract T getObject(String id);

    public abstract void update(T t);

    public abstract void insert(T t);

    public abstract void delete(T t);
}
