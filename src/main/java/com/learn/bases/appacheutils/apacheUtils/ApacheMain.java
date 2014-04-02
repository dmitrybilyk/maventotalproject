package com.learn.bases.appacheutils.apacheUtils;

import org.apache.commons.lang.StringUtils;

/**
 * Created with IntelliJ IDEA.
 * User: dmitriy.bilyk
 * Date: 09.04.13
 * Time: 23:26
 * To change this template use File | Settings | File Templates.
 */
public class ApacheMain {
    public static void main(String[] args) {
//        System.out.println(StringUtils.EMPTY.isEmpty());
        System.out.println(StringUtils.center("Word", 4, "or"));
        safeDoStuffWithString3("MyStringValue");
        safeDoStuffWithString3(null);
//        System.out.println("IsBlank() =  " + StringUtils.isBlank("   "));
        System.out.println("IsEmpty() =  "+StringUtils.isEmpty("   "));
        split();
        utilsJoin();
        indexOf();
        substringtest();
    }

    public static void safeDoStuffWithString3(String stringParam) {
        if(StringUtils.equals(stringParam,"MyStringValue")) {
            System.out.println("equals");
    }  else{
            System.out.println("not equals");
        }
}
    public static void split() {
        String contrivedExampleString = "one.two.three.four";
        String[] result = contrivedExampleString.split("\\.");
        System.out.println(result.length); // 0
    }

    public static void utilsJoin(){
        String[] numbers = {"one", "two", "three"};
        System.out.println(StringUtils.join(numbers, ","));  // returns "one,two,three"
    }

    public static void indexOf(){
//        System.out.println(StringUtils.indexOf("ValueForIndex", 'l'));
    }

    public static void substringtest(){
//        System.out.println(StringUtils.substring("ValueForIndex", 3));
//        System.out.println(StringUtils.right("ValueForIndex", 3));
//        System.out.println(StringUtils.substringAfterLast("ValueForIndex", "o"));
    }

}

//        IsEmpty/IsBlank - checks if a String contains text
//        Trim/Strip - removes leading and trailing whitespace
//        Equals - compares two strings null-safe
//        startsWith - check if a String starts with a prefix null-safe
//        endsWith - check if a String ends with a suffix null-safe
//        IndexOf/LastIndexOf/Contains - null-safe index-of checks
//        IndexOfAny/LastIndexOfAny/IndexOfAnyBut/LastIndexOfAnyBut - index-of any of a set of Strings
//        ContainsOnly/ContainsNone/ContainsAny - does String contains only/none/any of these characters
//        Substring/Left/Right/Mid - null-safe substring extractions
//        SubstringBefore/SubstringAfter/SubstringBetween - substring extraction relative to other strings
//        Split/Join - splits a String into an array of substrings and vice versa
//        Remove/Delete - removes part of a String
//        Replace/Overlay - Searches a String and replaces one String with another
//        Chomp/Chop - removes the last part of a String
//        LeftPad/RightPad/Center/Repeat - pads a String
//        UpperCase/LowerCase/SwapCase/Capitalize/Uncapitalize - changes the case of a String
//        CountMatches - counts the number of occurrences of one String in another
//        IsAlpha/IsNumeric/IsWhitespace/IsAsciiPrintable - checks the characters in a String
//        DefaultString - protects against a null input String
//        Reverse/ReverseDelimited - reverses a String
//        Abbreviate - abbreviates a string using ellipsis
//        Difference - compares Strings and reports on their differences
//        LevenshteinDistance - the number of changes needed to change one String into another
