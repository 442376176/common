package com.zcc.test;

import org.junit.Test;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.Period;

/**
 * @author zcc
 * @version 1.0
 * @date 2022/1/7 9:06
 */
public class TimeTest {


    @Test
    public void a() {
        String format = "%s差为：--------";
//        String text = "2022-01-07";
//        Period t1 = Period.parse(text);
        LocalDate e = LocalDate.now();
        LocalDate s = LocalDate.parse("2021-01-06");

        int dayOfMonth = e.getDayOfMonth();
        Period between = Period.between(s, e);
        System.out.println(String.format(format,"天") + between.getDays());
        System.out.println(String.format(format,"月") + between.getMonths());
        System.out.println(String.format(format,"年") + between.getYears());
        Instant start = Instant.parse("2021-01-01T00:00:00.00Z");
        Instant end = Instant.now();
        Duration between1 = Duration.between(start, end);
        System.out.println(between1.toDays());


    }




}
