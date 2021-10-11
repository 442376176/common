package com.zcc.utils.sqlBuilder;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Condition {
    private Map<String,Object> fields = new MyHashMap();
    private List<String> declaredFields = new ArrayList<>();
    public Condition(Class clazz){
        Field[] fields = clazz.getDeclaredFields();
        declaredFields = Arrays.stream(fields).map(Field::getName).collect(Collectors.toList());
//       declaredFields = Arrays.asList(fields.clone());
    }

    public Condition andEqualTo(String filed,Object value)throws Exception{
        if (!declaredFields.contains(filed)) {
            throw new Exception("表中没有该属性");
        }
        fields.put(StringUtils.humpToLine2(filed),value);
        return this;
    }

    public Condition andNotEqualTo(String filed,Object value)throws Exception{
        if (!declaredFields.contains(filed)) {
            throw new Exception("表中没有该属性");
        }
        fields.put(StringUtils.humpToLine2(filed) +"<>",value);
        return this;
    }

    public Condition andLike(String filed,Object value)throws Exception{
        if (!declaredFields.contains(filed)) {
            throw new Exception("表中没有该属性");
        }
        fields.put(StringUtils.humpToLine2(filed)+" like ",value);
        return this;
    }

    public Condition andIn(String filed,List<?> value)throws Exception{
        if (!declaredFields.contains(filed)) {
            throw new Exception("表中没有该属性");
        }
        fields.put(StringUtils.humpToLine2(filed)+ " in ",value);
        return this;
    }

    public Condition andGreaterThan(String filed,Object value)throws Exception{
        if (!declaredFields.contains(filed)) {
            throw new Exception("表中没有该属性");
        }
        fields.put(StringUtils.humpToLine2(filed)+ " > ",value);
        return this;
    }
    public Condition andBetween(String filed,Object low,Object high)throws Exception{
        if (!declaredFields.contains(filed)) {
            throw new Exception("表中没有该属性");
        }
        fields.put(StringUtils.humpToLine2(filed)+ " between " + low + " and ",high);
        return this;
    }
    public Condition andLessThan(String filed,Object value)throws Exception{
        if (!declaredFields.contains(filed)) {
            throw new Exception("表中没有该属性");
        }
        fields.put(StringUtils.humpToLine2(filed)+ " < ",value);
        return this;
    }

    public  String toString(){
        return fields.toString();
    }
}
