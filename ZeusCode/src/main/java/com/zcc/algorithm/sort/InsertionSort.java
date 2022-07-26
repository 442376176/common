package com.zcc.algorithm.sort;

import org.junit.Test;

import java.util.Arrays;

/**
 * @ProjectName: ORCode
 * @ClassName: com.zcc.algorithm.sort
 * @author: zcc
 * @date: 2022/3/18 10:19
 * @version:
 * @Describe:
 */
public class InsertionSort {


    public int[] sort(int[] sourceArray) throws Exception {
        // 对 arr 进行拷贝，不改变参数内容
        int[] arr = Arrays.copyOf(sourceArray, sourceArray.length);

        // 从下标为1的元素开始选择合适的位置插入，因为下标为0的只有一个元素，默认是有序的
        for (int i = 1; i < arr.length; i++) { // 插入次序

            // 记录要插入的数据
            int tmp = arr[i];

            // 从已经排序的序列最右边的开始比较，找到比其小的数
            int j = i;
            while (j > 0 && tmp < arr[j - 1]) { // 交换
                arr[j] = arr[j - 1];
                j--;
            }

            // 存在比其小的数，插入
            if (j != i) {
                arr[j] = tmp;
            }

        }
        return arr;
    }

    @Test
    public void sort() throws Exception {
        int[] s = {1,15,5,26,6,79,19};
        int[] sort = sort(s);
        System.out.println(s.toString());
        for (int i = 0; i < sort.length; i++) {
            System.out.print(sort[i]+",");
        }

    }

}
