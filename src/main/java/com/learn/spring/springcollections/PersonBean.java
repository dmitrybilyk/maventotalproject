package com.learn.spring.springcollections;

/**
 * Created with IntelliJ IDEA.
 * User: dmitriy.bilyk
 * Date: 18.03.13
 * Time: 21:04
 * To change this template use File | Settings | File Templates.
 */
public class PersonBean {
    private String name;
    private int age;
    private String hearColor;
    private String address;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getHearColor() {
        return hearColor;
    }

    public void setHearColor(String hearColor) {
        this.hearColor = hearColor;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress() {
        return address;
    }
}
