package com.learn.bases.innerclasses.basic.innertest;

/**
 * Created by IntelliJ IDEA.
 * User: Flash
 * Date: 26.05.12
 * Time: 8:49
 * To change this template use File | Settings | File Templates.
 */
public class MainForNested {
    public static void main(String[] args){
        StaticNestedTest.Nested nested = new StaticNestedTest.Nested();
        nested.print();
    }
}
