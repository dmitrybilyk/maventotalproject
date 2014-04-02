package com.learn.patterns.creational.factory.factorymethod;

/**
 * Created with IntelliJ IDEA.
 * User: dmitriy.bilyk
 * Date: 12.04.13
 * Time: 23:56
 * To change this template use File | Settings | File Templates.
 */
public class FactoryMain {
    public static void main(String[] args) {
        String country = "India";
        Currency rupee = CurrencyFactory.createCurrency(country);
        System.out.println(rupee.getSymbol());
    }
}
