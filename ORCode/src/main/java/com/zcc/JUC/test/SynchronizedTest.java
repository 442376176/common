package com.zcc.JUC.test;

import com.zcc.JUC.interviewQuestions.SleepHelper;
import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * @ProjectName: ORCode
 * @ClassName: com.zcc.JUC.test
 * @author: zcc
 * @date: 2022/5/26 10:45
 * @version:
 * @Describe:
 */
public class SynchronizedTest {

    private static  int count = 0;


    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                SleepHelper.sleepMilli(100);
                synchronized (SynchronizedTest.class) {
                    count++;
                }
            }).start();
        }
        SleepHelper.sleep(1);
        System.out.println(count);
    }

}

/**
 * Unsafe 类是直接操作操作系统的，所以是不安全的类，不允许自己new
 */
class UnSafeTest {

    private int i = 0;
    private static Unsafe unsafe;
    //偏移量，unsafe方法中会使用到
    private static long COUNT_OFFSET;

    static {
        //不能直接new，会报错
        //unsafe = Unsafe.getUnsafe();
        //Unsafe类中有一个熟悉private static final Unsafe theUnsafe; 内部通过new实例化了，所以
        // 可以通过反射类获取这个属性，从而获取unsfe
        try {
            //获取属性
            Field field = Unsafe.class.getDeclaredField("theUnsafe");
            field.setAccessible(true);
            unsafe = (Unsafe) field.get(null);

            //获取i属性字段的偏移量
            COUNT_OFFSET = unsafe.objectFieldOffset(UnSafeTest.class.getDeclaredField("i"));
        } catch (IllegalAccessException | NoSuchFieldException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        UnSafeTest unSafeTest = new UnSafeTest();
        //创建两个线程来操作变量i
        new Thread(new Runnable() {
            @Override
            public void run() {
                //修改unsafe对象中偏移量为COUNT_OFFSET属性的值，如果unSafeTest.i跟内存中的值一致，说明还没被修改
                //则进行加1操作，如果不一致，则不进行操作
                boolean b = unsafe.compareAndSwapInt(unSafeTest, COUNT_OFFSET, unSafeTest.i, unSafeTest.i + 1);
                if (b) {
                    System.out.println(unsafe.getIntVolatile(unSafeTest, COUNT_OFFSET));
                }

            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                boolean b = unsafe.compareAndSwapInt(unSafeTest, COUNT_OFFSET, unSafeTest.i, unSafeTest.i + 1);
                if (b) {
                    System.out.println(unsafe.getIntVolatile(unSafeTest, COUNT_OFFSET));
                }

            }
        }).start();


    }

}


