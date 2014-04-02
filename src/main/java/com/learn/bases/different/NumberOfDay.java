package com.learn.bases.different;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class NumberOfDay {
    public static void main(String[] args) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("DDD");
        Calendar calender = Calendar.getInstance();
        calender.set(Calendar.MONTH, 3);
        calender.set(Calendar.DATE, 9);
        System.out.println(calender.getTime());
        System.out.println(simpleDateFormat.format(calender.getTime()));
    }
}
