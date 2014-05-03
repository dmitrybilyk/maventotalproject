package com.learn.spring.springinaction.parentchild;

/**
 * Created by dmitry on 03.05.14.
 */
public class Parent1 {
    private String name;
//    private String description;

    public void printSomething(){
        System.out.println("printing from parent");
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

//    public String getDescription() {
//        return description;
//    }
//
//    public void setDescription(String description) {
//        this.description = description;
//    }
}
