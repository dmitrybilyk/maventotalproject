package com.learn.patterns.creational.singleton.singleton;

/**
 * Created by IntelliJ IDEA.
 * User: bilyk
 * Date: 22.06.12
 * Time: 14:15
 * To change this template use File | Settings | File Templates.
 */
public class OneElseSingleton {
    private OneElseSingleton(){
    }

    private static class SingleToneHolder{
        private static OneElseSingleton INSTANCE = new OneElseSingleton();
    }

    public OneElseSingleton getInstance(){
        return SingleToneHolder.INSTANCE;
    }
}
