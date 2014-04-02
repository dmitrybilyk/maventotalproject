package com.learn.patterns.behavioral.strategypattern.kumar;

/**
 * Created with IntelliJ IDEA.
 * User: dmitriy.bilyk
 * Date: 04.11.13
 * Time: 22:26
 * To change this template use File | Settings | File Templates.
 */
public class Item {

    private String upcCode;
    private int price;

    public Item(String upc, int cost){
        this.upcCode=upc;
        this.price=cost;
    }

    public String getUpcCode() {
        return upcCode;
    }

    public int getPrice() {
        return price;
    }

}
