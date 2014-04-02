package com.learn.bases.innerclasses.basic.innertest.staticinner;

/**
 * Created with IntelliJ IDEA.
 * User: Buh
 * Date: 18.08.12
 * Time: 19:01
 * To change this template use File | Settings | File Templates.
 */
//The static Inner Nested Classes are nothing else, but are like Regular Inner class, but are marked static.
// So the behavior of these classes is static in nature. This explains most of the behavior of the static inner class.
// Like it behaves like a class level variable. I mean to say that, you need not instantiate the outer class for creating
// an instance of the static inner class, and that the static inner class can be directly instantiated.

//        Moreover as the behavior of a static member is, the static inner class cannot access the non static members of the class,
// this is in sync with the basic properties of a static variable or member of a class. For instantiating a Static Inner Class,
// you need to use the name of both the Outer class and Inner class. For example:-

//        OuterClass.InnerClass ocic = new OuterClass.InnerClass();

public class Main {
    public static void main(String[] args){
        StaticInnerExample.InnerStatic innerStatic = new StaticInnerExample.InnerStatic();
        innerStatic.print();
    }
}
