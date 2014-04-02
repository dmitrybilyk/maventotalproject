package com.learn.bases.polimorthism.overriding;

/**
 * Created with IntelliJ IDEA.
 * User: dmitriy.bilyk
 * Date: 12.04.13
 * Time: 23:01
 * To change this template use File | Settings | File Templates.
 */
public class OverridingTest {

    public static void main(String[] args) {
        ParentClass entity = new SecondChild();
        entity.printSomething();
    }

}
