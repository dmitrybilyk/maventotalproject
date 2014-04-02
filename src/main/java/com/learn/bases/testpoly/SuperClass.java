package com.learn.bases.testpoly;

/**
 * Created by IntelliJ IDEA.
 * User: Flash
 * Date: 05.05.12
 * Time: 15:53
 * To change this template use File | Settings | File Templates.
 */
public class SuperClass{
    String name;
    int age;

    public SuperClass(){
        
    }
    public SuperClass(String name, int age) {
        this.name = name;
        this.age = age;
    }
    public void printInfo(){
        System.out.println("abstract info");
    }


    public void printAbstractInfo() {
        System.out.println("PrintAbstractInfoFromSuperClass");
    }
}
