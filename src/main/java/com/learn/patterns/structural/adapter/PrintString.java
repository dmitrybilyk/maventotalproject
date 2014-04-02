package com.learn.patterns.structural.adapter;

/**
 * Created with IntelliJ IDEA.
 * User: dmitriy.bilyk
 * Date: 12.11.13
 * Time: 20:18
 * To change this template use File | Settings | File Templates.
 */
//Adaptee. Class, functionality of which we want to enhance.
public class PrintString {

    public void print(String s)
    {
        System.out.println(s);
    }
}
