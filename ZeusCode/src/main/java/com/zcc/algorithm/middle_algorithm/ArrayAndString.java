package com.zcc.algorithm.middle_algorithm;

import org.junit.Test;

import java.util.*;

/**
 * @ProjectName: ORCode
 * @ClassName: com.zcc.algorithm.middle_algorithm
 * @author: zcc
 * @date: 2022/3/18 9:12
 * @version:
 * @Describe:
 */
public class ArrayAndString {
    /**
     * 三数之和
     * 给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？请你找出所有和为 0 且不重复的三元组。
     * <p>
     * 注意：答案中不可以包含重复的三元组。
     * <p>
     *  
     * <p>
     * 示例 1：
     * <p>
     * 输入：nums = [-1,0,1,2,-1,-4]
     * 输出：[[-1,-1,2],[-1,0,1]]
     * 示例 2：
     * <p>
     * 输入：nums = []
     * 输出：[]
     * 示例 3：
     * <p>
     * 输入：nums = [0]
     * 输出：[]
     *  
     * <p>
     * 提示：
     * <p>
     * 0 <= nums.length <= 3000
     * -105 <= nums[i] <= 105
     * <p>
     * 作者：力扣 (LeetCode)
     * 链接：https://leetcode-cn.com/leetbook/read/top-interview-questions-medium/xvpj16/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> threeSum(int[] nums) {
        int length = nums.length;
        Arrays.sort(nums);
        if (length < 3) {
            return new ArrayList<>();
        }
        List<List<Integer>> res = new ArrayList<>();
        int i = 0;
        for (; i <= length - 3; i++) {
            //过滤掉重复的
            if (i > 0 && nums[i] == nums[i - 1])
                continue;
            int num = nums[i];
            for (int j = i + 1, n = length - 1; j < n; ) {
                int sum = nums[j] + nums[n];
                if (num + sum == 0) {
                    res.add(Arrays.asList(num, nums[j], nums[n]));
                    while (j < n && nums[j] == nums[j + 1]) {
                        j++;
                    }
                    while (j < n && nums[n] == nums[n - 1]) {
                        n--;
                    }
                    j++;
                    n--;
                } else if (sum + num > 0) {
                    n--;
                } else {
                    j++;
                }
            }

        }
        return res;
//        //先对数组进行排序
//        Arrays.sort(nums);
//        List<List<Integer>> res = new ArrayList<>();
//        for (int i = 0; i < nums.length - 2; i++) {
//            //过滤掉重复的
//            if (i > 0 && nums[i] == nums[i - 1])
//                continue;
//            //因为是排序的，如果第一个数字大于0，那么后面的也都
//            //大于0，他们三个数字的和不可能等于0
//            if (nums[i] > 0)
//                break;
//            int left = i + 1;//左指针
//            int right = nums.length - 1;//右指针
//            int target = -nums[i];
//            while (left < right) {
//                //左右指针的和
//                int sum = nums[left] + nums[right];
//                if (sum == target) {
//                    //找到了一组，把他们加入到集合list中
//                    res.add(Arrays.asList(nums[i], nums[left], nums[right]));
//                    //过滤掉重复的
//                    while (left < right && nums[left] == nums[left + 1])
//                        left++;
//                    while (left < right && nums[right] == nums[right - 1])
//                        right--;
//                    left++;
//                    right--;
//                } else if (sum < target) {
//                    left++;
//                } else {
//                    right--;
//                }
//            }
//        }
//        return res;

    }

    /**
     * 矩阵置零
     * 给定一个 m x n 的矩阵，如果一个元素为 0 ，则将其所在行和列的所有元素都设为 0 。请使用 原地 算法。
     * <p>
     *  
     * <p>
     * 示例 1：
     * <p>
     * <p>
     * 输入：matrix = [[1,1,1],[1,0,1],[1,1,1]]
     * 输出：[[1,0,1],[0,0,0],[1,0,1]]
     * 示例 2：
     * <p>
     * <p>
     * 输入：matrix = [[0,1,2,0],[3,4,5,2],[1,3,1,5]]
     * 输出：[[0,0,0,0],[0,4,5,0],[0,3,1,0]]
     *  
     * <p>
     * 提示：
     * <p>
     * m == matrix.length
     * n == matrix[0].length
     * 1 <= m, n <= 200
     * -231 <= matrix[i][j] <= 231 - 1
     *  
     * <p>
     * 进阶：
     * <p>
     * 一个直观的解决方案是使用  O(mn) 的额外空间，但这并不是一个好的解决方案。
     * 一个简单的改进方案是使用 O(m + n) 的额外空间，但这仍然不是最好的解决方案。
     * 你能想出一个仅使用常量空间的解决方案吗？
     * 相关标签
     * <p>
     * 作者：力扣 (LeetCode)
     * 链接：https://leetcode-cn.com/leetbook/read/top-interview-questions-medium/xvmy42/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */
    public void setZeroes(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        boolean[] row = new boolean[m];
        boolean[] col = new boolean[n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 0) {
                    row[i] = true;
                    col[j] = true;
                }
            }
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (row[i] || col[j]) {
                    matrix[i][j] = 0;
                }
            }
        }
    }

    /**
     * 字母异位词分组
     * 给你一个字符串数组，请你将 字母异位词 组合在一起。可以按任意顺序返回结果列表。
     * <p>
     * 字母异位词 是由重新排列源单词的字母得到的一个新单词，所有源单词中的字母通常恰好只用一次。
     * <p>
     *  
     * <p>
     * 示例 1:
     * <p>
     * 输入: strs = ["eat", "tea", "tan", "ate", "nat", "bat"]
     * 输出: [["bat"],["nat","tan"],["ate","eat","tea"]]
     * 示例 2:
     * <p>
     * 输入: strs = [""]
     * 输出: [[""]]
     * 示例 3:
     * <p>
     * 输入: strs = ["a"]
     * 输出: [["a"]]
     *  
     * <p>
     * 提示：
     * <p>
     * 1 <= strs.length <= 104
     * 0 <= strs[i].length <= 100
     * strs[i] 仅包含小写字母
     * <p>
     * 作者：力扣 (LeetCode)
     * 链接：https://leetcode-cn.com/leetbook/read/top-interview-questions-medium/xvaszc/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     *
     * @param strs
     * @return
     */
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> s = new HashMap<>();
        for (int i = 0; i < strs.length; i++) {
            String str = strs[i];
            char[] chars = str.toCharArray();
            Arrays.sort(chars);
            String s1 = String.valueOf(chars);
            if (s.containsKey(s1)) {
                s.get(s1).add(str);
            } else {
                s.put(s1, new ArrayList<String>() {{
                    add(str);
                }});
            }
        }
        return new ArrayList<>(s.values());

    }


    /**
     * 无重复字符的最长子串
     * 给定一个字符串 s ，请你找出其中不含有重复字符的 最长子串 的长度。
     * <p>
     *  
     * <p>
     * 示例 1:
     * <p>
     * 输入: s = "abcabcbb"
     * 输出: 3
     * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
     * 示例 2:
     * <p>
     * 输入: s = "bbbbb"
     * 输出: 1
     * 解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
     * 示例 3:
     * <p>
     * 输入: s = "pwwkew"
     * 输出: 3
     * 解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
     *      请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
     *  
     * <p>
     * 提示：
     * <p>
     * 0 <= s.length <= 5 * 104
     * s 由英文字母、数字、符号和空格组成
     * <p>
     * 作者：力扣 (LeetCode)
     * 链接：https://leetcode-cn.com/leetbook/read/top-interview-questions-medium/xv2kgi/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     *
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring(String s) throws Exception {
        int length = s.length(), max = 0;
        // 边界
        if (length == 0) {
            return 0;
        }
        char[] chars = s.toCharArray();
        HashMap<Character, Integer> map = new HashMap<>();
        for (int i = 0, j = 0; i < length; i++) {
            // 重复
            if (map.containsKey(chars[i])) {
                j = Math.max(j, map.get(chars[i]) + 1);
            }
            map.put(chars[i], i);
            max = Math.max(max, i - j + 1);
        }
        return max;
    }

    @Test
    public void testThreeSum() {
        int[] s = {-1, 0, 1, 2, -1, -4};
        int[] a = {0};
        List<List<Integer>> lists = threeSum(s);
        System.out.println(lists);
    }

    @Test
    public void testSetZero() {
        int[][] s = {{0, 1, 2, 0}, {3, 4, 5, 2}, {1, 3, 1, 5}};
        setZeroes(s);
        for (int i = 0; i < s.length; i++) {
            for (int j = 0; j < s[0].length; j++) {
                System.out.print(s[i][j]);
            }
            System.out.println();
        }
    }

    @Test
    public void testGroupAnagrams() {
        String[] s = {"eat", "tea", "tan", "ate", "nat", "bat"};
        List<List<String>> lists = groupAnagrams(s);
        System.out.println(lists);
    }

    @Test
    public void testLengthOfLongestSubstring() throws Exception {
        int q = lengthOfLongestSubstring("pwwkew");
        System.out.println(q);
    }


    /**
     * 最长回文子串
     * 给你一个字符串 s，找到 s 中最长的回文子串。
     * <p>
     *  
     * <p>
     * 示例 1：
     * <p>
     * 输入：s = "babad"
     * 输出："bab"
     * 解释："aba" 同样是符合题意的答案。
     * 示例 2：
     * <p>
     * 输入：s = "cbbd"
     * 输出："bb"
     *  
     * <p>
     * 提示：
     * <p>
     * 1 <= s.length <= 1000
     * s 仅由数字和英文字母组成
     * <p>
     * 作者：力扣 (LeetCode)
     * 链接：https://leetcode-cn.com/leetbook/read/top-interview-questions-medium/xvn3ke/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     *
     * @param s
     * @return
     */
    public String longestPalindrome(String s) {
        //1.暴力求解
        return null;
    }

    public boolean isPalindrome(String str, int a, int b) {
        char[] chars = str.toCharArray();
        while (a <= b) {
            if (chars[a++] != chars[b--]) {
                return false;
            }
        }
        return true;
    }

    public String splitAllStr(String str) {

        int length = str.length();
        int max = 0, start = 0;
        for (int i = 0; i < length - 1; i++) {
            for (int j = i; j < length; j++) {
                if (j - i < max) {
                    break;
                }
                if (isPalindrome(str, i, j)) {
                    if (max < i - j + 1) {
                        max = i - j + 1;
                        start = i;
                    }
                }
            }
        }
        return str.substring(start, start + max);
    }

    @Test
    public void testLongestPalindrome() {
        System.out.println(splitAllStr("cbbd"));
    }


        public boolean increasingTriplet(int[] nums) {
            if (nums.length < 3) {
                return false;
            }
            int min = Integer.MAX_VALUE;
            int secondMin = Integer.MAX_VALUE;
            for (int num : nums) {
                if (min >= num) {
                    min = num;
                } else if (secondMin >= num) {
                    secondMin = num;
                }else {
                    return true;
                }
            }
            return false;
        }

        @Test
        public void test() {
            int[] nums = {1,2,2,1};
            System.out.println(increasingTriplet(nums));
        }

}
