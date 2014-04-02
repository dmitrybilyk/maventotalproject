package com.learn.bases.genericstest.tutorial1;

/**
 * Created with IntelliJ IDEA.
 * User: dmitriy.bilyk
 * Date: 10.04.13
 * Time: 21:30
 * To change this template use File | Settings | File Templates.
 */
public class GenericClass<T> {
    T ob;

    GenericClass(T o) {
        ob = o;
    }

    T getob() {
        return ob;
    }

    void showType() {
        System.out.println("Type of T is " + ob.getClass().getName());
    }
}
