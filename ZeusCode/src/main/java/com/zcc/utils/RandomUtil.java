package com.zcc.utils;

import java.util.Random;

/**
 * @author zcc
 * @version 1.0
 * @date 2021/11/30 16:05
 */
public class RandomUtil {
    public static char[] arr = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
            'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
            'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'
    };

    public static String getRandomStr(Integer length) {
        if (length < 1 || length == null) {
            return getRandomStr();
        }
        StringBuilder stringBuilder = new StringBuilder();
        Random random = new Random();

        for (int i = 0; i < length; i++) {
            stringBuilder.append(arr[random.nextInt(arr.length)]);
        }
        return stringBuilder.toString();
    }

    public static String getRandomStr() {
        StringBuilder stringBuilder = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 6; i++) {
            stringBuilder.append(arr[random.nextInt(arr.length)]);
        }
        return stringBuilder.toString();
    }
    public static String getRandomNumber() {
        StringBuilder stringBuilder = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 6; i++) {
            stringBuilder.append(random.nextInt(10));
        }
        return stringBuilder.toString();
    }

}
