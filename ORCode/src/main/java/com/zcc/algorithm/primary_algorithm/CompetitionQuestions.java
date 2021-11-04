package com.zcc.algorithm.primary_algorithm;

import org.junit.Test;

/**
 * @author zcc
 * @version 1.0
 * @date 2021/10/22 16:23
 */
public class CompetitionQuestions {

    /**
     * 句子是由若干 token 组成的一个列表，token 间用 单个 空格分隔，句子没有前导或尾随空格。每个 token 要么是一个由数字 0-9 组成的不含前导零的 正整数 ，要么是一个由小写英文字母组成的 单词 。
     * <p>
     * 示例，"a puppy has 2 eyes 4 legs" 是一个由 7 个 token 组成的句子："2" 和 "4" 是数字，其他像 "puppy" 这样的 tokens 属于单词。
     * 给你一个表示句子的字符串 s ，你需要检查 s 中的 全部 数字是否从左到右严格递增（即，除了最后一个数字，s 中的 每个 数字都严格小于它 右侧 的数字）。
     * <p>
     * 如果满足题目要求，返回 true ，否则，返回 false 。
     * <p>
     *  
     * <p>
     * 示例 1：
     * <p>
     * <p>
     * <p>
     * 输入：s = "1 box has 3 blue 4 red 6 green and 12 yellow marbles"
     * 输出：true
     * 解释：句子中的数字是：1, 3, 4, 6, 12 。
     * 这些数字是按从左到右严格递增的 1 < 3 < 4 < 6 < 12 。
     * 示例 2：
     * <p>
     * 输入：s = "hello world 5 x 5"
     * 输出：false
     * 解释：句子中的数字是：5, 5 。这些数字不是严格递增的。
     * 示例 3：
     * <p>
     * <p>
     * <p>
     * 输入：s = "sunset is at 7 51 pm overnight lows will be in the low 50 and 60 s"
     * 输出：false
     * 解释：s 中的数字是：7, 51, 50, 60 。这些数字不是严格递增的。
     * 示例 4：
     * <p>
     * 输入：s = "4 5 11 26"
     * 输出：true
     * 解释：s 中的数字是：4, 5, 11, 26 。
     * 这些数字是按从左到右严格递增的：4 < 5 < 11 < 26 。
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/check-if-numbers-are-ascending-in-a-sentence
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * 3 <= s.length <= 200
     * s 由小写英文字母、空格和数字 0 到 9 组成（包含 0 和 9）
     * s 中数字 token 的数目在 2 和 100 之间（包含 2 和 100）
     * s 中的 token 之间由单个空格分隔
     * s 中至少有 两个 数字
     * s 中的每个数字都是一个 小于 100 的 正 数，且不含前导零
     * s 不含前导或尾随空格
     */
    public boolean areNumbersAscending(String s) {
        String[] s1 = s.split(" ");
        int k = 0;
        for (int i = 0; i < s1.length; i++) {
            if (s1[i].charAt(0) - '0' > 9 || s1[i].charAt(0) - '0' < 0) {
                continue;
            }
            int i1 = Integer.parseInt(s1[i]);
            if (k >= i1)
                return false;
            k = i1;
        }
        return true;
    }

    @Test
    public void testAreNumbersAscending() {
        String s = "4 5 11 26";
        System.out.println(areNumbersAscending(s));
    }

    /**
     * 2043. 简易银行系统
     * 你的任务是为一个很受欢迎的银行设计一款程序，以自动化执行所有传入的交易（转账，存款和取款）。银行共有 n 个账户，编号从 1 到 n 。每个账号的初始余额存储在一个下标从 0 开始的整数数组 balance 中，其中第 (i + 1) 个账户的初始余额是 balance[i] 。
     * <p>
     * 请你执行所有 有效的 交易。如果满足下面全部条件，则交易 有效 ：
     * <p>
     * 指定的账户数量在 1 和 n 之间，且
     * 取款或者转账需要的钱的总数 小于或者等于 账户余额。
     * 实现 Bank 类：
     * <p>
     * Bank(long[] balance) 使用下标从 0 开始的整数数组 balance 初始化该对象。
     * boolean transfer(int account1, int account2, long money) 从编号为 account1 的账户向编号为 account2 的账户转帐 money 美元。如果交易成功，返回 true ，否则，返回 false 。
     * boolean deposit(int account, long money) 向编号为 account 的账户存款 money 美元。如果交易成功，返回 true ；否则，返回 false 。
     * boolean withdraw(int account, long money) 从编号为 account 的账户取款 money 美元。如果交易成功，返回 true ；否则，返回 false 。
     * <p>
     * <p>
     * 示例：
     * <p>
     * 输入：
     * ["Bank", "withdraw", "transfer", "deposit", "transfer", "withdraw"]
     * [[[10, 100, 20, 50, 30]], [3, 10], [5, 1, 20], [5, 20], [3, 4, 15], [10, 50]]
     * 输出：
     * [null, true, true, true, false, false]
     * <p>
     * 解释：
     * Bank bank = new Bank([10, 100, 20, 50, 30]);
     * bank.withdraw(3, 10);    // 返回 true ，账户 3 的余额是 $20 ，所以可以取款 $10 。
     * // 账户 3 余额为 $20 - $10 = $10 。
     * bank.transfer(5, 1, 20); // 返回 true ，账户 5 的余额是 $30 ，所以可以转账 $20 。
     * // 账户 5 的余额为 $30 - $20 = $10 ，账户 1 的余额为 $10 + $20 = $30 。
     * bank.deposit(5, 20);     // 返回 true ，可以向账户 5 存款 $20 。
     * // 账户 5 的余额为 $10 + $20 = $30 。
     * bank.transfer(3, 4, 15); // 返回 false ，账户 3 的当前余额是 $10 。
     * // 所以无法转账 $15 。
     * bank.withdraw(10, 50);   // 返回 false ，交易无效，因为账户 10 并不存在。
     * <p>
     * <p>
     * 提示：
     * <p>
     * n == balance.length
     * 1 <= n, account, account1, account2 <= 105
     * 0 <= balance[i], money <= 1012
     * transfer, deposit, withdraw 三个函数，每个 最多调用 104 次
     * 通过次数4,422提交次数7,375
     */
    static class Bank {

        private long[] balance;

        public Bank(long[] balance) {
            this.balance = balance;
        }

        /**
         * 转账
         *
         * @param account1 转出账户
         * @param account2 转入账户
         * @param money    转账金额
         * @return
         */
        public boolean transfer(int account1, int account2, long money) {
            Long aLong = account1 > balance.length ? 0 : balance[account1 - 1];
            Long bLong = account2 > balance.length ? 0 : balance[account2 - 1];
            if (aLong == 0 || bLong == 0 || aLong - money < 0) {
                return false;
            }
            balance[account1 - 1] = aLong - money;
            balance[account2 - 1] = bLong + money;
            return true;
        }

        /**
         * 存款
         *
         * @param account 存款账户
         * @param money   存款金额
         * @return
         */
        public boolean deposit(int account, long money) {
            Long aLong = account > balance.length ? 0 : balance[account - 1];
            if (aLong == 0) {
                return false;
            }
            balance[account - 1] = aLong + money;
            return true;
        }

        /**
         * 取款
         *
         * @param account 取款账户
         * @param money   取款金额
         * @return
         */
        public boolean withdraw(int account, long money) {
            Long aLong = account > balance.length ? 0 : balance[account - 1];
            if (aLong == 0 || aLong - money < 0) {
                return false;
            }
            balance[account - 1] = aLong - money;
            return true;
        }
    }

    @Test
    public void testBank() {
        long[] s = {10L, 100L, 20L, 50L, 30L};
        Bank bank = new Bank(s);
        System.out.println(bank.withdraw(3, 10));
        System.out.println(bank.transfer(5, 1, 20));
        System.out.println(bank.deposit(5, 20));
        System.out.println(bank.transfer(3, 4, 15));
        System.out.println(bank.withdraw(10, 50));
        System.out.println(bank.balance);
    }

    /**
     * 2044. 统计按位或能得到最大值的子集数目
     * 给你一个整数数组 nums ，请你找出 nums 子集 按位或 可能得到的 最大值 ，并返回按位或能得到最大值的 不同非空子集的数目 。
     * <p>
     * 如果数组 a 可以由数组 b 删除一些元素（或不删除）得到，则认为数组 a 是数组 b 的一个 子集 。如果选中的元素下标位置不一样，则认为两个子集 不同 。
     * <p>
     * 对数组 a 执行 按位或 ，结果等于 a[0] OR a[1] OR ... OR a[a.length - 1]（下标从 0 开始）。
     * <p>
     * <p>
     * <p>
     * 示例 1：
     * <p>
     * 输入：nums = [3,1]
     * 输出：2
     * 解释：子集按位或能得到的最大值是 3 。有 2 个子集按位或可以得到 3 ：
     * - [3]
     * - [3,1]
     * 示例 2：
     * <p>
     * 输入：nums = [2,2,2]
     * 输出：7
     * 解释：[2,2,2] 的所有非空子集的按位或都可以得到 2 。总共有 23 - 1 = 7 个子集。
     * 示例 3：
     * <p>
     * 输入：nums = [3,2,1,5]
     * 输出：6
     * 解释：子集按位或可能的最大值是 7 。有 6 个子集按位或可以得到 7 ：
     * - [3,5]
     * - [3,1,5]
     * - [3,2,5]
     * - [3,2,1,5]
     * - [2,5]
     * - [2,1,5]
     * <p>
     * <p>
     * 提示：
     * <p>
     * 1 <= nums.length <= 16
     * 1 <= nums[i] <= 105
     *
     * @param nums
     */
//    public int countMaxOrSubsets(int[] nums) {
//        int count = 0;
//        int temp = nums[0];
//        // 计算按位于结果
//        for (int i = 1; i < nums.length; i++) {
//            temp|=nums[i];
//        }
//        final int  s = temp;
//        // 拆分子集
//        List<int[]> list = new ArrayList<>();
//        for (int i = 2; i <= nums.length; i++) {
//            list.add(Arrays.copyOf(nums,i));
//        }
//        for (int i = 0; i < nums.length; i++) {
//            list.add(new int[]{nums[i]});
//        }
//        // 符合按位于子集
//        List<int[]> collect = list.stream().filter(item -> {
//            int tempValue = item[0];
//            for (int i = 1; i < item.length; i++) {
//                tempValue |= item[i];
//            }
//            return s == tempValue ? true : false;
//        }).collect(Collectors.toList());
//        // 判断数组的不同组合数,计算count
//        count += collect.stream().map(this::countMix).reduce(this::add).get();
//
//        return count;
//    }
    public int add(int x,int y){
        return x+y;
    }
    private int ans = 0;
    public int countMaxOrSubsets(int[] nums) {
        // 按位或是不减的操作，所以全部 | 起来是最大值
        int sum = 0;
        for(int p : nums){
            sum |= p;
        }
        dfs(nums, sum, 0, 0);
        return ans;
    }
    public void dfs(int[] nums, int sum, int idx, int cur){
        // 剪枝
        if(cur == sum){
            ans += 1 << (nums.length - idx);
            return;
        }
        if(idx == nums.length){
            return;
        }
        // 加上这个数
        dfs(nums, sum, idx + 1, cur | nums[idx]);
        // 不加这个数
        dfs(nums, sum, idx + 1, cur);
    }
    public int countMix(int[] nums) {
        int count = 0;
//        if (nums.length == 0 || nums == null) {
//            return count;
//        }
//        if (nums.length==1)return ++count;
//        else count++;
//        Arrays.sort(nums);
//        int repeatCount = 0;
//        for (int i = 0, j = 1; i < nums.length; ) {
//            if (nums[i] != nums[j]) {
//                if (j + 2 < nums.length)
//                    j += 2;
//                else break;
//            } else {
//                repeatCount++;
//            }
//            if (i + 2 < nums.length)
//                i += 2;
//            else break;
//        }
//        for (int k = nums.length-count;k>1;k--)
//            count*=k;
        count += (int)Math.pow(2,nums.length)-1;
        return count;
    }

    @Test
    public void testCountMaxOrSubsets() {
        int[] s = {2,2,2};
        System.out.println(countMaxOrSubsets(s));
    }

}
