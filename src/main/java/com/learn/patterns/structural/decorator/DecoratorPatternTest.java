package com.learn.patterns.structural.decorator;

/**
 * Created with IntelliJ IDEA.
 * User: dmitriy.bilyk
 * Date: 12.11.13
 * Time: 21:39
 * To change this template use File | Settings | File Templates.
 */

//Decorator design pattern is used to modify the functionality of an object
// at runtime. At the same time other instances of the same class will not
// be affected by this, so individual object gets the modified behavior.
//Notice that client program can create different kinds of Object at
// runtime and they can specify the order of execution too.
public class DecoratorPatternTest {

    public static void main(String[] args) {
        Car sportsCar = new SportsCar(new BasicCar());
        sportsCar.assemble();
        System.out.println("\n*****");

        Car sportsLuxuryCar = new SportsCar(new LuxuryCar(new BasicCar()));
        sportsLuxuryCar.assemble();
    }
//The disadvantage of decorator pattern is that it uses a lot of similar
// kind of objects (decorators).
}
