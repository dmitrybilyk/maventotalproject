package com.learn.refactoring.removeassignmentstoparams.usenewvariableforassignment;

import java.util.Date;

/**
 * Created by dmitry on 5/25/14.
 */
public class PassingParams {
    public static void main(String[] args) {

        Date d1 = new Date("1 Apr 98");
        nextDateUpdate(d1);
        System.out.println("d1 after nextDate - " + d1);
        Date d2 = new Date("1 Apr 98");
        nextDateReplace(d2);
        System.out.println("d1 after replace - " + d2);
    }

    private static void nextDateUpdate(Date arg) {
        arg.setDate(arg.getDate() +1);
        System.out.println("arg in next day - " + arg);
    }

    private static void nextDateReplace(Date arg) {
        arg = new Date(arg.getYear(), arg.getMonth(), arg.getDate() + 1);
        System.out.println("arg in nextDay - " + arg);
    }

}
