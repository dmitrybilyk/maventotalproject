package com.learn.bases.polimorthism.overriding;

/**
 * Created with IntelliJ IDEA.
 * User: dmitriy.bilyk
 * Date: 12.04.13
 * Time: 22:59
 * To change this template use File | Settings | File Templates.
 */
public class ThirdChild extends ParentClass2 {
    @Override
    public void printSomething() {
        System.out.println("Printing something in first");
    }
}
