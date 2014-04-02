package com.learn.bases.overrover.overloading;

/**
 * Created with IntelliJ IDEA.
 * User: flash
 * Date: 20.08.12
 * Time: 20:44
 * To change this template use File | Settings | File Templates.
 */
public class SuperClass {
    public SuperClass() {
    }

    public String printForOverride(String s, int i){
        System.out.println("From original "+s+ " " +i);
        return "super";
    }
}
