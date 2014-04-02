package com.learn.patterns.structural.composite;

/**
 * Created with IntelliJ IDEA.
 * User: dmitriy.bilyk
 * Date: 12.11.13
 * Time: 22:09
 * To change this template use File | Settings | File Templates.
 */
//Leaf Object. Leaf implements base component and these are the building
// block for the composite. We can create multiple leaf objects such as
// Triangle, Circle etc.
public class Triangle implements Shape {

    @Override
    public void draw(String fillColor) {
        System.out.println("Drawing Triangle with color "+fillColor);
    }

}