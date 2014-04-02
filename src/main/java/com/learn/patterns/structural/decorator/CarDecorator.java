package com.learn.patterns.structural.decorator;

/**
 * Created with IntelliJ IDEA.
 * User: dmitriy.bilyk
 * Date: 12.11.13
 * Time: 21:37
 * To change this template use File | Settings | File Templates.
 */
//Decorator – Decorator class implements the component interface and it
// has a HAS-A relationship with the component interface.
// The component variable should be accessible to the child decorator classes,
// so we will make this variable protected.
public class CarDecorator implements Car {

    protected Car car;

    public CarDecorator(Car c){
        this.car=c;
    }

    @Override
    public void assemble() {
        this.car.assemble();
    }

}