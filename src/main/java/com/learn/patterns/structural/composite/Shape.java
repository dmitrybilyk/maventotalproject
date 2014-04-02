package com.learn.patterns.structural.composite;

/**
 * Created with IntelliJ IDEA.
 * User: dmitriy.bilyk
 * Date: 12.11.13
 * Time: 22:09
 * To change this template use File | Settings | File Templates.
 */
//Base Component – Base component is the interface for all objects in
// the composition, client program uses base component to work with
// the objects in the composition. It can be an interface or an abstract
// class with some methods common to all the objects.
public interface Shape {

    public void draw(String fillColor);
}
