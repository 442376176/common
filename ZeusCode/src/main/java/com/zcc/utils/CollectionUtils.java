package com.zcc.utils;

import com.zcc.entity.User;
import org.junit.Test;

import java.util.*;

/**
 * @author zcc
 * @version 1.0
 * @date 2021/11/25 13:49
 */
public class CollectionUtils<T> {

    public static  Map<String, Object> map = new HashMap<>();
    static {
        User user = new User();
        user.setSex("1");
        user.setName("!");
        map.put("1",user);
    }

    /**
     * 判断集合是否为空
     *
     * @param collection
     * @return
     */
    public static boolean isEmpty(Collection<?> collection) {
        return collection == null || collection.isEmpty();
    }

    /**
     * 判断集合是不为空
     *
     * @param collection
     * @return
     */
    public static boolean isNotEmpty(Collection<?> collection) {
        return !isEmpty(collection);
    }

    /**
     * 判断Map对象是否为空
     *
     * @param map
     * @return
     */
    public static boolean isEmpty(Map<?, ?> map) {
        return map == null || map.isEmpty();
    }

    /**
     * 判断Map对象是否不为空
     *
     * @param map
     * @return
     */
    public static boolean isNotEmpty(Map<?, ?> map) {
        return !isEmpty(map);
    }

    /**
     * @param num  分的份数
     * @param list 需要分的集合
     * @Author zcc
     */
    public static <T> Map<String, List<T>> splitList(List<T> list, Integer num) {
        HashMap<String, List<T>> stringListHashMap = new HashMap<String, List<T>>(); //用户封装返回的多个list
        int key = num;
        int listSize = list.size(); //list 长度
        int oneSize = listSize / key;
        int surplus = listSize % key; // 放在最后一个

        List<T> stringlist = new ArrayList<>();

        //用于承装每个等分list
        int i = 0;
        for (; i < listSize; i++) {                        //for循环依次放入每个list中
            stringlist.add(list.get(i));                            //先将string对象放入list,以防止最后一个没有放入
            if (((i + 1) % oneSize == 0) || (i + 1 == listSize)) {               //如果l+1 除以 要分的份数 为整除,或者是最后一份,为结束循环.那就算作一份list,
                if (i+1>listSize-surplus-oneSize){
                    i++;
                    for (; i < listSize ; i++) {
                        stringlist.add(list.get(i));
                    }
                }
                stringListHashMap.put(--key + "", stringlist); //将这一份放入Map中.
                stringlist = new ArrayList<T>();                //新建一个list,用于继续存储对象
            }
        }
        return stringListHashMap;                                     //将map返回
    }

    /**
     * 取交集
     *
     * @param collectionA
     * @param collections
     * @return
     * @Author zcc
     */
    public static <T> Collection<T> getIntersection(Collection<T> collectionA, Collection<T>... collections) {
        for (Collection<T> collectionB : collections) {
            collectionA.retainAll(collectionB);
        }
        return collectionA;
    }


    /**
     * 取并集
     *
     * @param collectionA
     * @param collections
     * @param <T>
     * @return
     */
    public static <T> Collection<T> getUnion(Collection<T> collectionA,
                                             Collection<T>... collections) {
        for (Collection<T> collectionB : collections) {
            getUnion(collectionA, collectionB);
        }
        return collectionA;
    }

    public static <T> void getUnion(Collection<T> collectionA,
                                    Collection<T> collectionB) {

        collectionA.removeAll(collectionB);
        collectionA.addAll(collectionB);

    }

    /**
     * 取差集
     *
     * @param collectionA
     * @param collections
     * @param <T>
     * @return
     */
    public static <T> Collection<T> getDifferenceSet(Collection<T> collectionA, Collection<T>... collections) {
        for (Collection<T> collectionB : collections) {
            collectionA.removeAll(collectionB);
        }
        return collectionA;
    }

//    public static <T> void getPinyinSort(List<T> s, Function<T, String> get) {
//
//    }


//    /**
//     * 字母排序
//     *
//     * @param collectionA
//     * @param <T>
//     * @return
//     */
//    public <T> Map<String, List<T>> getPinyinSort(List<T> collectionA, Function<T, String>[] get, BiConsumer<T, String> set) throws Exception {
//        for (T t : collectionA) {
//            Class<T> entityClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
//            T vo = entityClass.newInstance();
//            BeanUtils.copyProperties(t, vo);
//            char c = get[0].apply(vo).charAt(0);
//            // 判断是否为汉字
//            if (19968 <= c && c < 40869) { // 汉字开头
//                char s = StringUtils.getShoupin(get[0].apply(vo).charAt(0) + "").charAt(0);
//                s -= 32;
//                set.accept(vo, StringUtils.getShoupin(String.valueOf(s)));
//
//            } else if (c >= 97 && c <= 122) { // 小写字母开头
//                c -= 32;
//                set.accept(vo, c + "");
//            } else if (c >= 65 && c <= 90) { // 大写字母开头
//                set.accept(vo, c + "");
//            } else { // 其他
//                set.accept(vo, "#");
//            }
//        }
//        return collectionA.parallelStream().collect(Collectors.groupingBy(item -> get[1].apply(item)));
//    }

    @Test
    public void sd() throws Exception {
//        String s ="!2312331";
//        System.out.println(s.replace("qweqwe", "|"));
//        StringBuffer buffer = new StringBuffer();
//        System.out.println(buffer.toString());

        User user1 = new User();
        user1.setSex("1");
        user1.setName("!");
        Set set = new HashSet();
        set.add(map.get("1"));
        set.add(user1);
        System.out.println(set);

//        System.out.println(System.getProperty("os.name")+": os_verson:"+System.getProperty("os.version"));
//        System.out.println(System.getenv("s"));

//        BiFunction<User,String,String> = (a,b,c)->{
//
//        }
//        CollectionUtils<User> collectionUtils = new CollectionUtils<>();
//        Map<String, List<User>> pinyinSort = collectionUtils.getPinyinSort(list, s, com.zcc.entity.User::setName);

//        System.out.println(pinyinSort);
    }

    public static void main(String[] args) throws Exception {
//        CollectionUtils<User> collectionUtils = new CollectionUtils<>();
//        collectionUtils.getPinyinSort()
//        List<String> listA = new ArrayList<String>();
//        List<String> listB = new ArrayList<String>();
//        listA.add("1386859631038886184");
//        listB.add("1386859631038886153");
//        listB.add("1386859631038885917");
//        List<String> listC = new ArrayList<>();
//        Collection<String> union = getUnion(listA, listC, listB);
//        System.out.println(union);

//        CollectionUtils mainTest1 = new CollectionUtils();
//
//        List<String> list = new ArrayList<String>();          //实例,新建一个96条的list集合,将他平均分成几等份
//        for (int i = 0; i < 96; i++) {
//            list.add(i + "");
//        }
//
//        Map<String, List<String>> stringListHashMap = mainTest1.splitList(list,10);     //调用方法,将list平均分成10份.
//        for(HashMap.Entry<String, List<String>> entry:stringListHashMap.entrySet()){         //打印,已验证是否正确
//            System.out.println(entry.getKey());
//            for(String s:entry.getValue()){
//                System.out.println(s);
//            }
//        }
    }

}
