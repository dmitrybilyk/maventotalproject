package com.learn.patterns.structural.decorator.decoratordesignpattern;

/**
 * Created with IntelliJ IDEA.
 * User: dmitriy.bilyk
 * Date: 13.04.13
 * Time: 20:23
 * To change this template use File | Settings | File Templates.
 */
// Concrete Decorator

public class USDDecorator extends Decorator{

    Currency currency;


    public USDDecorator(Currency currency){
        this.currency = currency;
    }


    public String getCurrencyDescription(){
        return currency.getCurrencyDescription()+" ,its US Dollar";
    }


    @Override
    public double cost(double value) {
        return 100;
    }
}

