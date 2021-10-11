package com.zcc.algorithm;

import org.apache.commons.collections.CollectionUtils;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

/**
 * @author zcc
 * @version 1.0
 * @date 2021/9/22 15:15
 */
public class StringTest {
    /**
     * @param s
     * @return
     * @deprecated 给定一个字符串，找到它的第一个不重复的字符，并返回它的索引。如果不存在，则返回 -1。
     */
    public int firstUniqChar(String s) {
        char[] chars = s.toCharArray();
        HashMap<Integer, Character> hashMap = new HashMap<>();
        for (int i = 0; i < chars.length; i++) {
//            hashMap.put("",)

            for (int j = 0; j < chars.length; j++) {
                if (i == j) {
                    if (j == chars.length - 1) {
                        return i;
                    }
                    continue;
                }
                if (chars[i] == chars[j]) {
                    break;
                }
                if (j == chars.length - 1) {
                    return i;
                }
            }
        }
        return -1;
    }

    /**
     * 给定两个字符串 s 和 t ，编写一个函数来判断 t 是否是 s 的字母异位词。
     * <p>
     * 注意：若 s 和 t 中每个字符出现的次数都相同，则称 s 和 t 互为字母异位词。
     * <p>
     * 作者：力扣 (LeetCode)
     * 链接：https://leetcode-cn.com/leetbook/read/top-interview-questions-easy/xn96us/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     *
     * @param s
     * @param t
     * @return
     */
    public boolean isAnagram(String s, String t) {
        /**
         * 最拉胯算法
         */
//        if (s.length() != t.length()) {
//            return false;
//        }
//        char[] chars = s.toCharArray();
//        char[] chars1 = t.toCharArray();
//        HashMap<Character, Integer> hashMap = new HashMap<>();
//        for (int i = 0; i < s.length(); i++) {
//            if (hashMap.get(chars[i])!=null) {
//                hashMap.put(chars[i],hashMap.get(chars[i])+1);
//            }else {
//                hashMap.put(chars[i], 1);
//            }
//        }
//        for (int i = 0; i <t.length(); i++) {
//
//            if (hashMap.get(chars1[i]) == null) {
//                return false;
//            }else {
//                    hashMap.put(chars1[i],hashMap.get(chars1[i])-1);
//                }
//            }
//
//        Set<Map.Entry<Character, Integer>> entries = hashMap.entrySet();
//        for (Map.Entry<Character, Integer> item: hashMap.entrySet()){
//            if (item.getValue()!=0) {
//                return false;
//            }
//        }
//        return true;
        if (s.length() != t.length())
            return false;
        int[] letterCount = new int[26];
        //统计字符串s中的每个字符的数量
        for (int i = 0; i < s.length(); i++)
            letterCount[s.charAt(i) - 'a']++;
        //减去字符串t中的每个字符的数量
        for (int i = 0; i < t.length(); i++) {
            //如果当前字符等于0，直接返回false，
            if (letterCount[t.charAt(i) - 'a'] == 0)
                return false;
            letterCount[t.charAt(i) - 'a']--;
        }
        return true;

    }


    /**
     * 验证回文串
     *
     * @param s
     * @return * 给定一个字符串，验证它是否是回文串，只考虑字母和数字字符，可以忽略字母的大小写。
     * *
     * * 说明：本题中，我们将空字符串定义为有效的回文串。
     * *
     * * 示例 1:
     * *
     * * 输入: "A man, a plan, a canal: Panama"
     * * 输出: true
     * * 解释："amanaplanacanalpanama" 是回文串
     * * 示例 2:
     * *
     * * 输入: "race a car"
     * * 输出: false
     * * 解释："raceacar" 不是回文串
     * *
     * * 作者：力扣 (LeetCode)
     * * 链接：https://leetcode-cn.com/leetbook/read/top-interview-questions-easy/xne8id/
     * * 来源：力扣（LeetCode）
     * * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */
    public boolean isPalindrome(String s) {
        char[] chars = s.toCharArray();
        int i = 0;
        int j = chars.length - 1;
        while (i <= j) {
            while (i < j && !Character.isLetterOrDigit(s.charAt(i)))
                i++;
            while (i < j && !Character.isLetterOrDigit(s.charAt(j)))
                j--;
            if (Character.toUpperCase(chars[i]) != Character.toUpperCase(chars[j])) {
                return false;
            } else {
                i++;
                j--;
            }
        }
        return true;
    }

    /**
     * 请你来实现一个 myAtoi(string s) 函数，使其能将字符串转换成一个 32 位有符号整数（类似 C/C++ 中的 atoi 函数）。
     * <p>
     * 函数 myAtoi(string s) 的算法如下：
     * <p>
     * 读入字符串并丢弃无用的前导空格
     * 检查下一个字符（假设还未到字符末尾）为正还是负号，读取该字符（如果有）。 确定最终结果是负数还是正数。 如果两者都不存在，则假定结果为正。
     * 读入下一个字符，直到到达下一个非数字字符或到达输入的结尾。字符串的其余部分将被忽略。
     * 将前面步骤读入的这些数字转换为整数（即，"123" -> 123， "0032" -> 32）。如果没有读入数字，则整数为 0 。必要时更改符号（从步骤 2 开始）。
     * 如果整数数超过 32 位有符号整数范围 [−231,  231 − 1] ，需要截断这个整数，使其保持在这个范围内。具体来说，小于 −231 的整数应该被固定为 −231 ，大于 231 − 1 的整数应该被固定为 231 − 1 。
     * 返回整数作为最终结果。
     * <p>
     * 作者：力扣 (LeetCode)
     * 链接：https://leetcode-cn.com/leetbook/read/top-interview-questions-easy/xnoilh/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * 示例 1：
     * <p>
     * 输入：s = "42"
     * 输出：42
     * 解释：加粗的字符串为已经读入的字符，插入符号是当前读取的字符。
     * 第 1 步："42"（当前没有读入字符，因为没有前导空格）
     * ^
     * 第 2 步："42"（当前没有读入字符，因为这里不存在 '-' 或者 '+'）
     * ^
     * 第 3 步："42"（读入 "42"）
     * ^
     * 解析得到整数 42 。
     * 由于 "42" 在范围 [-231, 231 - 1] 内，最终结果为 42 。
     * 示例 2：
     * <p>
     * 输入：s = "   -42"
     * 输出：-42
     * 解释：
     * 第 1 步："   -42"（读入前导空格，但忽视掉）
     * ^
     * 第 2 步："   -42"（读入 '-' 字符，所以结果应该是负数）
     * ^
     * 第 3 步："   -42"（读入 "42"）
     * ^
     * 解析得到整数 -42 。
     * 由于 "-42" 在范围 [-231, 231 - 1] 内，最终结果为 -42 。
     * 示例 3：
     * <p>
     * 输入：s = "4193 with words"
     * 输出：4193
     * 解释：
     * 第 1 步："4193 with words"（当前没有读入字符，因为没有前导空格）
     * ^
     * 第 2 步："4193 with words"（当前没有读入字符，因为这里不存在 '-' 或者 '+'）
     * ^
     * 第 3 步："4193 with words"（读入 "4193"；由于下一个字符不是一个数字，所以读入停止）
     * ^
     * 解析得到整数 4193 。
     * 由于 "4193" 在范围 [-231, 231 - 1] 内，最终结果为 4193 。
     * 示例 4：
     * <p>
     * 输入：s = "words and 987"
     * 输出：0
     * 解释：
     * 第 1 步："words and 987"（当前没有读入字符，因为没有前导空格）
     * ^
     * 第 2 步："words and 987"（当前没有读入字符，因为这里不存在 '-' 或者 '+'）
     * ^
     * 第 3 步："words and 987"（由于当前字符 'w' 不是一个数字，所以读入停止）
     * ^
     * 解析得到整数 0 ，因为没有读入任何数字。
     * 由于 0 在范围 [-231, 231 - 1] 内，最终结果为 0 。
     * 示例 5：
     * <p>
     * 输入：s = "-91283472332"
     * 输出：-2147483648
     * 解释：
     * 第 1 步："-91283472332"（当前没有读入字符，因为没有前导空格）
     * ^
     * 第 2 步："-91283472332"（读入 '-' 字符，所以结果应该是负数）
     * ^
     * 第 3 步："-91283472332"（读入 "91283472332"）
     * ^
     * 解析得到整数 -91283472332 。
     * 由于 -91283472332 小于范围 [-231, 231 - 1] 的下界，最终结果被截断为 -231 = -2147483648 。
     * <p>
     * 作者：力扣 (LeetCode)
     * 链接：https://leetcode-cn.com/leetbook/read/top-interview-questions-easy/xnoilh/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */
    public int myCharToInt(String s) {
//        char[] chars = s.trim().toCharArray();
//        if (chars.length == 0)
//            return 0;
//        if ((chars[0] == '-' || chars[0] == '+') && chars.length == 1)
//            return 0;
//        int sum = 0;
//        // 1.判断首位是否为正负号
//        char[] digit = new char[chars.length];
//        int i = chars[0] == '-' || chars[0] == '+' ? 1 : 0;
//        int j = 0;
//        // 找到首个字符
//        if (!Character.isDigit(chars[i]) && chars[i] != '-')
//            return 0;
//
//        // 取数
//        while (Character.isDigit(chars[i])) {
//            // 去掉前缀0
//            digit[j++] = chars[i++];
//            if (i == chars.length)
//                break;
//        }
//        // 计算
//        if (j == 0) {
//            return 0;
//        }
//        int k = 0;
//        while (k < j) {
//            if (j == k && sum + 7 >= Integer.MAX_VALUE) {
//                sum = Integer.MAX_VALUE;
//                return chars[0] == '-' ? -sum - 1 : sum;
//            } else if (j == k) {
//                sum += digit[k++] - '0';
//                return sum;
//            } else {
//                sum += digit[k++] - '0';
//            }
//            if (sum >= Integer.MAX_VALUE / 10 && j > k || sum >= Integer.MAX_VALUE / 10) {
//
//                if (digit[k] - '0' > 7 || j - k > 1) {
//                    sum = Integer.MAX_VALUE;
//                    return chars[0] == '-' ? -sum - 1 : sum;
//                } else if (j - k == 1) {
//                    sum *= 10;
//                    sum += digit[k++] - '0';
//                    return chars[0] == '-' ? -sum  : sum;
//                }
//            }
//            if (k != j) {
//                sum *= 10;
//            } else {
//                break;
//            }
//        }
//        return chars[0] == '-' ? -sum : sum;
        char[] chars = s.toCharArray();
        int length = chars.length;
        int index = 0;
        // 先去除空格
        while (index < length && chars[index] == ' ') {
            index++;
        }
        // 极端情况 "  " 和""
        if (index >= length) {
            return 0;
        }
        // 再判断符号
        int sign = 1;
        if (chars[index] == '-' || chars[index] == '+') {
            if (chars[index] == '-') {
                sign = -1;
            }
            index++;
        }
        int result = 0;
        int temp = 0;
        while (index < length) {
            int num = chars[index] - '0';
            if (num > 9 || num < 0) {
                break;
            }
            temp = result;
            result = result * 10 + num;
            // 越界后，数值和期望数值发生变化，取余再除10获取原始值，比对判断
            if (result / 10 != temp) {
                if (sign > 0) {
                    return Integer.MAX_VALUE;
                } else {
                    return Integer.MIN_VALUE;
                }
            }
            index++;
        }
        return result * sign;
    }

    /**
     * 实现 strStr() 函数。
     *
     * @param haystack
     * @param needle
     * @return 给你两个字符串 haystack 和 needle ，请你在 haystack 字符串中找出 needle 字符串出现的第一个位置（下标从 0 开始）。如果不存在，则返回  -1 。
     * <p>
     * 作者：力扣 (LeetCode)
     * 链接：https://leetcode-cn.com/leetbook/read/top-interview-questions-easy/xnr003/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * 当 needle 是空字符串时，我们应当返回什么值呢？这是一个在面试中很好的问题。
     * <p>
     * 对于本题而言，当 needle 是空字符串时我们应当返回 0 。这与 C 语言的 strstr() 以及 Java 的 indexOf() 定义相符。
     * <p>
     *  
     * <p>
     * 示例 1：
     * <p>
     * 输入：haystack = "hello", needle = "ll"
     * 输出：2
     * 示例 2：
     * <p>
     * 输入：haystack = "aaaaa", needle = "bba"
     * 输出：-1
     * 示例 3：
     * <p>
     * 输入：haystack = "", needle = ""
     * 输出：0
     */
//    public int strStr(String haystack, String needle) {
//        if (needle == "")
//            return 0;
//        if (needle.length()>haystack.length())
//            return -1;
//
//
//    }

    @Test
    public void testDemo() {
        System.out.println(new BigDecimal("2131312321312312312312abc").toPlainString());
    }

    @Test
    public void testFirstUniqChar() {
        int abc = firstUniqChar("abcbc");
        System.out.println(abc);
    }

    @Test
    public void testisAnagram() {
        System.out.println(isAnagram("aacc", "ccac"));
    }

    @Test
    public void testisPalindrome() {
        System.out.println(isPalindrome("A man, a plan, a canal: Panama"));
    }

    @Test
    public void testMyCharToInt() {
//        System.out.println(myCharToInt("-91283472332"));
//        System.out.println(myCharToInt(" 1192820738r2"));
//        System.out.println(myCharToInt("2147483646"));
        System.out.println(myCharToInt("2147483646"));


    }

    @Test
    public void testUnion() {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        List<Integer> list1 = new ArrayList<>();
        list1.add(1);
        list1.add(2);
        list1.add(3);
        Collection union = CollectionUtils.union(list, list1);
        list.contains(list1);
        System.out.println(union);
    }


}
