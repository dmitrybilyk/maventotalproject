package com.learn.patterns.creational.builder.myown;

/**
 * Created with IntelliJ IDEA.
 * User: dmitriy.bilyk
 * Date: 03.11.13
 * Time: 10:41
 * To change this template use File | Settings | File Templates.
 */
public class MyOwnTest {
    public static void main(String[] args) {
        Computer computer = new Computer.ComputerBuilder("Pentium")
                .forSale(true)
                .weight("huge")
                .build();
        System.out.println(computer.getModel());
        System.out.println(computer.getWeight());
        System.out.println(computer.isForSale());
    }
}
