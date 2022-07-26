package com.zcc.algorithm.primary_algorithm;

import java.util.Random;

/**
 * @author zcc
 * @version 1.0
 * @date 2021/11/5 11:14
 */
public class DesignProblemTest {

    /**
     * 打乱数组
     * 给你一个整数数组 nums ，设计算法来打乱一个没有重复元素的数组。
     * <p>
     * 实现 Solution class:
     * <p>
     * Solution(int[] nums) 使用整数数组 nums 初始化对象
     * int[] reset() 重设数组到它的初始状态并返回
     * int[] shuffle() 返回数组随机打乱后的结果
     *  
     * <p>
     * 示例：
     * <p>
     * 输入
     * ["Solution", "shuffle", "reset", "shuffle"]
     * [[[1, 2, 3]], [], [], []]
     * 输出
     * [null, [3, 1, 2], [1, 2, 3], [1, 3, 2]]
     * <p>
     * 解释
     * Solution solution = new Solution([1, 2, 3]);
     * solution.shuffle();    // 打乱数组 [1,2,3] 并返回结果。任何 [1,2,3]的排列返回的概率应该相同。例如，返回 [3, 1, 2]
     * solution.reset();      // 重设数组到它的初始状态 [1, 2, 3] 。返回 [1, 2, 3]
     * solution.shuffle();    // 随机返回数组 [1, 2, 3] 打乱后的结果。例如，返回 [1, 3, 2]
     *  
     * <p>
     * 提示：
     * <p>
     * 1 <= nums.length <= 200
     * -106 <= nums[i] <= 106
     * nums 中的所有元素都是 唯一的
     * 最多可以调用 5 * 104 次 reset 和 shuffle
     * <p>
     * 作者：力扣 (LeetCode)
     * 链接：https://leetcode-cn.com/leetbook/read/top-interview-questions-easy/xn6gq1/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */
    static class Solution {
        private int[] nums;
        private Random random;

        public Solution(int[] nums) {
            this.nums = nums;
            random = new Random();
        }

        //重置数组，就是返回之前的数组
        public int[] reset() {
            return nums;
        }


        //打乱数组
        public int[] shuffle() {
            if (nums == null)
                return null;
            int[] a = nums.clone();//clone一个新的数组
            for (int j = 1; j < a.length; j++) {
                int i = random.nextInt(j + 1);
                swap(a, i, j);
            }
            return a;
        }


        private void swap(int[] a, int i, int j) {
            if (i != j) {
                a[i] = a[i] + a[j];
                a[j] = a[i] - a[j];
                a[i] = a[i] - a[j];
            }
        }


    }


    /**
     * 设计一个支持 push ，pop ，top 操作，并能在常数时间内检索到最小元素的栈。
     * <p>
     * push(x) —— 将元素 x 推入栈中。
     * pop() —— 删除栈顶的元素。
     * top() —— 获取栈顶元素。
     * getMin() —— 检索栈中的最小元素。
     *  
     * <p>
     * 示例:
     * <p>
     * 输入：
     * ["MinStack","push","push","push","getMin","pop","top","getMin"]
     * [[],[-2],[0],[-3],[],[],[],[]]
     * <p>
     * 输出：
     * [null,null,null,null,-3,null,0,-2]
     * <p>
     * 解释：
     * MinStack minStack = new MinStack();
     * minStack.push(-2);
     * minStack.push(0);
     * minStack.push(-3);
     * minStack.getMin();   --> 返回 -3.
     * minStack.pop();
     * minStack.top();      --> 返回 0.
     * minStack.getMin();   --> 返回 -2.
     *  
     * <p>
     * 提示：
     * <p>
     * pop、top 和 getMin 操作总是在 非空栈 上调用。
     * <p>
     * 作者：力扣 (LeetCode)
     * 链接：https://leetcode-cn.com/leetbook/read/top-interview-questions-easy/xnkq37/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */
    static class MinStack {
        //链表头，相当于栈顶
        private ListNode head;

        //压栈，需要判断栈是否为空
        public void push(int x) {
            if (empty())
                head = new ListNode(x, x, null);
            else
                head = new ListNode(x, Math.min(x, head.min), head);
        }

        //出栈，相当于把链表头删除
        public void pop() {
            if (empty())
                throw new IllegalStateException("栈为空……");
            head = head.next;
        }

        //栈顶的值也就是链表头的值
        public int top() {
            if (empty())
                throw new IllegalStateException("栈为空……");
            return head.val;
        }

        //链表中头结点保存的是整个链表最小的值，所以返回head.min也就是
        //相当于返回栈中最小的值
        public int getMin() {
            if (empty())
                throw new IllegalStateException("栈为空……");
            return head.min;
        }

        //判断栈是否为空
        private boolean empty() {
            return head == null;
        }
        class ListNode {
            public int val;
            public int min;//最小值
            public ListNode next;

            public ListNode(int val, int min, ListNode next) {
                this.val = val;
                this.min = min;
                this.next = next;
            }
        }
    }



    public static void main(String[] args) {
/**
 * testRandom
 */
//        Random random = new Random();
//        for (int i = 0; i < 10; i++) {
//            int i1 = random.nextInt(10);
//            System.out.println(i1);
//        }
//
        /**
         * test Solution
         */
//        Solution s = new Solution(new int[]{1, 2, 3});
//        int[] shuffle = s.shuffle();
//        for (int i = 0; i < shuffle.length; i++) {
//            System.out.print(shuffle[i] + " ");
//        }
//        System.out.println("\t----------------------------------------------");
//        int[] reset = s.reset();
//        for (int i = 0; i < reset.length; i++) {
//            System.out.print(reset[i] + " ");
//        }

        /**
         * test MinStack
         */
        MinStack minStack = new MinStack();
        minStack.push(-2);
        minStack.push(0);
        minStack.push(-3);

        System.out.println(minStack.getMin());
        minStack.pop();
        System.out.println(minStack.top());
        System.out.println(minStack.getMin());

    }
}
