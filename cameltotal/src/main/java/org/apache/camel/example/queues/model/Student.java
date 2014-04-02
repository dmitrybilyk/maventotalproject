package org.apache.camel.example.queues.model;

import java.io.Serializable;

/**
 * Created by dmitry.bilyk on 3/12/14.
 */
public class Student implements Serializable{
    private String name;
    private int age;

    public Student(String name, int age) {
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

    @Override
    public String toString() {
        return this.getName();
    }
}
