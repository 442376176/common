package com.zcc.algorithm.huawei;

import java.util.BitSet;

/**
 * @ProjectName: ORCode
 * @ClassName: com.zcc.algorithm.huawei
 * @author: zcc
 * @date: 2022/4/2 13:04
 * @version:
 * @Describe:
 */
class SodaQuestion {

    static int[] solve(int[] nums) {
        int[] count = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            count[i] = nums[i] / 3;
            int temp = nums[i] % 3 + count[i];
            while (temp > 2) {
                count[i] += temp / 3;
                temp = temp/3+temp%3;
            }
            if (temp==2){
                count[i]++;
            }
        }
        return count;
    }



    public static void main(String[] args) {
        int[] nums = {3, 10, 81, 0};
        for (int i : solve(nums)) {
            System.out.println(i);
        }

    }

}
class DuplicateRemoval {

    static BitSet solve(int[] nums) {
        BitSet bitSet = new BitSet();
        for (int i = 0; i < nums.length; i++) {
            bitSet.set(nums[i]);
        }
        return bitSet;
    }



    public static void main(String[] args) {
        int[] nums = {1,2,2};
        BitSet bitSet = solve(nums);
        for (int i = bitSet.nextSetBit(0);i!=-1;i = bitSet.nextSetBit(i+1)){
            System.out.println(i);
        }

    }

}

class BinaryConversion{
    static int solve(String hexStr) {
        if (hexStr.contains("x")){
            hexStr = hexStr.substring(hexStr.indexOf("x")+1,hexStr.length());
        }
        char[] chars = hexStr.toCharArray();
        double decimal =  0;
        for (int i = 0; i < chars.length; i++) {
            if (chars[i]>'0' && chars[i]<'9'){
                decimal += Math.pow(16, chars.length - i - 1) * (chars[i] - '0');
                continue;
            }
            decimal += Math.pow(16, chars.length - i - 1) * convert(chars[i]);
        }
        return (int)decimal;
    }

    static int convert(char c){
        switch (Character.toLowerCase(c)){
            case 'a':return 10;
            case 'b':return 11;
            case 'c':return 12;
            case 'd':return 13;
            case 'e':return 14;
            case 'f':return 15;
        }
        return 0;
    }



    public static void main(String[] args) {
        System.out.println(solve("0xAA"));
    }
}
