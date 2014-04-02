package com.learn.bases.polimorthism.overriding;

import com.learn.bases.polimorthism.overriding.returnclass.FirstChildReturn;

import java.io.FileNotFoundException;

/**
 * Created with IntelliJ IDEA.
 * User: dmitriy.bilyk
 * Date: 12.04.13
 * Time: 22:57
 * To change this template use File | Settings | File Templates.
 */
public class FirstChild extends ParentClass{
    public void printSomething() {
        System.out.println("Printing something in first");
    }

    @Override
    public FirstChildReturn testReturn(String s, int i) throws FileNotFoundException {
        return new FirstChildReturn();
    }

    public FirstChildReturn testReturn(String s, int i, int a){
        return new FirstChildReturn();
    }
}
