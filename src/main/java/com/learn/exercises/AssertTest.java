package com.learn.exercises;

/**
 * Created by dmitry on 8/13/14.
 */
public class AssertTest {
    public static boolean returnSomething(){
        System.out.println("print from return something");
        return false;
    }

    public static void print(){
        System.out.println("Print");
    }

    public static void main(String[] args) {
        AssertTest assertTest = new AssertTest();
        int x = 10;
        assert 5<x: assertTest.returnSomething();
        assert 5>x;
    }
}
