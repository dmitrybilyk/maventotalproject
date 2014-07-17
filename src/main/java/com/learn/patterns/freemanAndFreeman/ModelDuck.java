package com.learn.patterns.freemanAndFreeman;

/**
 * Created by dmitry on 7/17/14.
 */
public class ModelDuck extends Duck {

    public ModelDuck() {
        quackBehavior = new Quack();
        flyBehavior = new FlyNoWay();
    }

    @Override
    public void display() {
        System.out.println("I'm the model duck!");
    }
}
