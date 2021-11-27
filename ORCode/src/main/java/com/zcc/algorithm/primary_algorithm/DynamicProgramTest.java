package com.zcc.algorithm.primary_algorithm;

import org.junit.Test;

/**
 * @author zcc
 * @version 1.0
 * @date 2021/10/22 9:11
 */
public class DynamicProgramTest {


    /**
     * 假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
     * <p>
     * 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
     * <p>
     * 注意：给定 n 是一个正整数。
     * <p>
     * 示例 1：
     * <p>
     * 输入： 2
     * 输出： 2
     * 解释： 有两种方法可以爬到楼顶。
     * 1.  1 阶 + 1 阶
     * 2.  2 阶
     * 示例 2：
     * <p>
     * 输入： 3
     * 输出： 3
     * 解释： 有三种方法可以爬到楼顶。
     * 1.  1 阶 + 1 阶 + 1 阶
     * 2.  1 阶 + 2 阶
     * 3.  2 阶 + 1 阶
     * <p>
     * 作者：力扣 (LeetCode)
     * 链接：https://leetcode-cn.com/leetbook/read/top-interview-questions-easy/xn854d/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     *
     * @param n
     * @return
     */
    public int climbStairs(int n) {
        // 定义dp[i] 的含义 n = dp[i]的值代表第i层台阶有 n 种走法
        // 找到关系式 dp[i] = dp[i-1] + dp[i-2] n层台阶有 dp[n]
        // 初始化 dp[1] = 1 dp[2] = 2
        if (n == 1) {
            return 1;
        }
        if (n == 2) {
            return 2;
        }
        int[] dp = new int[n + 1];
        dp[1] = 1;
        dp[2] = 2;
        for (int i = 3; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }


    /**
     * 买卖股票的最佳时机
     * 给定一个数组 prices ，它的第 i 个元素 prices[i] 表示一支给定股票第 i 天的价格。
     * <p>
     * 你只能选择 某一天 买入这只股票，并选择在 未来的某一个不同的日子 卖出该股票。设计一个算法来计算你所能获取的最大利润。
     * <p>
     * 返回你可以从这笔交易中获取的最大利润。如果你不能获取任何利润，返回 0 。
     * <p>
     *  
     * <p>
     * 示例 1：
     * <p>
     * 输入：[7,1,5,3,6,4]
     * 输出：5
     * 解释：在第 2 天（股票价格 = 1）的时候买入，在第 5 天（股票价格 = 6）的时候卖出，最大利润 = 6-1 = 5 。
     * 注意利润不能是 7-1 = 6, 因为卖出价格需要大于买入价格；同时，你不能在买入前卖出股票。
     * 示例 2：
     * <p>
     * 输入：prices = [7,6,4,3,1]
     * 输出：0
     * 解释：在这种情况下, 没有交易完成, 所以最大利润为 0。
     *  
     * <p>
     * 提示：
     * <p>
     * 1 <= prices.length <= 105
     * 0 <= prices[i] <= 104
     * <p>
     * 作者：力扣 (LeetCode)
     * 链接：https://leetcode-cn.com/leetbook/read/top-interview-questions-easy/xn8fsh/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     *
     * @param prices
     * @return
     */
    public int maxProfit(int[] prices) {
        /**
         * 动态规划解法
         */

//        if (prices == null || prices.length == 0)
//            return 0;
//        int length = prices.length;
//        // 1.确定状态 dp[i][0] 表示股票抛售 dp[i][1] 表示股票未抛售
//        int dp[][] = new int[length][2];
//        // 3.初始条件
//        dp[0][0] = 0;
//        dp[0][1] = -prices[0];
//        // 4.计算
//        for (int i = 1; i < length; i++) {
//            // 2.转移公式
//            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);
//            dp[i][1] = Math.max(dp[i - 1][1], -prices[i]);
//        }
//        return dp[length-1][0];

        /**
         * doublePointer
         */
        if (prices.length == 1) {
            return 0;
        }
        int slowIndex = 0;
        int fastIndex = slowIndex + 1;
        int maxRes = 0;
        while (fastIndex < prices.length) {
            if (prices[fastIndex] < prices[slowIndex]) {
                slowIndex = fastIndex;
                fastIndex++;
                continue;
            } else {
                int maxResTemp = prices[fastIndex] - prices[slowIndex];
                if (maxResTemp > maxRes) {
                    maxRes = maxResTemp;
                }
                fastIndex++;
            }
        }
        return maxRes;
    }

    /**
     * 最大子序和
     * 给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
     * <p>
     *  
     * <p>
     * 示例 1：
     * <p>
     * 输入：nums = [-2,1,-3,4,-1,2,1,-5,4]
     * 输出：6
     * 解释：连续子数组 [4,-1,2,1] 的和最大，为 6 。
     * 示例 2：
     * <p>
     * 输入：nums = [1]
     * 输出：1
     * 示例 3：
     * <p>
     * 输入：nums = [0]
     * 输出：0
     * 示例 4：
     * <p>
     * 输入：nums = [-1]
     * 输出：-1
     * 示例 5：
     * <p>
     * 输入：nums = [-100000]
     * 输出：-100000
     *  
     * <p>
     * 提示：
     * <p>
     * 1 <= nums.length <= 105
     * -104 <= nums[i] <= 104
     *  
     * <p>
     * 进阶：如果你已经实现复杂度为 O(n) 的解法，尝试使用更为精妙的 分治法 求解。
     * <p>
     * 作者：力扣 (LeetCode)
     * 链接：https://leetcode-cn.com/leetbook/read/top-interview-questions-easy/xn3cg3/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     *
     * @param nums
     * @return
     */
    public int maxSubArray(int[] nums) {
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        int max = nums[0];
        for (int i = 1; i < nums.length; i++) {
            dp[i] = Math.max(dp[i - 1], 0) + nums[i];
            max = Math.max(max, dp[i]);
        }
        return max;
    }

    /**
     * 打家劫舍
     * 你是一个专业的小偷，计划偷窃沿街的房屋。每间房内都藏有一定的现金，影响你偷窃的唯一制约因素就是相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。
     * <p>
     * 给定一个代表每个房屋存放金额的非负整数数组，计算你 不触动警报装置的情况下 ，一夜之内能够偷窃到的最高金额。
     * <p>
     *  
     * <p>
     * 示例 1：
     * <p>
     * 输入：[1,2,3,1]
     * 输出：4
     * 解释：偷窃 1 号房屋 (金额 = 1) ，然后偷窃 3 号房屋 (金额 = 3)。
     *      偷窃到的最高金额 = 1 + 3 = 4 。
     * 示例 2：
     * <p>
     * 输入：[2,7,9,3,1]
     * 输出：12
     * 解释：偷窃 1 号房屋 (金额 = 2), 偷窃 3 号房屋 (金额 = 9)，接着偷窃 5 号房屋 (金额 = 1)。
     *      偷窃到的最高金额 = 2 + 9 + 1 = 12 。
     *  
     * <p>
     * 提示：
     * <p>
     * 1 <= nums.length <= 100
     * 0 <= nums[i] <= 400
     * <p>
     * 作者：力扣 (LeetCode)
     * 链接：https://leetcode-cn.com/leetbook/read/top-interview-questions-easy/xnq4km/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     *
     * @param nums
     * @return
     */
    public int rob(int[] nums) {

        /**
         * 动态规划
         */
        int length = nums.length;
        int[][] dp = new int[length][2];
        dp[0][0] = nums[0];
        dp[0][1] = 0;
        for (int i = 1; i < length; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1]);
            dp[i][1] = dp[i - 1][0] + nums[i];
        }
        return Math.max(dp[length-1][0],dp[length-1][1]);

    }

    @Test
    public void testClimbStairs() {
        System.out.println(climbStairs(3));
    }

    @Test
    public void testMaxProfit() {
        System.out.println(maxProfit(new int[]{7, 1, 5, 3, 6, 4}));
    }

    @Test
    public void testMaxSubArray() {
        System.out.println(maxSubArray(new int[]{-2}));
    }

    @Test
    public void testRob() {
        System.out.println(rob(new int[]{1,1}));
    }

}
