package com.zcc.algorithm.primary_algorithm;

import org.junit.Test;

/**
 * @author zcc
 * @version 1.0
 * @date 2021/10/22 9:11
 */
public class OtherTest {


    /**
     * 假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
     *
     * 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
     *
     * 注意：给定 n 是一个正整数。
     *
     * 示例 1：
     *
     * 输入： 2
     * 输出： 2
     * 解释： 有两种方法可以爬到楼顶。
     * 1.  1 阶 + 1 阶
     * 2.  2 阶
     * 示例 2：
     *
     * 输入： 3
     * 输出： 3
     * 解释： 有三种方法可以爬到楼顶。
     * 1.  1 阶 + 1 阶 + 1 阶
     * 2.  1 阶 + 2 阶
     * 3.  2 阶 + 1 阶
     *
     * 作者：力扣 (LeetCode)
     * 链接：https://leetcode-cn.com/leetbook/read/top-interview-questions-easy/xn854d/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param n
     * @return
     */
    public int climbStairs(int n) {
        // 定义dp[i] 的含义 n = dp[i]的值代表第i层台阶有 n 种走法
        // 找到关系式 dp[i] = dp[i-1] + dp[i-2] n层台阶有 dp[n]
        // 初始化 dp[1] = 1 dp[2] = 2
        if(n == 1){
            return 1;
        }
        if(n== 2){
            return 2;
        }
        int[] dp = new int[n+1];
        dp[1] = 1;
        dp[2] = 2;
        for (int i = 3; i <= n; i++) {
            dp[i] = dp[i-1] + dp[i-2];
        }
        return dp[n];
    }
    @Test
    public void testClimbStairs(){
        System.out.println(climbStairs(3));
    }
}
