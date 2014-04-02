package com.learn.bases.appacheutils;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;

public class AppacheTest {
    public static void main(String[] args) {
        String s = "   check   you  ";
        String s2 = StringUtils.trim(s);
        System.out.println(s2.replaceAll("( )+", " "));
        String[] sArray = {"Dima", "Ruslan", "Andrey"};
        for (String s1 : sArray) {
            System.out.println(s1);
        }
        CollectionUtils.reverseArray(sArray);
        for (String s1 : sArray) {
            System.out.println(s1);
        }
    }
}
