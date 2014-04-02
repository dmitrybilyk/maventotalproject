package com.learn.patterns.creational.singleton.singleton;

/**
 * Created with IntelliJ IDEA.
 * User: Buh
 * Date: 18.08.12
 * Time: 13:50
 * To change this template use File | Settings | File Templates.
 */
// Подходит только для однопоточных приложений

public class FirstSingleton {
    private static FirstSingleton instance;

    private FirstSingleton() {
    }

    public static FirstSingleton getInstance(){
        if(instance==null){
            return new FirstSingleton();
        }else{
            return instance;
        }
    }
}
