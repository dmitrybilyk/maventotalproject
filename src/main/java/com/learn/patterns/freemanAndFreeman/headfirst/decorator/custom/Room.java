package com.learn.patterns.freemanAndFreeman.headfirst.decorator.custom;

/**
 * Created by dmitry on 7/23/14.
 */
public abstract class Room {
    private String name;
    public abstract double cost();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
