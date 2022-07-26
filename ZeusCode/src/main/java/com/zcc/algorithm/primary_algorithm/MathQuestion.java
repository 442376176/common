package com.zcc.algorithm.primary_algorithm;

import org.junit.Test;

/**
 * @author zcc
 * @version 1.0
 * @date 2021/11/11 16:40
 */
public class MathQuestion {


    /**
     * 计数质数
     * 统计所有小于非负整数 n 的质数的数量。
     * <p>
     *  
     * <p>
     * 示例 1：
     * <p>
     * 输入：n = 10
     * 输出：4
     * 解释：小于 10 的质数一共有 4 个, 它们是 2, 3, 5, 7 。
     * 示例 2：
     * <p>
     * 输入：n = 0
     * 输出：0
     * 示例 3：
     * <p>
     * 输入：n = 1
     * 输出：0
     *  
     * <p>
     * 提示：
     * <p>
     * 0 <= n <= 5 * 106
     * 相关标签
     * <p>
     * 作者：力扣 (LeetCode)
     * 链接：https://leetcode-cn.com/leetbook/read/top-interview-questions-easy/xnzlu6/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     *
     * @param n
     * @return
     */
    public int countPrimes(int n) {
//        if (n == 4) return 2;
//        if (n == 3) return 1;
//        if (n > 4) {
//            int count = 2;
//            for (int i = 5; i < n; i+=2) {
//                for (int j = 3; j <= i / 2; j+=2) {
//                    if (i % j == 0) break;
//                    if (i % j != 0 && j + 1 > i / 2) count++;
//                }
//            }
//            return count;
//        }
//        return 0;
        boolean[] arr = new boolean[n];
        int cnt = 0;
        for (int i = 2; i < n; i++) {
            if (arr[i]) continue;
            cnt++;
            for (int j = i; j < n; j += i) {
                arr[j] = true;
            }
        }
        return cnt;
    }

    /**
     * 罗马数字转整数
     * 罗马数字包含以下七种字符: I， V， X， L，C，D 和 M。
     * <p>
     * 字符          数值
     * I             1
     * V             5
     * X             10
     * L             50
     * C             100
     * D             500
     * M             1000
     * 例如， 罗马数字 2 写做 II ，即为两个并列的 1 。12 写做 XII ，即为 X + II 。 27 写做  XXVII, 即为 XX + V + II 。
     * <p>
     * 通常情况下，罗马数字中小的数字在大的数字的右边。但也存在特例，例如 4 不写做 IIII，而是 IV。数字 1 在数字 5 的左边，所表示的数等于大数 5 减小数 1 得到的数值 4 。同样地，数字 9 表示为 IX。这个特殊的规则只适用于以下六种情况：
     * <p>
     * I 可以放在 V (5) 和 X (10) 的左边，来表示 4 和 9。
     * X 可以放在 L (50) 和 C (100) 的左边，来表示 40 和 90。 
     * C 可以放在 D (500) 和 M (1000) 的左边，来表示 400 和 900。
     * 给定一个罗马数字，将其转换成整数。
     * <p>
     *  
     * <p>
     * 示例 1:
     * <p>
     * 输入: s = "III"
     * 输出: 3
     * 示例 2:
     * <p>
     * 输入: s = "IV"
     * 输出: 4
     * 示例 3:
     * <p>
     * 输入: s = "IX"
     * 输出: 9
     * 示例 4:
     * <p>
     * 输入: s = "LVIII"
     * 输出: 58
     * 解释: L = 50, V= 5, III = 3.
     * 示例 5:
     * <p>
     * 输入: s = "MCMXCIV"
     * 输出: 1994
     * 解释: M = 1000, CM = 900, XC = 90, IV = 4.
     *  
     * <p>
     * 提示：
     * <p>
     * 1 <= s.length <= 15
     * s 仅含字符 ('I', 'V', 'X', 'L', 'C', 'D', 'M')
     * 题目数据保证 s 是一个有效的罗马数字，且表示整数在范围 [1, 3999] 内
     * 题目所给测试用例皆符合罗马数字书写规则，不会出现跨位等情况。
     * IL 和 IM 这样的例子并不符合题目要求，49 应该写作 XLIX，999 应该写作 CMXCIX 。
     * 关于罗马数字的详尽书写规则，可以参考 罗马数字 - Mathematics 。
     * <p>
     * 作者：力扣 (LeetCode)
     * 链接：https://leetcode-cn.com/leetbook/read/top-interview-questions-easy/xn4n7c/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     *
     * @param s
     * @return
     */
    public int romanToInt(String s) {
        char[] chars = s.toCharArray();
        int sum = 0;
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == 'I' && i < chars.length - 1 && (chars[i + 1] == 'V' || chars[i + 1] == 'X')) {
                sum += chars[i + 1] == 'V' ? 4 : 9;
                i++;
                continue;
            }
            if (chars[i] == 'X' && i < chars.length - 1 && (chars[i + 1] == 'L' || chars[i + 1] == 'C')) {
                sum += chars[i + 1] == 'L' ? 40 : 90;
                i++;
                continue;
            }
            if (chars[i] == 'C' && i < chars.length - 1 && (chars[i + 1] == 'D' || chars[i + 1] == 'M')) {
                sum += chars[i + 1] == 'D' ? 400 : 900;
                i++;
                continue;
            }
            sum += getValue(chars[i]);
        }
        return sum;
    }

    public int getValue(char c) {
        switch (c) {
            case 'I':
                return 1;
            case 'V':
                return 5;
            case 'X':
                return 10;
            case 'L':
                return 50;
            case 'C':
                return 100;
            case 'D':
                return 500;
            case 'M':
                return 1000;
            default:
                return 0;
        }
    }


    @Test
    public void testCountPrimes() {
        System.out.println(countPrimes(499979));
    }

    @Test
    public void testRomanToInt() {
        System.out.println(romanToInt("III"));
    }
}
