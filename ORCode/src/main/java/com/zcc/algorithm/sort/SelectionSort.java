package com.zcc.algorithm.sort;

import org.junit.Test;

import java.util.Arrays;

/**
 * @ProjectName: ORCode
 * @ClassName: com.zcc.algorithm.sort
 * @author: zcc
 * @date: 2022/3/18 10:17
 * @version:
 * @Describe:
 */
public class SelectionSort {
    public int[] sort(int[] sourceArray) throws Exception {
        int[] arr = Arrays.copyOf(sourceArray, sourceArray.length);

        // 总共要经过 N-1 轮比较
        for (int i = 0; i < arr.length - 1; i++) {
            int min = i;

            // 每轮需要比较的次数 N-i
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[min]) {
                    // 记录目前能找到的最小值元素的下标
                    min = j;
                }
            }

            // 将找到的最小值和i位置所在的值进行交换
            if (i != min) {
                int tmp = arr[i];
                arr[i] = arr[min];
                arr[min] = tmp;
            }

        }
        return arr;
    }

    @Test
    public void sort() throws Exception {
        int[] s = {1,2,34,5,6,4,19,13,14,15};
        int[] sort = sort(s);
        System.out.println(s.toString());
        for (int i = 0; i < sort.length; i++) {
            System.out.print(sort[i]+",");
        }

    }

}
