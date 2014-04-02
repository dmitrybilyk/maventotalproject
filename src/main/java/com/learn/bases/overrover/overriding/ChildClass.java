package com.learn.bases.overrover.overriding;

/**
 * Created with IntelliJ IDEA.
 * User: flash
 * Date: 20.08.12
 * Time: 20:45
 * To change this template use File | Settings | File Templates.
 */
public class ChildClass extends SuperClass{

    public ChildClass() {
    }

    public String printForOverride(String s, int i) throws RuntimeException{
        System.out.println("From child "+s+" "+i);
        super.printForOverride("super", 30);
        return "child";
    }


}
