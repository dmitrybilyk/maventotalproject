package com.learn.bases.bloh;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created with IntelliJ IDEA.
 * User: flash
 * Date: 09.12.12
 * Time: 13:11
 * To change this template use File | Settings | File Templates.
 */


public class _NeverCreateDuplicateObjectIfpossible {
    public static void main(String[] args) {
        String s = new String("test"); //never do in such way
        //You should do:
        String ss = "test";
        //You should prefer using static initialization methods instead of constructors.
        //For example: Boolean b = new Boolean("true").
        //Bad... You should use:
        Boolean b = Boolean.valueOf("true");
    }

}



//class Person {
//    private final Date birthDate;
//    // Прочие поля опущены
//    public Person(Date birthDate) {
//        this.birthDate = birthDate;
//    }
//    // Никогда не делайте т! ак
//    public boolean isBabyBoomer() {
//        Calendar gmtCal =
//                Calendar.getInstance(TimeZone.getTimeZone("GMT"));
//        gmtCal.set(1946, Calendar.JANUARY, 1, 0, 0, 0);
//        Date boomStart = gmtCal.getTime();
//        gmtCal.set(1965, Calendar.JANUARY, 1, 0, 0, 0);
//        Date boomEnd = gmtCal.getTime();
//        return birthDate.compareTo(boomStart) >= 0 &&
//                birthDate.compareTo(boomEnd) < 0;
//    }
//    public static void main(String[] args) {
//        Person p = new Person(new Date());
//        long startTime = System.currentTimeMillis();
//        for (int i=0; i<1000000; i++)
//            p.isBabyBoomer();
//        long endTime = System.currentTimeMillis();
//        long time = endTime - startTime;
//        System.out.println(time+" ms.");
//    }
//}


class Person {
    private final Date birthDate;
    public Person(Date birthDate) {
        this.birthDate = birthDate;
    }
    /**
     * Даты начала и конца демографического взрыва
     */
    private static final Date BOOM_START;
    private static final Date BOOM_END;
    static {
        Calendar gmtCal =
                Calendar.getInstance(TimeZone.getTimeZone("GMT"));
        gmtCal.set(1946, Calendar.JANUARY, 1, 0, 0, 0);
        BOOM_START = gmtCal.getTime();
        gmtCal.set(1965, Calendar.JANUARY, 1, 0, 0, 0);
        BOOM_END = gmtCal.getTime();
    }
    public boolean isBabyBoomer() {
        return birthDate.compareTo(BOOM_START) >= 0 &&
                birthDate.compareTo(BOOM_END) < 0;
    }
    public static void main(String[] args) {
        Person p = new Person(new Date());
        long startTime = System.currentTimeMillis();
        for (int i=0; i<1000000; i++)
            p.isBabyBoomer();
        long endTime = System.currentTimeMillis();
        long time = endTime - startTime;
        System.out.println(time+" ms.");
    } }
