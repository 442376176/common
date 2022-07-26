package com.zcc.utils;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

/**
 * @author zcc
 * @version 1.0
 * @date 2021/9/1 15:46
 */
public class TypeUtil {



    /**
     * ParameterizedType 的几个主要方法:
     * Type[] getActualTypeArguments(); //返回表示此类型实际类型参数的 Type 对象的数组。
     *        获取泛型中的实际类型，可能会存在多个泛型，例如Map<K,V>,所以会返回Type[]数组:
     *         值得注意的是，无论<>中有几层嵌套(List<Map<String,Integer>)，getActualTypeArguments()方法永远都是脱去最外层的<>(也就是List<>)，
     *        将<>号内的内容（Map<String,Integer>）返回；
     *        我们经常遇到的List<T>，通过getActualTypeArguments()方法，得到的返回值是TypeVariableImpl对象，也就是TypeVariable类型
     * 实例如下
     */
    static class ParameterizedTest<T> {

        private Map<String, Integer> list = null;

        public static void main(String[] args) {

            try {
                Field field = ParameterizedTest.class.getDeclaredField("list");

                Type type = field.getGenericType();

                if (type instanceof ParameterizedType) {
                    ParameterizedType pType = (ParameterizedType) type;
                    Type[] types = pType.getActualTypeArguments();

                    System.out.println(types[0]);// class java.lang.String
                    System.out.println(types[1]);// class java.lang.Integer
                }

            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            }


        }
    }



    /**
     * Type getRawType();//返回 Type 对象，表示声明此类型的类或接口。
     *      *       获取声明泛型的类或者接口，也就是泛型中<>前面的那个值:
     * @return
     */

    static class ParameterizedTest1<T> {

        private Map<String, Integer> list = null;

        public static void main(String[] args) {

            try {
                Field field = ParameterizedTest1.class.getDeclaredField("list");

                Type type = field.getGenericType();

                if (type instanceof ParameterizedType) {
                    ParameterizedType pType = (ParameterizedType) type;

                    Type t = pType.getRawType();//interface java.util.Map

                    System.out.println(t);
                }

            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 1.3 getOwnerType
     * (这个比较少用到) 返回的是这个 ParameterizedType 所在的类的 Type （注意当前的 ParameterizedType 必须属于所在类的 member）。
     * 比如 Map<String,Person> map 这个 ParameterizedType 的 getOwnerType() 为 null，
     * 而 Map.Entry<String, String>entry 的 getOwnerType() 为 Map 所属于的 Type。
     */


    /**
     * TypeVariable
     *
     * 类型变量，即泛型中的变量；例如：T、K、V等变量，可以表示任何类，
     * 实际的Java类型是TypeVariableImpl（TypeVariable的子类）。在这需要强调的是，
     * TypeVariable代表着泛型中的变量，而ParameterizedType则代表整个泛型。
     * 此外，还可以对类型变量加上extend限定，这样会有类型变量对应的上限。
     */

    static class ParameterizedTest2<T> {

        private List<T> list = null;

        public static void main(String[] args) {

            try {
//                ParameterizedTest2<User> userParameterizedTest2 = new ParameterizedTest2<>();
//                Field field =userParameterizedTest2.getClass().getDeclaredField("list");
                Field field =ParameterizedTest2.class.getDeclaredField("list");
                Type type = field.getGenericType();//获取List<T>

                if (type instanceof ParameterizedType) {
                    ParameterizedType pType = (ParameterizedType) type;

                    Type[] t = pType.getActualTypeArguments();// 获取T

                    System.out.println(t[0].getClass().getName());//T的类型 sun.reflect.generics.reflectiveObjects.TypeVariableImpl
                }

            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            }
        }
    }
}
