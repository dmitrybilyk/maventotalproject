package com.learn.patterns.behavioral.strategypattern;

/**
 * Created with IntelliJ IDEA.
 * User: dmitriy.bilyk
 * Date: 13.04.13
 * Time: 21:09
 * To change this template use File | Settings | File Templates.
 */
// Класс контекста использующий интерфейс стратегии
class Context {

    private Strategy strategy;

    // Constructor
    public Context(Strategy strategy) {
        this.strategy = strategy;
    }

    public int executeStrategy(int a, int b) {
        return strategy.execute(a, b);
    }
}
