package com.learn.patterns.creational.singleton.gupta.singletonreadresolve;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: dmitriy.bilyk
 * Date: 03.11.13
 * Time: 18:48
 * To change this template use File | Settings | File Templates.
 */
public class DemoSingleton implements Serializable {
//It will prevent the compiler to throw the exception by telling that both
// classes are same, and will load the available instance variables only.
    private static final long serialVersionUID = 1L;


    private volatile static DemoSingleton instance = null;

    public static DemoSingleton getInstance() {
        if (instance == null) {
            instance = new DemoSingleton();
        }
        return instance;
    }

//    So, again we are in same problem of multiple instances in application.
//To solve this issue, we need to include readResolve() method in our
// DemoSingleton class. This method will be invoked when you will de-serialize
// the object. Inside this method, you must return the existing instance to
// ensure single instance application wide.
    protected Object readResolve() {
        return instance;
    }

    private int i = 10;

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }
}
