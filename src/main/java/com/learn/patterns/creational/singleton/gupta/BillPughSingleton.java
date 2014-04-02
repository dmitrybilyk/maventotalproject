package com.learn.patterns.creational.singleton.gupta;

/**
 * Created with IntelliJ IDEA.
 * User: dmitriy.bilyk
 * Date: 03.11.13
 * Time: 12:44
 * To change this template use File | Settings | File Templates.
 */
public class BillPughSingleton {
    private BillPughSingleton() {
    }

    private static class LazyHolder {
        private static final BillPughSingleton INSTANCE = new BillPughSingleton();
    }

    public static BillPughSingleton getInstance() {
        return LazyHolder.INSTANCE;
    }
}

//As you can see, until we need an instance, the LazyHolder class will not be
// initialized until required and you can still use other static members of
// BillPughSingleton class. This is the solution, i will recommend to use.
// I also use it in my all projects.
