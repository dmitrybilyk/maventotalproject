package com.learn.patterns.behavioral.interpreter;

/**
 * Created with IntelliJ IDEA.
 * User: dmitriy.bilyk
 * Date: 11.11.13
 * Time: 21:39
 * To change this template use File | Settings | File Templates.
 */
public class InterpreterContext {

    public String getBinaryFormat(int i){
        return Integer.toBinaryString(i);
    }

    public String getHexadecimalFormat(int i){
        return Integer.toHexString(i);
    }
}