package com.learn.bases.genericstest.tutorial1;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: dmitriy.bilyk
 * Date: 10.04.13
 * Time: 21:50
 * To change this template use File | Settings | File Templates.
 */
public class NestedGenerics {
    public static void main(String[] args) {
        List<String> listOfStrings = new ArrayList<String>();
        listOfStrings.add("Hello again");
        List<List<String>> listOfLists = new ArrayList<List<String>>();
        listOfLists.add(listOfStrings);
        String s = listOfLists.get(0).get(0);
        System.out.println(s); // prints "Hello again"
    }
}
