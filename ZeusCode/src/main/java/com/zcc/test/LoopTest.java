package com.zcc.test;

/**
 * @ProjectName: ZeusCode
 * @ClassName: com.zcc.test
 * @author: zcc
 * @date: 2022/7/26 14:19
 * @version:
 * @Describe:
 */
public class LoopTest {

    public static void main(String[] args) {
        long l = System.currentTimeMillis();
        int i = 0, j = 0;
        for (; i < 10; i++) {
            for (; j < 100000000; j++) {

            }
        }
        System.out.println("外小内大" + (System.currentTimeMillis() - l));
        long l1 = System.currentTimeMillis();
        int m = 0, n = 0;
        for (; m < 100000000; m++) {
            for (; n < 10; n++) {
            }
        }
        System.out.println("外大内小" + (System.currentTimeMillis() - l1));
    }

    static class Test {

        public static void main(String[] args) {

            int small = 10;
            int middle = 1000;
            int large = 1000000;
            // 大循环在外面，小循环在里面，变量每次生成
            long t = System.currentTimeMillis();
            for (int i = 1; i <= large; i++) {
                for (int j = 1; j <= middle; j++) {
                    for (int k = 1; k <= small; k++) {
                    }
                }
            }
            System.out.println(System.currentTimeMillis() - t);
            // 大循环在外面，小循环在里面，变量统一生成
            t = System.currentTimeMillis();
            int i, j, k;
            for (i = 1; i <= large; i++) {
                for (j = 1; j <= middle; j++) {
                    for (k = 1; k <= small; k++) {
                    }
                }
            }
            System.out.println(System.currentTimeMillis() - t);
            // 小循环在外面，大循环在里面，变量每次生成
            t = System.currentTimeMillis();
            for (int kk = 1; kk <= small; kk++) {
                for (int jj = 1; jj <= middle; jj++) {
                    for (int ii = 1; ii <= large; ii++) {
                    }
                }
            }
            System.out.println(System.currentTimeMillis() - t);
            // 小循环在外面，大循环在里面，变量统一生成
            t = System.currentTimeMillis();
            int ii, jj, kk;
            for (kk = 1; kk <= small; kk++) {
                for (jj = 1; jj <= middle; jj++) {
                    for (ii = 1; ii <= large; ii++) {
                    }
                }
            }
            System.out.println(System.currentTimeMillis() - t);
        }
    }
}
