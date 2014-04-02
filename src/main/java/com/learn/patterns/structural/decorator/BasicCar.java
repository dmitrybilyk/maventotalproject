package com.learn.patterns.structural.decorator;

/**
 * Created with IntelliJ IDEA.
 * User: dmitriy.bilyk
 * Date: 12.11.13
 * Time: 21:35
 * To change this template use File | Settings | File Templates.
 */
//Component Implementation – The basic implementation of the component
// interface.
public class BasicCar implements Car {

    @Override
    public void assemble() {
        System.out.print("Basic Car.");
    }

}
