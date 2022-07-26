package com.zcc.test;

import com.alibaba.fastjson.JSONObject;
import com.zcc.utils.DateUtil;

import java.text.ParseException;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.function.BiConsumer;
import java.util.function.Function;

/**
 * @author zcc
 * @version 1.0
 * @date 2021/9/22 13:16
 */
public class CommonTest {


    static class A{
        public static void main(String[] args) {
            String s = "1,2,3";
            List<Integer> objects = new ArrayList<>();
            objects.add(1);
            objects.add(2);
            objects.add(3);
            long l = System.currentTimeMillis();
            List<String> strings = Arrays.asList(s.split(","));
            objects.forEach(item->{
                CompletableFuture.supplyAsync(()->item)
                        .thenApply(a-> add(a));
            });
            System.out.println(System.currentTimeMillis()-l);
            long l1 = System.currentTimeMillis();
            objects.forEach( A::add);
            System.out.println(System.currentTimeMillis()-l1);
        }


        public static int add(int a){
            System.out.println(a+1);
            return a+1;
        }
    }


    static class PeriodDto{
        private Date from;
        private Date to;

        public Date getFrom() {
            return from;
        }

        public void setFrom(Date from) {
            this.from = from;
        }

        public Date getTo() {
            return to;
        }

        public void setTo(Date to) {
            this.to = to;
        }
    }

//    public static List<PeriodDto> mergePeriod(List<PeriodDto> periodList) {
//        List<PeriodDto> result = new ArrayList<PeriodDto>();
//
//        if (periodList == null || periodList.size() < 1) {
//            return result;
//        }
//
//        // 对区间进行排序
//        Collections.sort(periodList, new Comparator<PeriodDto>() {
//            @Override
//            public int compare(PeriodDto o1, PeriodDto o2) {
//                if ((o1.getFrom().getTime() - o2.getFrom().getTime()) > 0) {
//                    return 1;
//                } else if ((o1.getFrom().getTime() - o2.getFrom().getTime()) == 0) {
//                    return 0;
//                } else {
//                    return -1;
//                }
//            }
//        });
//        PeriodDto prev = null;
//        for (PeriodDto item : periodList) {
//            if (prev == null || prev.getTo().before(item.getFrom())) {
//                result.add(item);
//                prev = item;
//            } else if (prev.getTo().before(item.getTo())) {
//                prev.setTo(item.getTo());
//            }
//        }
//
//        return result;
//    }
    public static <T> List<T> mergePeriod(List<T> periodList, Function<T,Date> start, Function<T,Date> end, BiConsumer<T,Date> consumer) {
        List<T> result = new ArrayList<>();

        if (periodList == null || periodList.size() < 1) {
            return result;
        }

        // 对区间进行排序
        Collections.sort(periodList, new Comparator<T>() {
            @Override
            public int compare(T o1, T o2) {
                if ((start.apply(o1).getTime() - start.apply(o2).getTime()) > 0) {
                    return 1;
                } else if ((start.apply(o1).getTime() - start.apply(o2).getTime()) == 0) {
                    return 0;
                } else {
                    return -1;
                }
            }
        });
        T prev = null;
        for (T item : periodList) {
            if (prev == null || end.apply(prev).before(start.apply(item))) {
                result.add(item);
                prev = item;
            } else if (end.apply(prev).before(end.apply(item))) {
                consumer.accept(prev,end.apply(prev));
            }
        }
        return result;
    }

    public static void main(String[] args) throws ParseException {

        PeriodDto date1 = new PeriodDto();
        date1.setFrom(DateUtil.parseDate("2020-01-01"));
        date1.setTo(DateUtil.parseDate("2020-05-01"));

        PeriodDto date2 = new PeriodDto();
        date2.setFrom(DateUtil.parseDate("2021-05-01"));
        date2.setTo(DateUtil.parseDate("2021-07-29"));

        PeriodDto date3 = new PeriodDto();
        date3.setFrom(DateUtil.parseDate("2018-01-01"));
        date3.setTo(DateUtil.parseDate("2019-01-01"));

        PeriodDto date4 = new PeriodDto();
        date4.setFrom(DateUtil.parseDate("2020-04-01 12:00:00"));
        date4.setTo(DateUtil.parseDate("2021-01-01 12:00:00"));

        List<PeriodDto> list = new ArrayList<PeriodDto>();
        list.add(date1);
        list.add(date2);
        list.add(date3);
        list.add(date4);

//        List<PeriodDto> result = mergePeriod(list);
        List<PeriodDto> result = mergePeriod(list,PeriodDto::getFrom,PeriodDto::getTo,PeriodDto::setTo);
        System.out.println(JSONObject.toJSONStringWithDateFormat(result, JSONObject.DEFFAULT_DATE_FORMAT));
    }

}
