package com.learn.testing.complextest.model;

import javax.persistence.Entity;

/**
 * Created with IntelliJ IDEA.
 * User: Buh
 * Date: 30.08.13
 * Time: 12:20
 * To change this template use File | Settings | File Templates.
 */

@Entity
public class TestedObject {
    private String name;
    private int age;

    public TestedObject() {
    }

    public TestedObject(String name, int age) {
        this.name = name;
        this.age = age;
    }

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
}
