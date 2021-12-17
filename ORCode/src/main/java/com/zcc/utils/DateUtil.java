package com.zcc.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.Function;

public class DateUtil {

    public static final String YYYYMMDD = "yyyy-MM-dd";
    public static final int FIRST_DAY_OF_WEEK = Calendar.MONDAY;


    static class PeriodDto {
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
    public static <T> Map<String, Integer> mergePeriod(List<T> periodList, Function<T, Date> start, Function<T, Date> end, BiConsumer<T, Date> consumer) {
        Map<String, Integer> map = new HashMap<>();
        List<T> result = new ArrayList<>();

        if (periodList == null || periodList.size() < 1) {
            return null;
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
                consumer.accept(prev, end.apply(prev));
            }
        }
        result.forEach(item -> {
            Date startTime = start.apply(item);
            Date endTime = end.apply(item);
            int startMonth = getMonth(startTime);
            int endMonth = getMonth(endTime);
            int startYear = getYear(startTime);
            int endYear = getYear(endTime);
            if (startYear == endYear && startMonth == endMonth) { // 同月
                int day = diffDay(startTime, endTime);
                map.put(formatDate(startTime, "yyyy-MM"), day);
            } else { // 不同月
                Map<String, Integer> months = getMonths(startTime, endTime);
                int remainDayOfMonth = getRemainDayOfMonth(startTime) + 1;
                int passDayOfMonth = getPassDayOfMonth(endTime);
                Iterator<Map.Entry<String, Integer>> iterator = months.entrySet().iterator();
                int i = 0;
                while (iterator.hasNext()) {
                    Map.Entry<String, Integer> next = iterator.next();
                    if (i == 0) {
                        map.put(next.getKey(), remainDayOfMonth);
                        i++;
                        continue;
                    }
                    if (!iterator.hasNext()) {
                        map.put(next.getKey(), passDayOfMonth);
                        continue;
                    }
                    map.put(next.getKey(), next.getValue());
                }
            }
        });
        return map;
    }

    /**
     * 天数差
     *
     * @param
     * @param
     * @return
     */
    public static int diffDay(Date before, Date after) {
        return Integer.parseInt(String.valueOf(((after.getTime() - before.getTime()) / 86400000)));
    }

    public static void main(String[] args) throws ParseException {
//        System.out.println(getPassDayOfMonth(parseDate("2021-12-16")));

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
        Map<String, Integer> stringIntegerMap = mergePeriod(list, PeriodDto::getFrom, PeriodDto::getTo, PeriodDto::setTo);
        System.out.println(stringIntegerMap.size());
        System.out.println(stringIntegerMap);
//        System.out.println(JSONObject.toJSONStringWithDateFormat(result, JSONObject.DEFFAULT_DATE_FORMAT));
    }

//    public static void main(String args[]) throws ParseException {
//        String str_begin = "2021-01-15";
//        String str_end = "2022-2-15";
//        System.out.println(getMonths(parseDate(str_begin), parseDate(str_end)));
////        System.out.println(getMonths(str_begin,str_end));
////        for (String s : map.keySet()){
////            System.out.println(s);
//////            Date date = parseDate(s, YYYYMMDD);
//////            Date[] seasonDate = getSeasonDate(date);
//////            int season = getSeason(date);
////        }
//
//
//        //getMonths(str_begin, str_end) ;
//        //getYears(str_begin, str_end) ;
//    }

    /**
     * 获取年份
     *
     * @param begins
     * @param ends
     * @throws ParseException
     */
    public static Map<String, List<String>> getYears(String begins, String ends) throws ParseException {
        Map<String, List<String>> map = new LinkedHashMap<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date begin = new Date();
        Date end = new Date();
        try {
            begin = sdf.parse(begins);
            end = sdf.parse(ends);
        } catch (ParseException e) {
            System.out.println("日期输入格式不对");
            return map;
        }
        Calendar cal_begin = Calendar.getInstance();
        cal_begin.setTime(begin);
        Calendar cal_end = Calendar.getInstance();
        cal_end.setTime(end);
        while (true) {
            List<String> years = new LinkedList<>();
            if (cal_begin.get(Calendar.YEAR) == cal_end.get(Calendar.YEAR)) {
//                System.out.println(sdf.format(cal_begin.getTime()) + "~" + sdf.format(cal_end.getTime()));
                years.add(cal_begin.get(Calendar.YEAR) + "01" + "01");
                years.add(cal_begin.get(Calendar.YEAR) + "12" + "31");
                map.put(getYear(cal_begin.getTime()) + "", years);
                break;
            } else {
                String str_begin = sdf.format(cal_begin.getTime());
                String str_end = getMonthEnd(cal_begin.getTime());
                int years1 = getYear(str_begin);
                String year = years1 + "-12" + "-31";
//                System.out.println(str_begin + "~" + year);
                years.add(str_begin);
                years.add(year);
                map.put(getYear(cal_begin.getTime()) + "", years);
                cal_begin.add(Calendar.YEAR, 1);
                cal_begin.set(Calendar.DAY_OF_YEAR, 1);

            }

        }
        return map;
    }

    /**
     * 获取时间段内的季度
     *
     * @param begins
     * @param ends
     */
    public static Map<String, List<String>> getQuarter(String begins, String ends) {
        Map<String, List<String>> map = new LinkedHashMap<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date begin = new Date();
        Date end = new Date();
        try {
            begin = sdf.parse(begins);
            end = sdf.parse(ends);
        } catch (ParseException e) {
            System.out.println("日期输入格式不对");
            return map;
        }
        Calendar cal_begin = Calendar.getInstance();
        cal_begin.setTime(begin);
        Calendar cal_end = Calendar.getInstance();
        cal_end.setTime(end);
        while (true) {
            List<String> quarters = new ArrayList<>();
            String str_begin = sdf.format(cal_begin.getTime());
            String str_end = getMonthEnd(cal_begin.getTime());
            Date begin_date = parseDate(str_begin);
            Date end_date = parseDate(str_end);
            String Quarter_begin = formatDate(getFirstDateOfSeason(begin_date));
            String Quarter_end = formatDate(getLastDateOfSeason(end_date));
            Date Quarter_begin_date = parseDate(Quarter_begin);
            Date Quarter_end_date = parseDate(Quarter_end);


            if (Quarter_end_date.getTime() == end_date.getTime()) {

                if (Quarter_begin_date.getTime() <= begin.getTime()) {
                    Quarter_begin = begins;
                }
                if (Quarter_end_date.getTime() >= end.getTime()) {
                    Quarter_end = ends;
                }
                quarters.add(Quarter_begin);
                quarters.add(Quarter_end);
                System.out.println(Quarter_begin + "~" + Quarter_end);
                if (end.getTime() <= end_date.getTime()) {
                    break;
                }
            } else if (Quarter_begin_date.getTime() == begin_date.getTime()) {
                if (Quarter_begin_date.getTime() <= begin.getTime()) {
                    Quarter_begin = begins;
                }
                if (Quarter_end_date.getTime() >= end.getTime()) {
                    Quarter_end = ends;
                }
                quarters.add(Quarter_begin);
                quarters.add(Quarter_end);
                System.out.println(Quarter_begin + "~" + Quarter_end);
            }
            map.put(getYear(cal_begin.getTime()) + "-" + getSeason(cal_begin.getTime()), quarters);
            cal_begin.add(Calendar.MONTH, 1);
            cal_begin.set(Calendar.DAY_OF_MONTH, 1);

        }
        return map;
    }


    /**
     * 获取时间段内的月
     *
     * @param begins
     * @param ends
     */
    public static Map<String, List<String>> getMonths(String begins, String ends) {
        Map<String, List<String>> map = new LinkedHashMap<>();
        SimpleDateFormat sdf = new SimpleDateFormat(YYYYMMDD);
        Date begin = new Date();
        Date end = new Date();
        try {
            begin = sdf.parse(begins);
            end = sdf.parse(ends);
        } catch (ParseException e) {
            System.out.println("日期输入格式不对");
            return map;
        }

        Calendar cal_begin = Calendar.getInstance();
        cal_begin.setTime(begin);
        Calendar cal_end = Calendar.getInstance();
        cal_end.setTime(end);
        while (true) {
            List<String> months = new LinkedList<>();
            if (cal_begin.get(Calendar.YEAR) == cal_end.get(Calendar.YEAR) && cal_begin.get(Calendar.MONTH) == cal_end.get(Calendar.MONTH)) {
                System.out.println(sdf.format(cal_begin.getTime()) + "~" + sdf.format(cal_end.getTime()));
                months.add(cal_begin.get(Calendar.YEAR) + cal_begin.get(Calendar.MONTH) + "01");
                months.add(cal_begin.get(Calendar.YEAR) + cal_begin.get(Calendar.MONTH) + "31");
                map.put(getYear(cal_begin.getTime()) + "-" + getMonth(cal_begin.getTime()), months);
                break;
            }
            String str_begin = sdf.format(cal_begin.getTime());
            String str_end = getMonthEnd(cal_begin.getTime());
            months.add(str_begin);
            months.add(str_end);
//            System.out.println(str_begin+"~"+str_end);
            map.put(getYear(cal_begin.getTime()) + "-" + getMonth(cal_begin.getTime()), months);
            cal_begin.add(Calendar.MONTH, 1);
            cal_begin.set(Calendar.DAY_OF_MONTH, 1);
        }
        return map;
    }

    /**
     * 获取时间段内的周
     *
     * @param begins
     * @param ends
     * @throws ParseException
     */
    public static void getWeeks(String begins, String ends) throws ParseException {
        SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat sdw = new SimpleDateFormat("E");
        String begin_date = begins;
        String end_date = ends;
        //String begin_date_fm = begin_date.substring(0, 4) + "-" + begin_date.substring(4,6) + "-" + begin_date.substring(6,8);
        // String end_date_fm =  end_date.substring(0, 4) + "-" + end_date.substring(4,6) + "-" + end_date.substring(6,8);
        String begin_date_fm = begins;
        String end_date_fm = ends;
        Date b = null;
        Date e = null;
        try {
            b = sd.parse(begin_date_fm);
            e = sd.parse(end_date_fm);
        } catch (ParseException ee) {
            ee.printStackTrace();
        }
        Calendar rightNow = Calendar.getInstance();
        rightNow.setTime(b);
        Date time = b;
        String year = begin_date_fm.split("-")[0];
        String mon = Integer.parseInt(begin_date_fm.split("-")[1]) < 10 ? begin_date_fm.split("-")[1] : begin_date_fm.split("-")[1];
        String day = Integer.parseInt(begin_date_fm.split("-")[2]) < 10 ? begin_date_fm.split("-")[2] : begin_date_fm.split("-")[2];
        String timeb = year + mon + day;
        String timee = null;
        if (begin_date == end_date) {
            System.out.println(begin_date + "~" + end_date);
        } else {
            while (time.getTime() <= e.getTime()) {
                rightNow.add(Calendar.DAY_OF_YEAR, 1);
                time = sd.parse(sd.format(rightNow.getTime()));
                if (time.getTime() > e.getTime()) {
                    break;
                }
                String timew = sdw.format(time);
                if (("星期一").equals(timew)) {
                    timeb = (sd.format(time)).replaceAll("-", "");
                }
                if (("星期日").equals(timew) || ("星期七").equals(timew) || time.getTime() == e.getTime()) {
                    timee = (sd.format(time)).replaceAll("-", "");
                    String begindate = fomaToDatas(timeb);
                    String enddate = fomaToDatas(timee);
                    System.out.println(begindate + "~" + enddate);
                }
            }

        }
    }


    public static String fomaToDatas(String data) {
        DateFormat fmt = new SimpleDateFormat("yyyyMMdd");
        try {
            Date parse = fmt.parse(data);
            DateFormat fmt2 = new SimpleDateFormat("yyyy-MM-dd");
            return fmt2.format(parse);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }


    }


    /**
     * 日期解析
     *
     * @param strDate
     * @param pattern
     * @return
     */
    public static Date parseDate(String strDate, String pattern) {
        Date date = null;
        try {
            if (pattern == null) {
                pattern = YYYYMMDD;
            }
            SimpleDateFormat format = new SimpleDateFormat(pattern);
            date = format.parse(strDate);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return date;
    }

    public static int getYear(String date) throws ParseException {
        Calendar c = Calendar.getInstance();
        c.setTime(parseDate(date));
        int year = c.get(Calendar.YEAR);
        return year;
    }

    public String getYearMonth(Date date) {
        return formatDateByFormat(date, "yyyy-MM");
    }

    /**
     * 取得指定月份的第一天
     *
     * @param date String
     * @return String
     */
    public String getMonthBegin(Date date) {
        return formatDateByFormat(date, "yyyy-MM") + "-01";
    }

    /**
     * 取得指定月份的最后一天
     *
     * @param date String
     * @return String
     */
    public static String getMonthEnd(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, 1);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.add(Calendar.DAY_OF_YEAR, -1);
        return formatDateByFormat(calendar.getTime(), "yyyy-MM-dd");
    }

    /**
     * 以指定的格式来格式化日期
     *
     * @param date   Date
     * @param format String
     * @return String
     */
    public static String formatDateByFormat(Date date, String format) {
        String result = "";
        if (date != null) {
            try {
                SimpleDateFormat sdf = new SimpleDateFormat(format);
                result = sdf.format(date);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return result;
    }

    /**
     * @param strDate
     * @return
     */
    public static Date parseDate(String strDate) {
        return parseDate(strDate, null);
    }


    /**
     * format date
     *
     * @param date
     * @return
     */
    public static String formatDate(Date date) {
        return formatDate(date, null);
    }

    /**
     * format date
     *
     * @param date
     * @param pattern
     * @return
     */
    public static String formatDate(Date date, String pattern) {
        String strDate = null;
        try {
            if (pattern == null) {
                pattern = YYYYMMDD;
            }
            SimpleDateFormat format = new SimpleDateFormat(pattern);
            strDate = format.format(date);
        } catch (Exception e) {

        }
        return strDate;
    }

    /**
     * 取得日期：年
     *
     * @param date
     * @return
     */
    public static int getYear(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        int year = c.get(Calendar.YEAR);
        return year;
    }

    /**
     * 取得日期：月
     *
     * @param date
     * @return
     */
    public static int getMonth(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        int month = c.get(Calendar.MONTH);
        return month + 1;
    }

    /**
     * 取得日期：日
     *
     * @param date
     * @return
     */
    public static int getDay(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        int da = c.get(Calendar.DAY_OF_MONTH);
        return da;
    }

    /**
     * 取得当天日期是周几
     *
     * @param date
     * @return
     */
    public static int getWeekDay(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        int week_of_year = c.get(Calendar.DAY_OF_WEEK);
        return week_of_year - 1;
    }

    /**
     * 取得一年的第几周
     *
     * @param date
     * @return
     */
    public static int getWeekOfYear(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        int week_of_year = c.get(Calendar.WEEK_OF_YEAR);
        return week_of_year;
    }

    /**
     * 获得一周的开始日期和结束日期
     *
     * @param date
     * @param pattern
     * @return
     */
    public static String getWeekBeginAndEndDate(Date date, String pattern) {
        Date monday = getMondayOfWeek(date);
        Date sunday = getSundayOfWeek(date);
        return formatDate(monday, pattern) + " - "
                + formatDate(sunday, pattern);
    }

    /**
     * 根据日期取得对应周周一日期
     *
     * @param date
     * @return
     */
    public static Date getMondayOfWeek(Date date) {
        Calendar monday = Calendar.getInstance();
        monday.setTime(date);
        monday.setFirstDayOfWeek(FIRST_DAY_OF_WEEK);
        monday.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        return monday.getTime();
    }

    /**
     * 根据日期取得对应周周日日期
     *
     * @param date
     * @return
     */
    public static Date getSundayOfWeek(Date date) {
        Calendar sunday = Calendar.getInstance();
        sunday.setTime(date);
        sunday.setFirstDayOfWeek(FIRST_DAY_OF_WEEK);
        sunday.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
        return sunday.getTime();
    }

    /**
     * 取得月的剩余天数
     *
     * @param date
     * @return
     */
    public static int getRemainDayOfMonth(Date date) {
        int dayOfMonth = getDayOfMonth(date);
        int day = getPassDayOfMonth(date);
        return dayOfMonth - day;
    }

    /**
     * 取得月已经过的天数
     *
     * @param date
     * @return
     */
    public static int getPassDayOfMonth(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * 取得月天数
     *
     * @param date
     * @return
     */
    public static int getDayOfMonth(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.getActualMaximum(Calendar.DAY_OF_MONTH);
    }

    /**
     * 取得月第一天
     *
     * @param date
     * @return
     */
    public static Date getFirstDateOfMonth(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.DAY_OF_MONTH, c.getActualMinimum(Calendar.DAY_OF_MONTH));
        return c.getTime();
    }

    /**
     * 取得月最后一天
     *
     * @param date
     * @return
     */
    public static Date getLastDateOfMonth(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH));
        return c.getTime();
    }

    /**
     * 取得季度第一天
     *
     * @param date
     * @return
     */
    public static Date getFirstDateOfSeason(Date date) {
        return getFirstDateOfMonth(getSeasonDate(date)[0]);
    }

    /**
     * 取得季度最后一天
     *
     * @param date
     * @return
     */
    public static Date getLastDateOfSeason(Date date) {
        return getLastDateOfMonth(getSeasonDate(date)[2]);
    }

    /**
     * 取得季度天数
     *
     * @param date
     * @return
     */
    public static int getDayOfSeason(Date date) {
        int day = 0;
        Date[] seasonDates = getSeasonDate(date);
        for (Date date2 : seasonDates) {
            day += getDayOfMonth(date2);
        }
        return day;
    }

    /**
     * 取得季度剩余天数
     *
     * @param date
     * @return
     */
    public static int getRemainDayOfSeason(Date date) {
        return getDayOfSeason(date) - getPassDayOfSeason(date);
    }

    /**
     * 取得季度已过天数
     *
     * @param date
     * @return
     */
    public static int getPassDayOfSeason(Date date) {
        int day = 0;

        Date[] seasonDates = getSeasonDate(date);

        Calendar c = Calendar.getInstance();
        c.setTime(date);
        int month = c.get(Calendar.MONTH);

        if (month == Calendar.JANUARY || month == Calendar.APRIL
                || month == Calendar.JULY || month == Calendar.OCTOBER) {// 季度第一个月
            day = getPassDayOfMonth(seasonDates[0]);
        } else if (month == Calendar.FEBRUARY || month == Calendar.MAY
                || month == Calendar.AUGUST || month == Calendar.NOVEMBER) {// 季度第二个月
            day = getDayOfMonth(seasonDates[0])
                    + getPassDayOfMonth(seasonDates[1]);
        } else if (month == Calendar.MARCH || month == Calendar.JUNE
                || month == Calendar.SEPTEMBER || month == Calendar.DECEMBER) {// 季度第三个月
            day = getDayOfMonth(seasonDates[0]) + getDayOfMonth(seasonDates[1])
                    + getPassDayOfMonth(seasonDates[2]);
        }
        return day;
    }

    /**
     * 取得季度月
     *
     * @param date
     * @return
     */
    public static Date[] getSeasonDate(Date date) {
        Date[] season = new Date[3];

        Calendar c = Calendar.getInstance();
        c.setTime(date);

        int nSeason = getSeason(date);
        if (nSeason == 1) {// 第一季度
            c.set(Calendar.MONTH, Calendar.JANUARY);
            season[0] = c.getTime();
            c.set(Calendar.MONTH, Calendar.FEBRUARY);
            season[1] = c.getTime();
            c.set(Calendar.MONTH, Calendar.MARCH);
            season[2] = c.getTime();
        } else if (nSeason == 2) {// 第二季度
            c.set(Calendar.MONTH, Calendar.APRIL);
            season[0] = c.getTime();
            c.set(Calendar.MONTH, Calendar.MAY);
            season[1] = c.getTime();
            c.set(Calendar.MONTH, Calendar.JUNE);
            season[2] = c.getTime();
        } else if (nSeason == 3) {// 第三季度
            c.set(Calendar.MONTH, Calendar.JULY);
            season[0] = c.getTime();
            c.set(Calendar.MONTH, Calendar.AUGUST);
            season[1] = c.getTime();
            c.set(Calendar.MONTH, Calendar.SEPTEMBER);
            season[2] = c.getTime();
        } else if (nSeason == 4) {// 第四季度
            c.set(Calendar.MONTH, Calendar.OCTOBER);
            season[0] = c.getTime();
            c.set(Calendar.MONTH, Calendar.NOVEMBER);
            season[1] = c.getTime();
            c.set(Calendar.MONTH, Calendar.DECEMBER);
            season[2] = c.getTime();
        }
        return season;
    }

    /**
     * 1 第一季度 2 第二季度 3 第三季度 4 第四季度
     *
     * @param date
     * @return
     */
    public static int getSeason(Date date) {

        int season = 0;

        Calendar c = Calendar.getInstance();
        c.setTime(date);
        int month = c.get(Calendar.MONTH);
        switch (month) {
            case Calendar.JANUARY:
            case Calendar.FEBRUARY:
            case Calendar.MARCH:
                season = 1;
                break;
            case Calendar.APRIL:
            case Calendar.MAY:
            case Calendar.JUNE:
                season = 2;
                break;
            case Calendar.JULY:
            case Calendar.AUGUST:
            case Calendar.SEPTEMBER:
                season = 3;
                break;
            case Calendar.OCTOBER:
            case Calendar.NOVEMBER:
            case Calendar.DECEMBER:
                season = 4;
                break;
            default:
                break;
        }
        return season;
    }

    /**
     * 获取时间段内的月
     *
     * @param begin
     * @param end
     */
    public static Map<String, Integer> getMonths(Date begin, Date end) {

        Map<String, Integer> map = new LinkedHashMap<>();
        SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd");
        Calendar cal_begin = Calendar.getInstance();
        cal_begin.setTime(begin);
        Calendar cal_end = Calendar.getInstance();
        cal_end.setTime(end);
        while (true) {

            if (cal_begin.get(Calendar.YEAR) == cal_end.get(Calendar.YEAR) && cal_begin.get(Calendar.MONTH) == cal_end.get(Calendar.MONTH)) {
                int i = cal_begin.get(Calendar.MONTH) + 1;
                String strMonth = i<10?"0"+i:i+"";
                String s = cal_begin.get(Calendar.YEAR) + "-" + strMonth + "-01";
                Date date = parseDate(s);
                map.put(getYear(cal_begin.getTime()) + "-" + getMonth(cal_begin.getTime()), getDayOfMonth(date));
                break;
            }
            String str_begin = sdf.format(cal_begin.getTime());
            int month = getMonth(cal_begin.getTime());
            String strMonth = month<10?"0"+month:month+"";
            map.put(getYear(cal_begin.getTime()) + "-" + strMonth, getDayOfMonth(parseDate(str_begin, "yyyy-MM-dd")));
            cal_begin.add(Calendar.MONTH, 1);
            cal_begin.set(Calendar.DAY_OF_MONTH, 1);
        }
        return map;
    }

}