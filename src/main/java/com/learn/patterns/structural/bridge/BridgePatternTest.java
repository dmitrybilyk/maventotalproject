package com.learn.patterns.structural.bridge;

public class BridgePatternTest {

//    Decouple an abstraction from its implementation so that the two can vary independently
//    The implementation of bridge design pattern follows the notion to prefer Composition over inheritance.
    public static void main(String[] args) {
        Shape tri = new Triangle(new RedColor());
        tri.applyColor();

        Shape pent = new Pentagon(new GreenColor());
        pent.applyColor();
    }

}