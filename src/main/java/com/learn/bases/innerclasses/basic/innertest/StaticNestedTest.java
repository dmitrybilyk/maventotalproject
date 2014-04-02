package com.learn.bases.innerclasses.basic.innertest;

/**
 * Created by IntelliJ IDEA.
 * User: Flash
 * Date: 26.05.12
 * Time: 8:49
 * To change this template use File | Settings | File Templates.
 */
public class StaticNestedTest {
    static String a = "fadsf";
    static class Nested{
        void print(){
            System.out.println("in nested print"+a);
        }
    }
}
