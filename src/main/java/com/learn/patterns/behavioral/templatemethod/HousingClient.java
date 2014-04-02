package com.learn.patterns.behavioral.templatemethod;

/**
 * Created with IntelliJ IDEA.
 * User: dmitriy.bilyk
 * Date: 04.11.13
 * Time: 23:32
 * To change this template use File | Settings | File Templates.
 */
//Template method should consists of certain steps whose order is fixed and
// for some of the methods, implementation differs from base class to subclass.
// Template method should be final.
public class HousingClient {

    public static void main(String[] args) {

        HouseTemplate houseType = new WoodenHouse();

        //using template method
        houseType.buildHouse();
        System.out.println("************");

        houseType = new GlassHouse();

        houseType.buildHouse();
    }

}