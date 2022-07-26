package com.zcc.enumTest;


public enum WeekDay {
    SUN(0),MON(1),TUS(2),WED(3),THU(4),FRI(5),SAT(6);

    private int index;

    private WeekDay(int index){
        this.index = index;
    }

    public static WeekDay getNextDay(WeekDay nowDay){
        int nextDayValue = nowDay.index;

        if (++nextDayValue == 7){
            nextDayValue =0;
        }

        return getWeekdayByValue(nextDayValue);
    }

    public static WeekDay getWeekdayByValue(int index) {
        for (WeekDay c : WeekDay.values()) {
            if (c.index == index) {
                return c;
            }
        }
        return null;
    }
}

class Test2{
    public static void main(String[] args) {
        System.out.println("nowday ====> " + WeekDay.SAT);
        // ordinal()  返回枚举类常量的序号
        System.out.println("nowday int ====> " + WeekDay.SAT.ordinal());
        System.out.println("nextday ====> " + WeekDay.getNextDay(WeekDay.SAT)); // 输出 SUN
        System.out.println(WeekDay.getWeekdayByValue(2));

    }
}