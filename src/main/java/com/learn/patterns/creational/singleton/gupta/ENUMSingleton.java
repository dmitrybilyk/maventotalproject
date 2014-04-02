package com.learn.patterns.creational.singleton.gupta;

/**
 * Created with IntelliJ IDEA.
 * User: dmitriy.bilyk
 * Date: 03.11.13
 * Time: 12:48
 * To change this template use File | Settings | File Templates.
 */
public enum ENUMSingleton {
    INSTANCE;
    private String name;
    public void someMethod(String param) {
        this.name = param;// some class member
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}