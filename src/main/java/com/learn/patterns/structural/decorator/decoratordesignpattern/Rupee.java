package com.learn.patterns.structural.decorator.decoratordesignpattern;

/**
 * Created with IntelliJ IDEA.
 * User: dmitriy.bilyk
 * Date: 13.04.13
 * Time: 20:20
 * To change this template use File | Settings | File Templates.
 */
// concrete component

public class Rupee extends Currency {
    double value;

    public Rupee() {
        description = "indian rupees";
    }

    public double cost(double v){
        value=v;
        return value;
    }
}

