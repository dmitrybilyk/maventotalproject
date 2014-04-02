package com.learn.patterns.structural.decorator.decoratordesignpattern;

/**
 * Created with IntelliJ IDEA.
 * User: dmitriy.bilyk
 * Date: 13.04.13
 * Time: 20:25
 * To change this template use File | Settings | File Templates.
 */
public class CurrencyCheck {

    public static void main(String[] args) {

        // without adding decorators

        Currency curr = new Dollar();

        System.out.println(curr.getCurrencyDescription() +" dollar. "+curr.cost(2.0));





        //adding decorators

        Currency curr2 = new USDDecorator(new Dollar());

        System.out.println(curr2.getCurrencyDescription() +" dollar. "+curr2.cost(4.0));

        Currency curr3 = new SGDDecorator(new Dollar());

        System.out.println(curr3.getCurrencyDescription() +" dollar. "+curr3.cost(4.0));
    }
}

