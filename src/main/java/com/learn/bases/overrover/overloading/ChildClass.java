package com.learn.bases.overrover.overloading;

/**
 * Created with IntelliJ IDEA.
 * User: flash
 * Date: 20.08.12
 * Time: 20:45
 * To change this template use File | Settings | File Templates.
 */
public class ChildClass extends SuperClass {

    public String printForOverride( int i) throws RuntimeException{
        System.out.println("From child "+i);
        super.printForOverride("super", 30);
        return "child";
    }


}
