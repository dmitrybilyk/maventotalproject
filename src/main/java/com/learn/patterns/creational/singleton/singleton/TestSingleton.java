package com.learn.patterns.creational.singleton.singleton;

/**
 * Created by IntelliJ IDEA.
 * User: bilyk
 * Date: 22.06.12
 * Time: 14:08
 * To change this template use File | Settings | File Templates.
 */

//Паттерн Singleton гарантирует, что у класса есть только один экземпляр, и предоставляет к нему глобальную точку доступа.
public class TestSingleton {
    public static void main(String [] args){
        Singleton singleton = Singleton.getInstance();
        singleton.setName("ffff");
        Singleton singleton1 = Singleton.getInstance();
        System.out.println(singleton1.getName());
        System.out.println(singleton1==singleton);
    }

}
