package com.learn.bases.overrover.overloading;

/**
 * Created with IntelliJ IDEA.
 * User: flash
 * Date: 20.08.12
 * Time: 20:56
 * To change this template use File | Settings | File Templates.
 */
//Overloading methods:
//-must have different argument list;
//- may have different return types as long as the argument lists is also different;
//- may have different access modifiers;
//- may throw different exceptions;
//Methods from superclass may be overloaded in the child class;


public class TestOverloading {
    public static void main(String[] args){
        ChildClass childClass = new ChildClass();
        childClass.printForOverride(30);
        childClass.printForOverride("test",30);
    }
}
