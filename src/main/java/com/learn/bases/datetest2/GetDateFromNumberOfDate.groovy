package com.learn.bases.datetest2

import java.text.DateFormat
import java.text.ParseException
import java.text.SimpleDateFormat

class GetDateFromNumberOfDate {
    public static void main(String[] args) {

        getNumberInYearFromDate("17.02.2014")
//        getDateFromNumberInYear("14152");
    }

    static void getNumberInYearFromDate(String targetDateString) {
        String pattern = "yyDDD"
        Date date = new SimpleDateFormat("dd.MM.yyyy").parse(targetDateString);
        final SimpleDateFormat sdf = new SimpleDateFormat(pattern)
        sdf.setLenient(false)
        Calendar calendar = new GregorianCalendar()
        calendar.setTime(date)
        System.out.println(calendar?.getTime() ? calendar.format(pattern) : null)
    }

    private static void getDateFromNumberInYear(String numberInYear){
        String DATE_NUMBER_IN_YEAR = "yyDDD";

        try {
            Date d =  new SimpleDateFormat(DATE_NUMBER_IN_YEAR).parse(numberInYear);
            DateFormat df = new SimpleDateFormat("dd.MM.yy");
            System.out.println(df.format(d));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

}
