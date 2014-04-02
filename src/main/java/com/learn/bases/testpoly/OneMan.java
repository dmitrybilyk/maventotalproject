package com.learn.bases.testpoly;

/**
 * Created by IntelliJ IDEA.
 * User: Flash
 * Date: 05.05.12
 * Time: 15:54
 * To change this template use File | Settings | File Templates.
 */
public class OneMan extends SuperClass implements InterfaceMan{
    int height;
    public OneMan(String name, int age, int height) {
        super(name, age);
        this.height = height;
    }

    public OneMan() {
        super("NameFromOneMan", 33);
    }

    public void printInfo(){
        System.out.println("Height is "+ age);
    }
}
