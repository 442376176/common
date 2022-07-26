package com.zcc.utils;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

/**
 * @author zcc
 * @version 1.0
 * @date 2021/9/26 15:49
 */
public class MathUtil {


    /**
     * 八种舍入模式解释如下
     * 1、ROUND_UP
     *
     * 舍入远离零的舍入模式。
     *
     * 在丢弃非零部分之前始终增加数字(始终对非零舍弃部分前面的数字加1)。
     *
     * 注意，此舍入模式始终不会减少计算值的大小。
     *
     * 2、ROUND_DOWN
     *
     * 接近零的舍入模式。
     *
     * 在丢弃某部分之前始终不增加数字(从不对舍弃部分前面的数字加1，即截短)。
     *
     * 注意，此舍入模式始终不会增加计算值的大小。
     *
     * 3、ROUND_CEILING
     *
     * 接近正无穷大的舍入模式。
     *
     * 如果 BigDecimal 为正，则舍入行为与 ROUND_UP 相同;
     *
     * 如果为负，则舍入行为与 ROUND_DOWN 相同。
     *
     * 注意，此舍入模式始终不会减少计算值。
     *
     * 4、ROUND_FLOOR
     *
     * 接近负无穷大的舍入模式。
     *
     * 如果 BigDecimal 为正，则舍入行为与 ROUND_DOWN 相同;
     *
     * 如果为负，则舍入行为与 ROUND_UP 相同。
     *
     * 注意，此舍入模式始终不会增加计算值。
     *
     * 5、ROUND_HALF_UP
     *
     * 向“最接近的”数字舍入，如果与两个相邻数字的距离相等，则为向上舍入的舍入模式。
     *
     * 如果舍弃部分 >= 0.5，则舍入行为与 ROUND_UP 相同;否则舍入行为与 ROUND_DOWN 相同。
     *
     * 注意，这是我们大多数人在小学时就学过的舍入模式(四舍五入)。
     *
     * 6、ROUND_HALF_DOWN
     *
     * 向“最接近的”数字舍入，如果与两个相邻数字的距离相等，则为上舍入的舍入模式。
     *
     * 如果舍弃部分 > 0.5，则舍入行为与 ROUND_UP 相同;否则舍入行为与 ROUND_DOWN 相同(五舍六入)。
     *
     * 7、ROUND_HALF_EVEN
     *
     * 向“最接近的”数字舍入，如果与两个相邻数字的距离相等，则向相邻的偶数舍入。
     *
     * 如果舍弃部分左边的数字为奇数，则舍入行为与 ROUND_HALF_UP 相同;
     *
     * 如果为偶数，则舍入行为与 ROUND_HALF_DOWN 相同。
     *
     * 注意，在重复进行一系列计算时，此舍入模式可以将累加错误减到最小。
     *
     * 此舍入模式也称为“银行家舍入法”，主要在美国使用。四舍六入，五分两种情况。
     *
     * 如果前一位为奇数，则入位，否则舍去。
     *
     * 以下例子为保留小数点1位，那么这种舍入方式下的结果。
     *
     * 1.15>1.2 1.25>1.2
     *
     * 8、ROUND_UNNECESSARY
     *
     * 断言请求的操作具有精确的结果，因此不需要舍入。
     *
     * 如果对获得精确结果的操作指定此舍入模式，则抛出ArithmeticException。
     *
     */



    /**
     * 标准差σ=sqrt(s^2)
     * 结果精度：scale
     * 牛顿迭代法求大数开方
     *
     * @param x
     * @param scale
     * @return
     */
    public static BigDecimal sqrt(BigDecimal x, int scale) {

        BigDecimal base2 = BigDecimal.valueOf(2.0);
        //计算精度
        int precision = 100;
        MathContext mc = new MathContext(precision, RoundingMode.HALF_UP);
        BigDecimal deviation = x;
        int cnt = 0;
        while (cnt < 100) {
            deviation = (deviation.add(x.divide(deviation, mc))).divide(base2, mc);
            cnt++;
        }
        deviation = deviation.setScale(scale, BigDecimal.ROUND_HALF_UP);
        return deviation;
    }

    public static BigDecimal add(BigDecimal x, BigDecimal y,int scale) {
        return x.add(y).setScale(scale, BigDecimal.ROUND_HALF_UP);
    }

    public static BigDecimal reduce(BigDecimal x, BigDecimal y,int scale){
        return x.subtract(y).setScale(scale, BigDecimal.ROUND_HALF_UP);
    }

    public static BigDecimal multiply(BigDecimal x, BigDecimal y,int scale){
        return x.multiply(y).setScale(scale, BigDecimal.ROUND_HALF_UP);
    }

    public static BigDecimal divide(BigDecimal x, BigDecimal y,int scale){
        return x.divide(y).setScale(scale, BigDecimal.ROUND_HALF_UP);
    }

    public static BigDecimal abs(BigDecimal x, BigDecimal y,int scale){
        int precision = 100;
        MathContext mc = new MathContext(precision, RoundingMode.HALF_UP);
        return x.abs(mc).setScale(scale, BigDecimal.ROUND_HALF_UP);
    }


    public static void main(String[] args) {
        BigDecimal sqrt = sqrt(new BigDecimal(100000000), 0);
        System.out.println(sqrt.toPlainString());
    }
}