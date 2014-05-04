package com.learn.spring.springinaction.parentchild;

/**
 * Created by dmitry on 03.05.14.
 */
public class Child1{

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String lastName;
    public void print(){
        System.out.println("print from child1");
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
