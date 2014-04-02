package com.learn.bases.reflection.tutorial;

/**
 * Created with IntelliJ IDEA.
 * User: dmitriy.bilyk
 * Date: 15.10.13
 * Time: 21:18
 * To change this template use File | Settings | File Templates.
 */
public class User {
    private int age;
    private String name;

    public User(int age, String name) {
        this.age = age;
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
