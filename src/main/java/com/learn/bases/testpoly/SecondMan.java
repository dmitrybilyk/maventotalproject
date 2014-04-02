package com.learn.bases.testpoly;

/**
 * Created by IntelliJ IDEA.
 * User: Flash
 * Date: 05.05.12
 * Time: 15:58
 * To change this template use File | Settings | File Templates.
 */
public class SecondMan extends SuperClass implements InterfaceMan{
    public SecondMan(){
        super("SuperMan", 100);
    }

    public void printInfo(){
        System.out.println("Info of Seconf Man here");
        System.out.println("and age of superMan "+age);
    }
}
