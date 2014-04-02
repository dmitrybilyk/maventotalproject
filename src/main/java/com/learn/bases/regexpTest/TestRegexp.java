package com.learn.bases.regexpTest;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by IntelliJ IDEA.
 * User: Flash
 * Date: 27.05.12
 * Time: 22:28
 * To change this template use File | Settings | File Templates.
 */
public class TestRegexp {
    public static void main(String[] args){
        Pattern p = Pattern.compile("[0-9a-zA-Z\\p{L}&apos;\\s\\-\\,\\.\\(\\)/\\?\\:\\+]*$");
        Matcher m = p.matcher("beneficiÙÙary 4");
        boolean b = m.matches();
        System.out.println(b);
    }
}
