package com.learn.bases.datatypes.bigdecimal.calendar;

import java.text.DateFormat;
import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created with IntelliJ IDEA.
 * User: dmitriy.bilyk
 * Date: 29.03.13
 * Time: 22:01
 * To change this template use File | Settings | File Templates.
 */
public class CalendarTest {
    public static void main(String[] args) {

       //Dates
        Date date = new Date();
        Date date2 = new Date(System.currentTimeMillis());
        System.out.println(date);
        System.out.println(date);
        for(int i=0; i<10; i++){
            date.setTime(System.currentTimeMillis());
            System.out.println(date);
        }

       //Calendars
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        System.out.println(Calendar.HOUR);
        System.out.println(Calendar.HOUR_OF_DAY);
        calendar.set(Calendar.MONTH, Calendar.JUNE);
        System.out.println(calendar.get(Calendar.MONTH));

        //DateFormat
        DateFormat dateFormatter = DateFormat.getDateInstance(DateFormat.LONG);
        Date aDate = new Date();
        String formattedDate = dateFormatter.format(aDate);
        System.out.println(formattedDate);

        //SimpleDateFormat
        Date aaDate = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("MM-/dd-/yyyy");
        String formaattedDate = formatter.format(aaDate);
        System.out.println(formaattedDate);

        DateFormatSymbols symbols = new DateFormatSymbols();
        String[] oddMonthAbbreviations = new String[] {
                "Ja","Fe","Mh","Ap","My","Jn","Jy","Au","Se","Oc","No","De" };
        symbols.setShortMonths(oddMonthAbbreviations);

        formatter = new SimpleDateFormat("MMM dd, yyyy", symbols);
        formattedDate = formatter.format(new Date());
        System.out.println(formattedDate);


        System.out.println("-------------------ADD---------------");

        //add
        Calendar gregorianCalendar = GregorianCalendar.getInstance();
        gregorianCalendar.set(Calendar.MONTH, 10);
        gregorianCalendar.set(Calendar.DAY_OF_MONTH, 15);
        gregorianCalendar.set(Calendar.YEAR, 2005);
        formatter = new SimpleDateFormat("MMM dd, yyyy");
        System.out.println("Before: " + formatter.format(gregorianCalendar.getTime()));

        gregorianCalendar.add(Calendar.YEAR, -20);
        System.out.println("After: " + formatter.format(gregorianCalendar.getTime()));

        System.out.println("-------------------ROLL------------------");

        Calendar gregCalendar = GregorianCalendar.getInstance();
        gregCalendar.set(Calendar.MONTH, 10);
        gregCalendar.set(Calendar.DAY_OF_MONTH, 15);
        gregCalendar.set(Calendar.YEAR, 2005);
        formatter = new SimpleDateFormat("MMM dd, yyyy");
        System.out.println("Before: " + formatter.format(gregCalendar.getTime()));
        gregCalendar.roll(2, false);
        System.out.println("After: " + formatter.format(gregCalendar.getTime()));
    }
}
