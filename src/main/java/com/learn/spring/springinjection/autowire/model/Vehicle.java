package com.learn.spring.springinjection.autowire.model;

/**
 * Created with IntelliJ IDEA.
 * User: Buh
 * Date: 30.08.13
 * Time: 8:27
 * To change this template use File | Settings | File Templates.
 */
public class Vehicle {
    private String name;

    protected void move(){
        System.out.println("I'm driving the vehicle");
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
