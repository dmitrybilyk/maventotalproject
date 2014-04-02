package com.learn.patterns.structural.decorator.decoratordesignpattern;

/**
 * Created with IntelliJ IDEA.
 * User: dmitriy.bilyk
 * Date: 13.04.13
 * Time: 20:19
 * To change this template use File | Settings | File Templates.
 */

//it is a Component in decorator design pattern

public abstract class Currency {
    String description = "Unknown currency";

    public String getCurrencyDescription() {
        return description;
    }

    public abstract double cost(double value);

}

