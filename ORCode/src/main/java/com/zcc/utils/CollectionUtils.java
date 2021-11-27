package com.zcc.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zcc
 * @version 1.0
 * @date 2021/11/25 13:49
 */
public class CollectionUtils {

    /**
     * @param num 分的份数
     * @param list 需要分的集合
     */
    public Map<String, List<String>> splitList(List<String> list, Integer num) {


        int listSize = list.size(); //list 长度

        HashMap<String, List<String>> stringListHashMap = new HashMap<String, List<String>>(); //用户封装返回的多个list
        List<String> stringlist =  new ArrayList<String>();;         //用于承装每个等分list

        for (int i = 0; i < listSize; i++) {                        //for循环依次放入每个list中
            stringlist.add(list.get(i));                            //先将string对象放入list,以防止最后一个没有放入
            if (((i+1) % num == 0)||(i+1==listSize)) {               //如果l+1 除以 要分的份数 为整除,或者是最后一份,为结束循环.那就算作一份list,
                stringListHashMap.put("stringList" + i, stringlist); //将这一份放入Map中.
                stringlist = new ArrayList<String>();                //新建一个list,用于继续存储对象
            }
        }
        return stringListHashMap;                                     //将map返回
    }
    public static void main(String[] args) throws Exception {
        CollectionUtils mainTest1 = new CollectionUtils();

        List<String> list = new ArrayList<String>();          //实例,新建一个96条的list集合,将他平均分成几等份
        for (int i = 0; i < 96; i++) {
            list.add(i + "");
        }

        Map<String, List<String>> stringListHashMap = mainTest1.splitList(list,10);     //调用方法,将list平均分成10份.
        for(HashMap.Entry<String, List<String>> entry:stringListHashMap.entrySet()){         //打印,已验证是否正确
            System.out.println(entry.getKey());
            for(String s:entry.getValue()){
                System.out.println(s);
            }
        }
    }
}
