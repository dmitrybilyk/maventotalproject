package com.learn.patterns.structural.adapter;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: dmitriy.bilyk
 * Date: 12.11.13
 * Time: 20:20
 * To change this template use File | Settings | File Templates.
 */
public class AdapterDesignPatternMain {

    public static void main(String[] args)
    {
        ArrayList<String> list=new  ArrayList<String>();
        list.add("one");
        list.add("two");
        list.add("three");
        PrintableList pl=new PrintableListAdapter();
        pl.printList(list);

    }
}
