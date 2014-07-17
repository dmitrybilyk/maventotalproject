package com.learn.patterns.freemanAndFreeman;

/**
 * Created by dmitry on 7/17/14.
 */
public class MallardDuck extends Duck {

    public MallardDuck() {
        quackBehavior = new Quack();
        flyBehavior = new FlyWithWins();
    }

    @Override
    public void display() {
        System.out.println("I'm the real mallard duck!");
    }
}
