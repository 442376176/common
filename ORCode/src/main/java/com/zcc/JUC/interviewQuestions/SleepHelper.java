package com.zcc.JUC.interviewQuestions;

import java.util.concurrent.TimeUnit;

/**
 * @ProjectName: ORCode
 * @ClassName: com.zcc.JUC.interviewQuestions
 * @author: zcc
 * @date: 2022/5/24 16:37
 * @version:
 * @Describe:
 */
public class SleepHelper {
    public static void sleep(int second){
        try {
            Thread.sleep(second*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public static void sleepMilli(int milli){
        try {
            Thread.sleep(milli);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void sleepNano(int nano){
        try {
            TimeUnit.NANOSECONDS.sleep(nano);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
