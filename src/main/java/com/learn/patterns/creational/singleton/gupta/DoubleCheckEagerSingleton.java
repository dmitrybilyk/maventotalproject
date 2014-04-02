package com.learn.patterns.creational.singleton.gupta;

/**
 * Created with IntelliJ IDEA.
 * User: dmitriy.bilyk
 * Date: 03.11.13
 * Time: 12:21
 * To change this template use File | Settings | File Templates.
 */

//Please ensure to use “volatile” keyword with instance variable otherwise
// you can run into out of order write error scenario, where reference of
// instance is returned before actually the object is constructed i.e.
// JVM has only allocated the memory and constructor code is still not executed.
// In this case, your other thread, which refer to uninitialized object may
// throw null pointer exception and can even crash the whole application.

public class DoubleCheckEagerSingleton {
    private static volatile DoubleCheckEagerSingleton instance = null;

    // private constructor
    private DoubleCheckEagerSingleton() {
    }

    public static DoubleCheckEagerSingleton getInstance() {
        if (instance == null) {
            synchronized (EagerSingleton.class) {
                // Double check
                if (instance == null) {
                    instance = new DoubleCheckEagerSingleton();
                }
            }
        }
        return instance;
    }
}