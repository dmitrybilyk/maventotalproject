package com.learn.bases.compare;

/**
 * Created by IntelliJ IDEA.
 * User: bilyk
 * Date: 07.05.12
 * Time: 10:40
 * To change this template use File | Settings | File Templates.
 */
public class Human {
    private int height;
    private String surName;

    public Human(int i, String surName) {
        this.height = i;
        this.surName = surName;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getSurName() {
        return surName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }
}
