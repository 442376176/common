package com.zcc.algorithm.sort;

import org.junit.Test;

import java.util.Arrays;

/**
 * @ProjectName: ORCode
 * @ClassName: com.zcc.algorithm.sort
 * @author: zcc
 * @date: 2022/3/18 13:22
 * @version:
 * @Describe:
 */
public class MergeSort {
    public int[] sort(int[] sourceArray) throws Exception {
        // 对 arr 进行拷贝，不改变参数内容
        int[] arr = Arrays.copyOf(sourceArray, sourceArray.length);

        if (arr.length < 2) {
            return arr;
        }
        int middle = (int) Math.floor(arr.length / 2);

        int[] left = Arrays.copyOfRange(arr, 0, middle);
        int[] right = Arrays.copyOfRange(arr, middle, arr.length);

        return merge(sort(left), sort(right));
    }

    protected int[] merge(int[] left, int[] right) {
        int[] result = new int[left.length + right.length];
        int i = 0;
        while (left.length > 0 && right.length > 0) {
            if (left[0] <= right[0]) {
                result[i++] = left[0];
                left = Arrays.copyOfRange(left, 1, left.length);
            } else {
                result[i++] = right[0];
                right = Arrays.copyOfRange(right, 1, right.length);
            }
        }

        while (left.length > 0) {
            result[i++] = left[0];
            left = Arrays.copyOfRange(left, 1, left.length);
        }

        while (right.length > 0) {
            result[i++] = right[0];
            right = Arrays.copyOfRange(right, 1, right.length);
        }

        return result;
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
