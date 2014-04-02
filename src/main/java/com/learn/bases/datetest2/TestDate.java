package com.learn.bases.datetest2;

import org.apache.commons.httpclient.util.DateUtil;
import org.apache.tools.ant.util.DateUtils;

import java.util.Calendar;
import java.util.Date;

public class TestDate {
    public static void main(String[] args) {
        long startDateLong = 1380034800000L;
        Date startDate = new Date(startDateLong);
        long endDateLong = 1380121200000L;
        Date endDate = new Date(endDateLong);
        long procDateLong = 1365714000000l;
        Date procDate = new Date(procDateLong);
        System.out.println(endDate);
        System.out.println("start " +startDate);
//        System.out.println(procDate);
//        System.out.println(startDateLong<procDateLong);
//        System.out.println(procDateLong<endDateLong);

//        System.out.println(isAfter(new Date(), new Date()));



    }

    public static boolean isAfter(Date day1, Date day2) {
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(day1);
        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(day2);
        return isAfter(cal1, cal2);
    }

    public static boolean isAfter(Calendar day1, Calendar day2) {
        Calendar cloneDay1 = (Calendar)day1.clone();
        roundCalendarToDay(cloneDay1);
        Calendar cloneDay2 = (Calendar)day2.clone();
        roundCalendarToDay(cloneDay2);
        return cloneDay1.after(cloneDay2);
    }

    public static void roundCalendarToDay(Calendar calendar) {
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
    }
}
