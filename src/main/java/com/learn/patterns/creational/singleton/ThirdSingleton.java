package com.learn.patterns.creational.singleton;

/**
 * Created by IntelliJ IDEA.
 * User: bilyk
 * Date: 22.06.12
 * Time: 14:15
 * To change this template use File | Settings | File Templates.
 */

//Решили проблему с ленивой инициализацией, но осталась проблема с обработкой ошибок в конструкторе
public class ThirdSingleton {
    private ThirdSingleton(){
    }

    private static class SingleToneHolder{
        private static ThirdSingleton INSTANCE = new ThirdSingleton();
    }

    public ThirdSingleton getInstance(){
        return SingleToneHolder.INSTANCE;
    }
}
