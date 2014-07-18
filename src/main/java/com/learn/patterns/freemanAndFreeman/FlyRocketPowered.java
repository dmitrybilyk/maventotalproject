package com.learn.patterns.freemanAndFreeman;

/**
 * Created by dmitry on 7/17/14.
 */
public class FlyRocketPowered implements FlyBehavior {
    @Override
    public void fly() {
        System.out.println("I'm flying with a rocket!");
    }
}
