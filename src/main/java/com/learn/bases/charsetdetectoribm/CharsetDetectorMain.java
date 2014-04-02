package com.learn.bases.charsetdetectoribm;

import com.ibm.icu.text.CharsetDetector;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: dmitriy.bilyk
 * Date: 20.03.13
 * Time: 9:06
 * To change this template use File | Settings | File Templates.
 */
public class CharsetDetectorMain {
    public static void main(String[] args) {
        CharsetDetector charsetDetector = new CharsetDetector();
        File file = new File("e:/shouldBeAnsi.txt");
        try {
            BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(file));
            charsetDetector.setText(bufferedInputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String encoding = charsetDetector.detect().getName();
        System.out.println(encoding);
    }
}
