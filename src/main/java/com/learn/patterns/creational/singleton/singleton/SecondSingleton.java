package com.learn.patterns.creational.singleton.singleton;

/**
 * Created with IntelliJ IDEA.
 * User: Buh
 * Date: 18.08.12
 * Time: 13:53
 * To change this template use File | Settings | File Templates.
 */

// Не предоставляет возможность отлавливать исключения в конструкторе и не предоставляет возможность ленивой инициализации. Зато позволяет использовать
//    в многопоточном приложении
public class SecondSingleton {
    private static SecondSingleton secondSingleton = new SecondSingleton();
    private SecondSingleton(){
    }
    private static SecondSingleton getInstance(){
        return secondSingleton;
    }
}
