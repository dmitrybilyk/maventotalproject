package com.learn.bases.genericstest.tutorial1;

/**
 * Created with IntelliJ IDEA.
 * User: dmitriy.bilyk
 * Date: 10.04.13
 * Time: 21:32
 * To change this template use File | Settings | File Templates.
 */
public class GenericClassMainClass {
    public static void main(String args[]) {
        // Create a Gen reference for Integers.
        GenericClass<Integer> iOb = new GenericClass<Integer>(88);
        iOb.showType();

        // no cast is needed.
        int v = iOb.getob();
        System.out.println("value: " + v);

        // Create a Gen object for Strings.
        GenericClass<String> strOb = new GenericClass<String>("Generics Test");
        strOb.showType();

        String str = strOb.getob();
        System.out.println("value: " + str);
    }
}