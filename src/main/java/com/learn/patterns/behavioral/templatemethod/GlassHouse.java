package com.learn.patterns.behavioral.templatemethod;

/**
 * Created with IntelliJ IDEA.
 * User: dmitriy.bilyk
 * Date: 04.11.13
 * Time: 23:23
 * To change this template use File | Settings | File Templates.
 */
public class GlassHouse extends HouseTemplate {

    @Override
    public void buildWalls() {
        System.out.println("Building Glass Walls");
    }

    @Override
    public void buildPillars() {
        System.out.println("Building Pillars with glass coating");
    }


}
