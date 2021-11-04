package com.zcc.algorithm.primary_algorithm;

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
    public int strStr(String haystack, String needle) {
        /**
         * 自己写的暴力算法
         */
//        if (needle.length()==0){
//            return 0;
//        }
//
//        if (needle.length() > haystack.length()) {
//            return -1;
//        }
//        char[] chars = haystack.toCharArray();
//        char[] chars1 = needle.toCharArray();
//        int res = -1;
//        for (int i = 0; i < haystack.length(); i++) {
//            int k = 0;
//            if (chars[i] == chars1[0] && haystack.length() - i >= needle.length()) {
//                res = i;
//                while (k != chars1.length) {
//                    if (chars[res++] != chars1[k++]) {
//                        break;
//                    }
//                    if (k-1 == chars1.length - 1) {
//                        return i;
//                    }
//                }
//            }
//        }
        return -1;

    }

    /**
     * KMP算法
     */
    public int KMPStrStr(String haystack, String needle) {
        if (needle.length() == 0)
            return 0;
        int i = 0;
        int j = 0;
        /**
         * 数组next表示pattern指定的下标前具有相同的字符串数量，语言组织能力不好，可能不是太好理解，我举个例子吧
         * ，比如ABCABA，数组next[0]是-1，这个是固定的，因为第一个A前面是没有字符的，next[1]是0，因为B的前面就一个A，没有
         * 重复的，所以是0,同理next[2]也是,next[3]也是0,而next[4]是1，因为next[4]所指向的是第二个B，第二个B前面有一个A和
         * 第一个A相同，所以是1,next[5]是2，因为next[5]所指向的是最后一个Ａ，因为前面的Ａ对比成功，并且Ｂ也对比成功，所以是２，
         * 也就是ＡＢ两个字符串匹配成功,再举个例子，比如WABCABA，数组除了第一个为-1，其他的都是为0，因为只有第一个匹配成功之后
         * 才能匹配后面的，虽然后面的AB和前面的AB匹配成功，但是后面AB的前面是C和前面AB的前面一个W不匹配，所以后面的匹配都是0.
         * 要记住只有指定字符前面的字符和第一个字符匹配成功的时候才能往后匹配，否则后面的永远都是先和第一个匹配。
         */
        int[] next = new int[needle.length()];
        getNext(needle, next);
        while (i < haystack.length() && j < needle.length()) {
            /**
             * 这里j等于-1的时候也只有在下面next数组赋值的时候才会出现，并且只有在数组next[0]的时候才会等于-1，
             其他时候是没有的，这一点要谨记，待会下面求next数组的时候就会用到。这里可以这样来理解，如果j不等于-1，
             并且下标i和j所指向的字符相等，那么i和j分别往后移一位继续比较，这个很好理解，那么如果j==-1的时候，就表示
             就表示前面没有匹配成功的，同时i往后移一位，j置为0（j==-1的时候，j++为0），再从0开始比较。
             */
            if (j == -1 || haystack.charAt(i) == needle.charAt(j)) {
                i++;
                j++;
            } else {
                /**
                 * i = i - j + 1;
                 j = 0;
                 返回到指定的位置，不是返回到匹配失败的下一个位置，这里都好理解，重点是求数组next。
                 这里只要j等于0，在next[j]赋值的之后，j就会等于-1；因为next[0]等于-1
                 */
                j = next[j]; // j回到指定位置
            }
            if (j == needle.length())
                return i - j;
        }
        return -1;
    }

    private void getNext(String p, int next[]) {
        int len = p.length();
        int i = 0;
        int j = -1;
        next[0] = -1;//这个默认的，
        /**
         * 匹配的时候是当前字符的前一个和前面的匹配，所以最后一个是不参与匹配的，可以看strStr方法的注释，
         */
        while (i < len - 1) {
            if (j == -1 || p.charAt(i) == p.charAt(j)) {
                /**
                 * 如果j不等于-1，指定的字符相等，那么i和j要往后移一位，这点很好理解，如果j为-1的时候，i往后移移位，j置为0
                 * 重新开始匹配。next[i]是匹配成功的数量
                 */
                i++;
                j++;
                next[i] = j;
            } else
            /**
             * 关键是这里不好理解，为什么匹配失败要让next[j]等于j，要记住这里的next[j]是指匹配成功的数量，有可能为0，也有可能是其他数.比如
             * 字符串ABCABXYABCABATDM,对应的next数组为{-1	0	0	0	1	2	0	0	1	2	3	4	5	1	0	0	}
             */
                j = next[j];
        }
    }

    @Test
    public void testStrStr() {
        System.out.println(strStr("mississippi",
                "issip"));
    }

    /**
     * 给定一个正整数 n ，输出外观数列的第 n 项。
     * <p>
     * 「外观数列」是一个整数序列，从数字 1 开始，序列中的每一项都是对前一项的描述。
     * <p>
     * 你可以将其视作是由递归公式定义的数字字符串序列：
     * <p>
     * countAndSay(1) = "1"
     * countAndSay(n) 是对 countAndSay(n-1) 的描述，然后转换成另一个数字字符串。
     * 前五项如下：
     * <p>
     * 1.     1
     * 2.     11
     * 3.     21
     * 4.     1211
     * 5.     111221
     * 第一项是数字 1
     * 描述前一项，这个数是 1 即 “ 一 个 1 ”，记作 "11"
     * 描述前一项，这个数是 11 即 “ 二 个 1 ” ，记作 "21"
     * 描述前一项，这个数是 21 即 “ 一 个 2 + 一 个 1 ” ，记作 "1211"
     * 描述前一项，这个数是 1211 即 “ 一 个 1 + 一 个 2 + 二 个 1 ” ，记作 "111221"
     * 要 描述 一个数字字符串，首先要将字符串分割为 最小 数量的组，每个组都由连续的最多 相同字符 组成。然后对于每个组，先描述字符的数量，然后描述字符，形成一个描述组。要将描述转换为数字字符串，先将每组中的字符数量用数字替换，再将所有描述组连接起来。
     * <p>
     * 作者：力扣 (LeetCode)
     * 链接：https://leetcode-cn.com/leetbook/read/top-interview-questions-easy/xnpvdm/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     *
     * @param n
     * @return
     */

//        public String countAndSay(int n) {
//            // 递归出口
//            if (n == 1) {
//                return "1";
//            }
//            // 假设我们获得上一次的结果为 s1 = 112213
//            String s1 = countAndSay(n - 1);
//            // 定义结果
//            StringBuilder result = new StringBuilder();
//            // 对s1遍历处理获取值
//            char local = s1.charAt(0);
//            int count = 0;
//            for (int i = 0; i < s1.length(); i++) {
//                // 设定计数器 计算同一个数字出现的次数 count
//                if (s1.charAt(i) == local) {
//                    count++;
//                } else {
//                    // 不符合，记录下
//                    result.append(count);
//                    result.append(local);
//                    count = 1;
//                    local = s1.charAt(i);
//                }
//            }
//            result.append(count);
//            result.append(local);
//            return result.toString();
//        }
    public String countAndSay(int n) {
        StringBuilder builder = new StringBuilder();
        if (n == 1) {
            return "1";
        }
        String s = countAndSay(n - 1);
        int count = 0;
        char local = s.charAt(0);
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) != local) {
                builder.append(count + "" + local);
                local = s.charAt(i);
                count = 1;
            } else {
                count++;
            }
        }
        builder.append(count + "" + local);
        return builder.toString();
    }

    public String countAndSayTop(int n) {
        StringBuilder res = new StringBuilder("1");
        StringBuilder prev;
        int count;
        char say;
        for (int i = 1; i < n; i++) {
            prev = res;
            res = new StringBuilder();
            count = 1;
            //遇到新的字符
            say = prev.charAt(0);
            for (int j = 1, len = prev.length(); j < len; j++) {
                if (prev.charAt(j) != say) {
                    //如果遇到了新的字符，把它的数量和字符都加入到res中，
                    //然后count置为1，再把say赋予遇到的新的字符
                    res.append(count).append(say);
                    count = 1;
                    say = prev.charAt(j);
                } else {
                    //如果不是新的字符，计算当前字符的数量
                    count++;
                }
            }
            res.append(count).append(say);
        }
        return res.toString();
    }


    @Test
    public void testCountAndSay() {
        System.out.println(countAndSay(4));
    }

    @Test
    public void testCountAndSayTop() {
        System.out.println(countAndSayTop(4));
    }

    /**
     * 最长公共前缀
     * 编写一个函数来查找字符串数组中的最长公共前缀。
     * <p>
     * 如果不存在公共前缀，返回空字符串 ""。
     * <p>
     *  
     * <p>
     * 示例 1：
     * <p>
     * 输入：strs = ["flower","flow","flight"]
     * 输出："fl"
     * 示例 2：
     * <p>
     * 输入：strs = ["dog","racecar","car"]
     * 输出：""
     * 解释：输入不存在公共前缀。
     *  
     * <p>
     * 提示：
     * <p>
     * 1 <= strs.length <= 200
     * 0 <= strs[i].length <= 200
     * strs[i] 仅由小写英文字母组成
     * <p>
     * 作者：力扣 (LeetCode)
     * 链接：https://leetcode-cn.com/leetbook/read/top-interview-questions-easy/xnmav1/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     *
     * @param strs
     * @return
     */
    public String longestCommonPrefix(String[] strs) {
        /**
         * 自己写的效率相当低
         */
//        int i = 0;
//        String local = strs[0];
//        List<String> strings = Arrays.asList(strs);
//        if (strings.contains("")){
//            return "";
//        }
//        String s = strings.stream().min((o1, o2) -> Integer.compare(o1.length(), o2.length())).get();
//        int count = s.length();
//        while (i < strs.length) {
//           int  k = 0;
//            for (int j = 0; j < s.length(); j++) {
//                if (strs[i].charAt(j) == local.charAt(j)) {
//                    k++;
//                    continue;
//                }
//                if (k == 0) {
//                  return "";
//                }
//                if (count>k){
//                    count = k;
//                }
//                break;
//            }
//            i++;
//        }
//        return local.substring(0,count);
        /**
         * 优化
         */
        int i = 1;
        String local = strs[0];
        int count = local.length();
        if (count == 0) return "";
        while (i < strs.length) {
            int j = 0;
            int k = 0;
            if (strs[i].length() == 0) return "";
            while (j < strs[i].length() && j < local.length()) {
                if (local.charAt(j) == strs[i].charAt(j++)) {
                    k++;
                    continue;
                }
                break;
            }
            if (count > k) { // 重复个数小于count就赋值
                if (k == 0) return "";
                count = k;
            }
            i++;
        }
        return local.substring(0, count);
        /**
         * 效率非常高
         */
//            //边界条件判断
//            if (strs == null || strs.length == 0)
//                return "";
//            //默认第一个字符串是他们的公共前缀
//            String pre = strs[0];
//            int i = 1;
//            while (i < strs.length) {
//                //不断的截取
//                while (strs[i].indexOf(pre) != 0)
//                    pre = pre.substring(0, pre.length() - 1);
//                i++;
//            }
//            return pre;

//        作者：数据结构和算法
//        链接：https://leetcode-cn.com/leetbook/read/top-interview-questions-easy/xnmav1/?discussion=zYLoja
//        来源：力扣（LeetCode）
//        著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
        // 纵向扫描
        /**
         *
         */
//        if(strs == null || strs.length ==0){
//            return "";
//        }
//        // 以第一个字符串为基准，进行纵向扫描
//        int length = strs[0].length();
//        // 纵向深度
//        int count = strs.length;
//        for (int i = 0; i < length; i++) {
//            char c =  strs[0].charAt(i);
//            // 以第一个字符串第i个进行向下扫描、
//            for (int j = 1; j < count; j++) {
//                //如果其他字符串长度不够，或者不为c，返回第一个数组的部分截取，如第i个不满足条件，就取[0，i-1]即substring(0,i)
//                if(strs[j].length() <= i || strs[j].charAt(i) != c){
//                    return strs[0].substring(0,i);
//                }
//            }
//        }
//        // 最大不超过第一个字符串
//        return strs[0];

//        作者：桂继宏
//        链接：https://leetcode-cn.com/leetbook/read/top-interview-questions-easy/xnmav1/?discussion=vGvcNu
//        来源：力扣（LeetCode）
//        著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
    }

    @Test
    public void testLongestCommonPrefix() {
        String[] strings = {"flower", "flow", "flight"};
        String[] strings1 = {"dog", "racecar", "car"};
        String[] strings2 = {"acc", "aaa", "abaaba"};
        String[] strings3 = {"a", "ac"};
        String[] strings4 = {"", "b"};

        System.out.println(longestCommonPrefix(strings2));
    }

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
