package com.learn.patterns.structural.decorator.decoratordesignpattern;

/**
 * Created with IntelliJ IDEA.
 * User: dmitriy.bilyk
 * Date: 13.04.13
 * Time: 20:24
 * To change this template use File | Settings | File Templates.
 */
//Another Concrete Decorator

public class SGDDecorator extends Decorator{
    Currency currency;

    public SGDDecorator(Currency currency){
        this.currency = currency;
    }


    public String getCurrencyDescription(){
        return currency.getCurrencyDescription()+" ,its singapore Dollar";
    }

    @Override
    public double cost(double value) {
        return 330;
    }
}

