package com.learn.bases.regexpTest.regexTest;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by IntelliJ IDEA.
 * User: bilyk
 * Date: 28.05.12
 * Time: 17:47
 * To change this template use File | Settings | File Templates.
 */
public class TestRegex {
    public static void main(String[] args){
//        String input = "Here is a WikiWord followed by AnotherWikiWord, then SomeWikiWord.";
////        Pattern pattern = Pattern.compile("[A-Z]*([a-z]*)+");
//        Pattern pattern = Pattern.compile("[b]");
//        Matcher matcher = pattern.matcher(input);
//
//        matcher.replaceAll("Replaced");
//
//        System.out.println(input);
//        while (matcher.find()){
////            System.out.println("Found this wiki word:" + matcher.start());
//        }


            String aDate = "date: 12-15-tttt";
            Pattern datePattern = Pattern.compile("(\\d{2})-(\\d{2})-(\\D{4})");
            Matcher dateMatcher = datePattern.matcher(aDate);
            if (dateMatcher.find())
            {
                System.out.println("Month is: " + dateMatcher.group(1));
                System.out.println("Day is:   " + dateMatcher.group(2));
                System.out.println("Year is:  " + dateMatcher.group(3));
            }

    }
}
