package com.zcc.algorithm.sort;

import org.junit.Test;

/**
 * @ProjectName: ORCode
 * @ClassName: com.zcc.algorithm.sort
 * @author: zcc
 * @date: 2022/3/18 11:23
 * @version:
 * @Describe:希尔排序
 */
public class ShellSort {

    public static void sort(int[] arr) {
        int length = arr.length;
        int temp;
        for (int step = length / 2; step >= 1; step /= 2) {
            for (int i = step; i < length; i++) {
                temp = arr[i];
                int j = i - step;
                while (j >= 0 && arr[j] > temp) {
                    arr[j + step] = arr[j];
                    j -= step;
                }
                arr[j + step] = temp;
            }
        }
    }

    @Test
    public void sort() throws Exception {
        int[] s = {1, 2, 34, 5, 6, 4, 19, 13, 14, 15};
        sort(s);
        System.out.println(s.toString());
        for (int i = 0; i < s.length; i++) {
            System.out.print(s[i] + ",");
        }

    }
}
