package com.learn.patterns.behavioral.templatemethod;

/**
 * Created with IntelliJ IDEA.
 * User: dmitriy.bilyk
 * Date: 04.11.13
 * Time: 23:18
 * To change this template use File | Settings | File Templates.
 */

//Template method defines the steps to execute an algorithm and it can
// provide default implementation that might be common for all or some of
// the subclasses.
public abstract class HouseTemplate {

    //template method, final so subclasses can't override
    public final void buildHouse(){
        buildFoundation();
        buildPillars();
        buildWalls();
        buildWindows();
        System.out.println("House is built.");
    }

    //default implementation
    private void buildWindows() {
        System.out.println("Building Glass Windows");
    }

    //methods to be implemented by subclasses
    public abstract void buildWalls();
    public abstract void buildPillars();

    private final void buildFoundation() {
        System.out.println("Building foundation with cement,iron rods and sand");
    }
}