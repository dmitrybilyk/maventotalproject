package com.learn.patterns.creational.factory.factorymethod;

/**
 * Created with IntelliJ IDEA.
 * User: dmitriy.bilyk
 * Date: 12.04.13
 * Time: 23:51
 * To change this template use File | Settings | File Templates.
 */
class Rupee implements Currency {
    @Override
    public String getSymbol() {
        return "Rs";
    }
}


