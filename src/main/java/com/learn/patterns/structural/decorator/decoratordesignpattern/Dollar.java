package com.learn.patterns.structural.decorator.decoratordesignpattern;

/**
 * Created with IntelliJ IDEA.
 * User: dmitriy.bilyk
 * Date: 13.04.13
 * Time: 20:21
 * To change this template use File | Settings | File Templates.
 */
//Another Concrete Component

public class Dollar extends Currency{
    double value;

    public Dollar () {
        description = "Dollar";
    }

    public double cost(double v){
        value=v;

        return value;

    }

}

