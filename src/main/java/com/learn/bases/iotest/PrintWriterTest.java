package com.learn.bases.iotest;

import java.io.PrintWriter;

/**
 * Created by IntelliJ IDEA.
 * User: Flash
 * Date: 26.05.12
 * Time: 14:27
 * To change this template use File | Settings | File Templates.
 */
public class PrintWriterTest {
    public static void main(String[] args){
        PrintWriter printWriter = new PrintWriter(System.out, true);
        String s = "sss";
        printWriter.println(s);
    }
}
