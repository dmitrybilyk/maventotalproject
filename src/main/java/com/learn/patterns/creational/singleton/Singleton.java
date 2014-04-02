package com.learn.patterns.creational.singleton;

/**
 * Created by IntelliJ IDEA.
 * User: bilyk
 * Date: 22.06.12
 * Time: 14:08
 * To change this template use File | Settings | File Templates.
 */
public class Singleton {
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String name;
    private Singleton(){

    }

    private static class SingletonHolder{
        private static final Singleton INSTANCE = new Singleton();
    }

    public static Singleton getInstance(){
        return SingletonHolder.INSTANCE;
    }
}
