package com.learn.patterns.creational.factory.factorymethod;

/**
 * Created with IntelliJ IDEA.
 * User: dmitriy.bilyk
 * Date: 12.04.13
 * Time: 23:56
 * To change this template use File | Settings | File Templates.
 */
class CurrencyFactory {

    public static Currency createCurrency (String country) {
        if (country. equalsIgnoreCase ("India")){
            return new Rupee();
        }else if(country. equalsIgnoreCase ("Singapore")){
            return new SGDDollar();
        }else if(country. equalsIgnoreCase ("US")){
            return new USDollar();
        }
        throw new IllegalArgumentException("No such currency");
    }
}


