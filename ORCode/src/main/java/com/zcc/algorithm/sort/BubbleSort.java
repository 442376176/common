package com.zcc.algorithm.sort;

import org.junit.Test;

import java.util.Arrays;

/**
 * @ProjectName: ORCode
 * @ClassName: com.zcc.algorithm.sort
 * @author: zcc
 * @date: 2022/3/18 9:53
 * @version:
 * @Describe: 冒泡排序
 * 冒泡排序（Bubble Sort）也是一种简单直观的排序算法。它重复地走访过要排序的数列，
 * 一次比较两个元素，如果他们的顺序错误就把他们交换过来。
 * 走访数列的工作是重复地进行直到没有再需要交换，也就是说该数列已经排序完成。
 * 这个算法的名字由来是因为越小的元素会经由交换慢慢"浮"到数列的顶端。
 *
 * 作为最简单的排序算法之一，冒泡排序给我的感觉就像 Abandon 在单词书里出现的感觉一样，
 * 每次都在第一页第一位，所以最熟悉。冒泡排序还有一种优化算法，
 * 就是立一个 flag，当在一趟序列遍历中元素没有发生交换，
 * 则证明该序列已经有序。但这种改进对于提升性能来
 * 说并没有什么太大作用。
 */
public class BubbleSort {
    public int[] sort(int[] sourceArray) throws Exception {
        // 对 arr 进行拷贝，不改变参数内容
        int[] arr = Arrays.copyOf(sourceArray, sourceArray.length);

        for (int i = 1; i < arr.length; i++) {
            // 设定一个标记，若为true，则表示此次循环没有进行交换，也就是待排序列已经有序，排序已经完成。
            boolean flag = true;

            for (int j = 0; j < arr.length - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    int tmp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = tmp;

                    flag = false;
                }
            }
            if (flag) {
                break;
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
