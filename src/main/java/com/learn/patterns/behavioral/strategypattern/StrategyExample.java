package com.learn.patterns.behavioral.strategypattern;

/**
 * Created with IntelliJ IDEA.
 * User: dmitriy.bilyk
 * Date: 13.04.13
 * Time: 21:09
 * To change this template use File | Settings | File Templates.
 */
public class StrategyExample {
    public static void main(String[] args) {

        Context context;

        context = new Context(new ConcreteStrategyAdd());
        int resultA = context.executeStrategy(3,4);

        context = new Context(new ConcreteStrategySubtract());
        int resultB = context.executeStrategy(3,4);

        context = new Context(new ConcreteStrategyMultiply());
        int resultC = context.executeStrategy(3,4);
        System.out.println(resultA);
        System.out.println(resultB);
        System.out.println(resultC);
    }
}
