package com.learn.bases.collections.map;

/**
 * Created by dmitry on 1/27/14.
 */
public class Soldier implements Comparable{
    private String name;
    private int age;

    public Soldier(String name, int age) {
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
    public int compareTo(Object o) {
        return 0;
//        Soldier anotherSoldier = (Soldier)o;
//        return this.name.equals(anotherSoldier.getName())? 1: 0 ;
    }
}
