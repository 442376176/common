package com.zcc.algorithm.primary_algorithm;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import static java.lang.Integer.MAX_VALUE;

/**
 * @author zcc
 * @version 1.0
 * @date 2021/11/12 13:36
 */
public class OtherTest {
    /**
     * 位1的个数
     * 编写一个函数，输入是一个无符号整数（以二进制串的形式），返回其二进制表达式中数字位数为 '1' 的个数（也被称为汉明重量）。
     * <p>
     *  
     * <p>
     * 提示：
     * <p>
     * 请注意，在某些语言（如 Java）中，没有无符号整数类型。在这种情况下，输入和输出都将被指定为有符号整数类型，并且不应影响您的实现，因为无论整数是有符号的还是无符号的，其内部的二进制表示形式都是相同的。
     * 在 Java 中，编译器使用二进制补码记法来表示有符号整数。因此，在上面的 示例 3 中，输入表示有符号整数 -3。
     *  
     * <p>
     * 示例 1：
     * <p>
     * 输入：00000000000000000000000000001011
     * 输出：3
     * 解释：输入的二进制串 00000000000000000000000000001011 中，共有三位为 '1'。
     * 示例 2：
     * <p>
     * 输入：00000000000000000000000010000000
     * 输出：1
     * 解释：输入的二进制串 00000000000000000000000010000000 中，共有一位为 '1'。
     * 示例 3：
     * <p>
     * 输入：11111111111111111111111111111101
     * 输出：31
     * 解释：输入的二进制串 11111111111111111111111111111101 中，共有 31 位为 '1'。
     *  
     * <p>
     * 提示：
     * <p>
     * 输入必须是长度为 32 的 二进制串 。
     *  
     * <p>
     * 进阶：
     * <p>
     * 如果多次调用这个函数，你将如何优化你的算法？
     * <p>
     * 作者：力扣 (LeetCode)
     * 链接：https://leetcode-cn.com/leetbook/read/top-interview-questions-easy/xn1m0i/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     *
     * @param n
     * @return
     */
    // you need to treat n as an unsigned value
    public int hammingWeight(int n) {
        n = n - ((n >>> 1) & 0x55555555);
        n = (n & 0x33333333) + ((n >>> 2) & 0x33333333);
        n = (n + (n >>> 4)) & 0x0f0f0f0f;
        n = n + (n >>> 8);
        n = n + (n >>> 16);
        return n & 0x3f;
    }

    /**
     * 汉明距离
     * 两个整数之间的 汉明距离 指的是这两个数字对应二进制位不同的位置的数目。
     * <p>
     * 给你两个整数 x 和 y，计算并返回它们之间的汉明距离。
     * <p>
     *  
     * <p>
     * 示例 1：
     * <p>
     * 输入：x = 1, y = 4
     * 输出：2
     * 解释：
     * 1   (0 0 0 1)
     * 4   (0 1 0 0)
     * ↑   ↑
     * 上面的箭头指出了对应二进制位不同的位置。
     * 示例 2：
     * <p>
     * 输入：x = 3, y = 1
     * 输出：1
     *  
     * <p>
     * 提示：
     * <p>
     * 0 <= x, y <= 231 - 1
     * <p>
     * 作者：力扣 (LeetCode)
     * 链接：https://leetcode-cn.com/leetbook/read/top-interview-questions-easy/xnyode/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     *
     * @param x
     * @param y
     * @return
     */
    public int hammingDistance(int x, int y) {
        int i = x ^ y;
        int count = 0;
        char[] s = Integer.toBinaryString(i).toCharArray();
        for (int j = 0; j < s.length; j++) {
            if (s[j] == '1') {
                count++;
            }
        }
        return count;
    }

    public int hammingDistance1(int x, int y) {
        int xor = x ^ y;
        int res = 0;
        while (xor != 0) {
            res += xor & 1;
            xor >>= 1;
        }
        return res;
    }


    /**
     * 颠倒二进制位
     * 颠倒给定的 32 位无符号整数的二进制位。
     * <p>
     * 提示：
     * <p>
     * 请注意，在某些语言（如 Java）中，没有无符号整数类型。在这种情况下，输入和输出都将被指定为有符号整数类型，并且不应影响您的实现，因为无论整数是有符号的还是无符号的，其内部的二进制表示形式都是相同的。
     * 在 Java 中，编译器使用二进制补码记法来表示有符号整数。因此，在 示例 2 中，输入表示有符号整数 -3，输出表示有符号整数 -1073741825。
     *  
     * <p>
     * 示例 1：
     * <p>
     * 输入：n = 00000010100101000001111010011100
     * 输出：964176192 (00111001011110000010100101000000)
     * 解释：输入的二进制串 00000010100101000001111010011100 表示无符号整数 43261596，
     * 因此返回 964176192，其二进制表示形式为 00111001011110000010100101000000。
     * 示例 2：
     * <p>
     * 输入：n = 11111111111111111111111111111101
     * 输出：3221225471 (10111111111111111111111111111111)
     * 解释：输入的二进制串 11111111111111111111111111111101 表示无符号整数 4294967293，
     *   因此返回 3221225471 其二进制表示形式为 10111111111111111111111111111111 。
     *  
     * <p>
     * 提示：
     * <p>
     * 输入是一个长度为 32 的二进制字符串
     *  
     * <p>
     * 进阶: 如果多次调用这个函数，你将如何优化你的算法？
     * <p>
     * 作者：力扣 (LeetCode)
     * 链接：https://leetcode-cn.com/leetbook/read/top-interview-questions-easy/xnc5vg/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     *
     * @param n
     * @return
     */
    public static int reverseBits(int n) {
        int res = 0;
        for (int i = 0; i < 32; i++) {
            res <<= 1;
            res |= n & 1;
            n >>>= 1;
        }
        return res;
    }

    /**
     * 杨辉三角
     * 给定一个非负整数 numRows，生成「杨辉三角」的前 numRows 行。
     * <p>
     * 在「杨辉三角」中，每个数是它左上方和右上方的数的和。
     * <p>
     * <p>
     * <p>
     *  
     * <p>
     * 示例 1:
     * <p>
     * 输入: numRows = 5
     * 输出: [[1],[1,1],[1,2,1],[1,3,3,1],[1,4,6,4,1]]
     * 示例 2:
     * <p>
     * 输入: numRows = 1
     * 输出: [[1]]
     *  
     * <p>
     * 提示:
     * <p>
     * 1 <= numRows <= 30
     * <p>
     * 作者：力扣 (LeetCode)
     * 链接：https://leetcode-cn.com/leetbook/read/top-interview-questions-easy/xncfnv/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     *
     * @param numRows
     * @return
     */

    public static List<List<Integer>> generate(int numRows) {
        /**
         * 方案 1 时空性能都很差
         */
//        if (numRows == 1) {
//            return new ArrayList<List<Integer>>() {{
//                add(new ArrayList<Integer>() {{
//                    add(1);
//                }});
//            }};
//        }
//        List<List<Integer>> result = new LinkedList<List<Integer>>() {{
//            add(new ArrayList<Integer>() {{
//                add(1);
//            }});
//        }};
//        List<Integer> temp = result.get(0);
//        for (int i = 1; i < numRows; i++) {
//            List<Integer> element = new LinkedList<Integer>(){{add(1);}};
//            int size = temp.size();
//            for (int j = 0; j < size; j++) {
//                if (size>j+1){
//                    element.add(temp.get(j)+temp.get(j+1));
//                }else {
//                    element.add(1);
//                    break;
//                }
//            }
//            temp = element;
//            result.add(element);
//        }
//        return result;
        /**
         * 空间效率较差
         */
        List<List<Integer>> list = new ArrayList<>();
        for (int i = 0; i < numRows; i++) {
            List<Integer> li = new ArrayList<>();
            for (int j = 0; j <= i; j++) {
                if (j == 0 || j == i) {
                    li.add(1);
                } else {
                    //第i层的第j个元素 = i-1层的第j个元素 + i-1层的第j-1元素
                    li.add(list.get(i - 1).get(j - 1) + list.get(i - 1).get(j));
                }
            }
            list.add(li);
        }
        return list;

    }

    /**
     * 有效的括号
     * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串 s ，判断字符串是否有效。
     * <p>
     * 有效字符串需满足：
     * <p>
     * 左括号必须用相同类型的右括号闭合。
     * 左括号必须以正确的顺序闭合。
     *  
     * <p>
     * 示例 1：
     * <p>
     * 输入：s = "()"
     * 输出：true
     * 示例 2：
     * <p>
     * 输入：s = "()[]{}"
     * 输出：true
     * 示例 3：
     * <p>
     * 输入：s = "(]"
     * 输出：false
     * 示例 4：
     * <p>
     * 输入：s = "([)]"
     * 输出：false
     * 示例 5：
     * <p>
     * 输入：s = "{[]}"
     * 输出：true
     * <p>
     * 作者：力扣 (LeetCode)
     * 链接：https://leetcode-cn.com/leetbook/read/top-interview-questions-easy/xnbcaj/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     *
     * @param s
     * @return
     * @Describe: 思路：
     * 利用栈的特性解题
     * 遇到左括号将对应的右括号压入栈
     * 遇到右括号时
     * 栈不为空时：对比栈顶数据 相等则继续，不等则返回 false
     * 栈为空时： 返回false
     * 最后栈为空返回 true 否则返回false
     */
    public static boolean isValid(String s) {
        char[] chars = s.toCharArray();

        Stack stack = new Stack<>();
        for (char c : chars) {
            if (c == '(') {
                stack.push(')');
            } else if (c == '[') {
                stack.push(']');
            } else if (c == '{') {
                stack.push('}');
            } else {
                if (stack.isEmpty() || (char) stack.pop() != c) {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }

    /**
     * 缺失数字
     * 给定一个包含 [0, n] 中 n 个数的数组 nums ，找出 [0, n] 这个范围内没有出现在数组中的那个数。
     * <p>
     *  
     * <p>
     * 示例 1：
     * <p>
     * 输入：nums = [3,0,1]
     * 输出：2
     * 解释：n = 3，因为有 3 个数字，所以所有的数字都在范围 [0,3] 内。2 是丢失的数字，因为它没有出现在 nums 中。
     * 示例 2：
     * <p>
     * 输入：nums = [0,1]
     * 输出：2
     * 解释：n = 2，因为有 2 个数字，所以所有的数字都在范围 [0,2] 内。2 是丢失的数字，因为它没有出现在 nums 中。
     * 示例 3：
     * <p>
     * 输入：nums = [9,6,4,2,3,5,7,0,1]
     * 输出：8
     * 解释：n = 9，因为有 9 个数字，所以所有的数字都在范围 [0,9] 内。8 是丢失的数字，因为它没有出现在 nums 中。
     * 示例 4：
     * <p>
     * 输入：nums = [0]
     * 输出：1
     * 解释：n = 1，因为有 1 个数字，所以所有的数字都在范围 [0,1] 内。1 是丢失的数字，因为它没有出现在 nums 中。
     *  
     * <p>
     * 提示：
     * <p>
     * n == nums.length
     * 1 <= n <= 104
     * 0 <= nums[i] <= n
     * nums 中的所有数字都 独一无二
     *  
     * <p>
     * 进阶：你能否实现线性时间复杂度、仅使用额外常数空间的算法解决此问题?
     * <p>
     * 作者：力扣 (LeetCode)
     * 链接：https://leetcode-cn.com/leetbook/read/top-interview-questions-easy/xnj4mt/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     *
     * @param nums
     * @return
     * @Describe: 解题思路：
     * 等差数列求和 最后减数组的各个元素得到结果即为缺失数字
     */
    public static int missingNumber(int[] nums) {
        int sum = nums.length * (nums.length + 1) / 2;
        int s = 0;
        for (int num : nums) {
            s += num;
        }
        return sum - s;
    }

    /**
     * 367. 有效的完全平方数
     * 给定一个 正整数 num ，编写一个函数，如果 num 是一个完全平方数，则返回 true ，否则返回 false 。
     * <p>
     * 进阶：不要 使用任何内置的库函数，如  sqrt 。
     * <p>
     * <p>
     * <p>
     * 示例 1：
     * <p>
     * 输入：num = 16
     * 输出：true
     * 示例 2：
     * <p>
     * 输入：num = 14
     * 输出：false
     * <p>
     * <p>
     * 提示：
     * <p>
     * 1 <= num <= 2^31 - 1
     * 通过次数134,287提交次数299,429
     *
     * @param num
     * @return
     */
    public static boolean isPerfectSquare(int num) {
        if (num == 1) return true;
        int max = num / 2;
        int min = 1;

        while (min <= max)
        {
            int middle = (max + min) / 2;
            if (middle>46341){
                middle=46340;
                max = 46341;
            }
            int square = middle * middle;
            if (square == num) {
                return true;
            } else if (square > num) {
                max = middle - 1;
            } else {
                min = middle + 1;
            }

        }

        return false;
    }

    public static void main(String[] args) {
//        int[] nums = {1};
//        System.out.println(missingNumber(nums));
//        System.out.println(Math.sqrt(2147483647));
        System.out.println(isPerfectSquare(2147483647));
//        int i = reverseBits(43261596);
//        boolean valid = isValid("()[){}");
//        System.out.println(valid);
//        List<List<Integer>> generate = generate(5);
//        System.out.println(generate);
//        byte[] bytes = new byte[32];
//        int x = 9;
//        do{
//            System.out.println(x&1);
//            x>>>=1;
//        }while (x != 0);
//        System.out.println(Math.pow(2,3));

    }

    @Test
    public void testHammingWeight() {
        System.out.println(MAX_VALUE);
//        System.out.println(hammingWeight());
        String s = "sdsd";
        System.out.println(s.getBytes());
    }

    @Test
    public void testHammingDistance() {
        System.out.println(hammingDistance(4, 1));
        System.out.println(hammingDistance1(4, 1));
//        int s = 8 >> 1;
//        System.out.println(s);
//
//        System.out.println(s);
//        int t = -s >> 3;
//        System.out.println(t);
//        int q = -s >>> 3;
//        System.out.println(q);
    }
}
