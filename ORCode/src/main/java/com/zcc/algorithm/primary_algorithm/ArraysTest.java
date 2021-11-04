package com.zcc.algorithm.primary_algorithm;

import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class ArraysTest {

    // 唯一数
    public static int singleNumber(int[] nums) {
        Arrays.sort(nums);
        int index = nums[0];
        for (int i = 1; i < nums.length - 1; i++) {
            if (index == nums[i]) {
                index = nums[++i];
                continue;
            }
            break;
        }
        return index;
    }

    // 旋转数组
    public static void rotate(int[] nums, int k) {
        k = k % nums.length;
//        int[] building = {2,1,30,47,13,48,78,80,73,85,89,90,3,10,92,93,15,14,17,18,19,20,21,25,26,27,28,29,31,40,41,42,44,45,46,49,71,16,84,91,94,95,23,39,38,55,62,61,70,69,83,12,22,32,33,34,35,36,37,43,50,51,52,53,54,56,57,58,59,60,63,64,65,66,67,68,72,77,81,82,88,96};
//        int[] bnun = {3,2,10,78,13,47,90,80,30,73,48,85,12,89,15,17,14,16,18,19,20,21,22,23,25,26,27,28,29,31,32,33,34,35,36,37,38,39,40,41,42,43,44,45,46,49,50,51,52,53,54,55,56,57,58,59,60,61,62,63,64,65,66,67,68,69,70,71,72,77,81,82,83,84,88,91,1,92,93,94,95,96}

        int[] rightpart = Arrays.copyOfRange(nums, nums.length - k, nums.length);
        System.arraycopy(nums, 0, nums, k, nums.length - k);
        System.arraycopy(rightpart, 0, nums, 0, k);
    }

    // 加一
    public static int[] plusOne(int[] digits) {
        int k = digits.length - 1;
        int[] reArray = null;
        while (true) {
            int s = digits[k] + 1;
            digits[k] = (digits[k] + 1) % 10;
            if (s >= 10) {
                k--;
            } else
                break;
            if (k + 1 == 0 && s >= 10) {
                reArray = new int[digits.length + 1];
                reArray[0] = 1;
                for (int i = 1; i <= digits.length; i++) {
                    reArray[i] = digits[i - 1];
                }
                break;
            }

        }
        return reArray == null ? digits : digits;
    }

    // 0后移
    public static void moveZeroes(int[] nums) {
        int i = 0;
        int j = 0;
        while (j < nums.length) {
            if (nums[j] == 0) {
                i++;
            } else {
                nums[j - i] = nums[j];
                nums[j] = 0;
            }
            j++;
        }
    }

    // 找出数组内相加等于目标值的两数下标
    public static int[] twoSum(int[] nums, int target) {

//        int [] arr = new int[nums.length];
//        int count = 0;
//        for(int i=0 ; i < nums.length ; i++){
//            for (int j = i+1; j < nums.length; j++) {
//                if (nums[i]+nums[j]==target){
//                    arr[count++] = i;
//                    arr[count++] = j;
//                    break;
//                }
//            }
//        }
//        int [] reArr = new int[count];
//        System.arraycopy(arr,0,reArr,0,count);
//        int i = 0; int j = 0;
//        while (true){
//
//
//        }

        Map<Integer, Integer> m = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (m.get(target - nums[i]) != null) {
                return new int[]{m.get(target - nums[i]), i};
            }
            m.put(nums[i], i);
        }
        return new int[]{0, 0};

    }

    // 数独判断
    public static boolean isValidSudoku(char[][] board) {
        int[] line = new int[9];
        int[] column = new int[9];
        int[] cell = new int[9];
        int shift = 0;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                //如果还没有填数字，直接跳过
                if (board[i][j] == '.')
                    continue;
                shift = 1 << (board[i][j] - '0');
                int k = (i / 3) * 3 + j / 3;
                //如果对应的位置只要有一个大于0，说明有冲突，直接返回false
                if ((column[i] & shift) > 0 || (line[j] & shift) > 0 || (cell[k] & shift) > 0)
                    return false;
                column[i] |= shift;
                line[j] |= shift;
                cell[k] |= shift;
            }
        }
        return true;
    }

    // n*n 选转90度
    public static void rotate(int[][] matrix) {
        int length = matrix.length;
        //因为是对称的，只需要计算循环前半行即可
        for (int i = 0; i < length / 2; i++)
            for (int j = i; j < length - i - 1; j++) {
                int temp = matrix[i][j];
                int m = length - j - 1;
                int n = length - i - 1;
                matrix[i][j] = matrix[m][i];
                matrix[m][i] = matrix[n][m];
                matrix[n][m] = matrix[j][n];
                matrix[j][n] = temp;
            }
    }

    /**
     * @param s
     * @deprecated 编写一个函数，其作用是将输入的字符串反转过来。输入字符串以字符数组 char[] 的形式给出。
     * <p>
     * 不要给另外的数组分配额外的空间，你必须原地修改输入数组、使用 O(1) 的额外空间解决这一问题。
     * <p>
     * 你可以假设数组中的所有字符都是 ASCII 码表中的可打印字符。
     * <p>
     * 作者：力扣 (LeetCode)
     * 链接：https://leetcode-cn.com/leetbook/read/top-interview-questions-easy/xnhbqj/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */
    public static void reverseString(char[] s) {
        int i = 0;
        int j = s.length - 1;
        char temp;
        while (i < j) {
            temp = s[i];
            s[i++] = s[j];
            s[j--] = temp;
        }
    }

    public static int reverse(int x) {
        int res = 0;
        int abs = Math.abs(x);
//        double limit = Math.pow(2,31)/10;
        int limit = Integer.MAX_VALUE/10;
        while (abs > 0) {
            res*=10;
            res +=  abs % 10;
            abs /= 10;
            if (abs>0 &&res>limit || (abs>7&&res==limit)) {
                res = 0;
                break;
            }
        }
        if (x<0){
            return -res;
        }
        return res;
    }

    public static void main(String[] args) throws FileNotFoundException {
//        char[][] board = {
//                {'8', '3', '.', '.', '7', '.', '.', '.', '.'}
//                , {'6', '.', '.', '1', '9', '5', '.', '.', '.'}
//                , {'.', '9', '8', '.', '.', '.', '.', '6', '.'}
//                , {'8', '.', '.', '.', '6', '.', '.', '.', '3'}
//                , {'4', '.', '.', '8', '.', '3', '.', '.', '1'}
//                , {'7', '.', '.', '.', '2', '.', '.', '.', '6'}
//                , {'.', '6', '.', '.', '.', '.', '2', '8', '.'}
//                , {'.', '.', '.', '4', '1', '9', '.', '.', '5'}
//                , {'.', '.', '.', '.', '8', '.', '.', '7', '9'}
//        };
//        isValidSudoku(board);
        char[] s = {'h', 'e', 'l', 'l', 'o'};
        reverseString(s);
        int reverse = reverse(-2147483412);
//        System.out.println(String.valueOf(964632435-214748364));
        System.out.println(reverse);
//        int[][] matrix = {{5,1,9,11},{2,4,8,10},{13,3,6,7},{15,14,12,16}};
//        rotate(matrix);
//        for (int i = 0; i < 4 ; i++) {
//            System.out.println(matrix[i][0]+","+matrix[i][1]+","+matrix[i][2]+","+matrix[i][3]);
//        }
//        File file;
//        OutputStream outputStream = new FileOutputStream("");

//        System.out.println(new ArrayList<String>() {{
//            add("123");
//            add("zcc");
//        }});
//        int x = 9;
//        System.out.println(1 << 9);
//            int[] x = {7,2,8,5,0,9,1,2,9,5,3,6,6,7,3,2,8,4,3,7,9,5,7,7,4,7,4,9,4,7,0,1,1,1,7,4,0,0,6};
//        int[] arr = {2,7,11,15};
//        moveZeroes(arr);
//        Map<Integer, Integer> m = new HashMap<>();
//        Integer integer = m.get(0);
//        System.out.println(m.size());
//        int[] x = {9};
//        int[] ints = plusOne(x);
//        int[] ints = twoSum(arr, 9);
//        for (int i = 0; i < ints.length; i++) {
////            System.out.println(m.get(i));
//            System.out.println(ints[i]);
//        }
//        System.out.println(x/10);
//        char myChar = 'g';
//        String myStr = Character.toString(myChar);
//        System.out.println('String is: '+myStr);
//        myStr = String.valueOf(myChar);
//        System.out.println('String is: '+myStr);
//                int[] arr = {4,1,2,1,2};
////           String[] demo = {'123','2','345','4','5','6','7'};
//           Arrays.sort(arr);
//        int i = singleNumber(arr);
//        System.out.println(i);
//        rotate(demo,3);
//        for (int i = 0; i < demo.length; i++) {
//            System.out.println(demo[i]);
//            HashSet<Integer> set = new HashSet();
//            set
//        }
//        List<User> list = new ArrayList<>();
//        for (int i = 0; i < 20; i++) {
//            list.add(new User(i, 'zcc', '123'));
//        }
//        arrayList.retainAll(collect)
//        ArrayList<Long> list = new ArrayList<>();
//        ArrayList<Long> list1 = new ArrayList<>();
//        list1.add(1L);
//        list1.add(2L);
//        list1.add(3L);
//        list.add(2L);
//        list.add(4L);
//        boolean b = list.retainAll(list1);
//        System.out.println(b);
//        System.out.println(list);
//        Map<String, List<User>> collect = list.stream().collect(Collectors.groupingBy(item -> {
//            if (item.getId() - 15>0)
//                return '15以上';
//            if (item.getId() - 10>0)
//                return '10以上,15以下';
//            if (item.getId() - 5>0)
//                return '5以上,10以下';
//            return '5以下';
//        }));
//        Iterator<Map.Entry<String, List<User>>> iterator = collect.entrySet().iterator();
//        while (iterator.hasNext()){
//            System.out.println(iterator.next());
//        }\
//        list.sort(((o1, o2) -> {
//            return  Double.parseDouble(o2.getId()+'') - Double.parseDouble(o1.getId()+'') > 0 ? 1
//                    : Double.parseDouble(o2.getId()+'') - Double.parseDouble(o1.getId()+'') == 0 ? 0: -1;
//
//        }));
//        System.out.println(list);
    }
}
