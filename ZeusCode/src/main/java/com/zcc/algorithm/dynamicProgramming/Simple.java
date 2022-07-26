package com.zcc.algorithm.dynamicProgramming;

import java.util.Arrays;

/**
 * @ProjectName: ORCode
 * @ClassName: com.zcc.algorithm.dynamicProgramming
 * @author: zcc
 * @date: 2022/4/1 16:12
 * @version:
 * @Describe:
 */
public class Simple {
    /**
     * 最大递增子序列
     *
     * @param nums
     * @return
     */
    public static int longestIncreasingSubsequence(int[] nums) {
        int[] length = new int[nums.length];
        for (int i = 0; i < length.length; i++) {
            length[i] = 1;
        }
        for (int i = nums.length - 1; i >= 0; i--) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[j] > nums[i]) {
                    length[i] = Math.max(length[i], length[j] + 1);
                }
            }
        }
        return Arrays.stream(length).max().getAsInt();
    }

    /**
     * 求解连续子序列的最大和
     *
     * @param arr
     * @return
     */
    public static int maxSumContinuousSubsequence(int[] arr) {
        int[] sum = new int[arr.length];
        for (int i = arr.length - 1; i >= 0; i--) {
            for (int j = i+1; j < arr.length; j++) {
                if (sum[j] > 0) {
                    sum[i] = arr[j] + sum[j];
                    break;
                }
                sum[i] = arr[j];
                break;
            }
        }
        return Arrays.stream(sum).max().getAsInt();
    }

    public static void main(String[] args) {
        int[] nums = {1, 5, 2, 4, 3};
        int[] arr = {3, -4, 2, -1, 2, 6, -5, 4};
        // 3
        System.out.println(longestIncreasingSubsequence(nums));
        // 9
        System.out.println(maxSumContinuousSubsequence(arr));
    }
}
