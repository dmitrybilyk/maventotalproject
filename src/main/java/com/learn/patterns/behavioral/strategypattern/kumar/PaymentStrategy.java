package com.learn.patterns.behavioral.strategypattern.kumar;

/**
 * Created with IntelliJ IDEA.
 * User: dmitriy.bilyk
 * Date: 04.11.13
 * Time: 22:17
 * To change this template use File | Settings | File Templates.
 */
public interface PaymentStrategy {

    public void pay(int amount);
}