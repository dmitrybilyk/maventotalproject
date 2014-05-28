package com.learn.bases.appacheutils.digestutils;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * Created by bid on 5/28/14.
 */
public class Main {
    public static void main(String[] args) {
        String codedString = DigestUtils.shaHex("SimpleString");
        String codedString2 = DigestUtils.shaHex("SimpleString");
        System.out.println(codedString);
        System.out.println(codedString.equals(codedString2));
    }
}
