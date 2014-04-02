package com.learn.bases.datetest2;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class GetDateBytNumberAndYear {
    public static void main(String[] args) {
        String numberInYear = "14152";
        System.out.println(getDateFromNumberInYear(numberInYear));

        String targetDateString = "11.06.2014";
//        Date date = new SimpleDateFormat("dd.MM.yyyy").parse(targetDateString);
        System.out.println(getDateInNumberFormatFromDate(targetDateString));

    }

    private static String getDateInNumberFormatFromDate(String targetDate){
        String USUAL_DATE_FORMAT = "dd.MM.yyyy";
        String DATE_NUMBER_IN_YEAR = "yyddd";
        try {
            Date date = new SimpleDateFormat("dd.MM.yyyy").parse(targetDate);
            DateFormat df = new SimpleDateFormat(DATE_NUMBER_IN_YEAR);
            return df.format(date);
        } catch (ParseException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        return null;
    }

    private static String getDateFromNumberInYear(String numberInYear){
        String DATE_NUMBER_IN_YEAR = "yyddd";

        try {
            Date d =  new SimpleDateFormat(DATE_NUMBER_IN_YEAR).parse(numberInYear);
            DateFormat df = new SimpleDateFormat("dd.MM.yy");
            return df.format(d);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

//    static String format(Calendar calendar, String pattern) {
//        final SimpleDateFormat sdf = new SimpleDateFormat(pattern)
//        sdf.setLenient(false)
//        return calendar?.getTime() ? sdf.format(calendar.getTime()) : null
//    }

}
