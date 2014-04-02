package com.learn.bases.bloh;

/**
 * Created with IntelliJ IDEA.
 * User: flash
 * Date: 12.12.12
 * Time: 8:49
 * To change this template use File | Settings | File Templates.
 */
public class _CloneMethod {
    //never use cloneable on child objects.
    // You may use it for copying objects that contais only arrays or classes that single.
    //In order to make Object's clonable appropriate for cloning objects of you class you should
    //imlement Clonable interface.
    //the problems may occur when objects contain complicated objects.

}


class TestClone implements Cloneable {
    int a;
    double b;
    // clone() is now overridden and is public.
    public Object clone() {
        try {
// call clone in Object.
            return super.clone();
        } catch(CloneNotSupportedException e) {
            System.out.println("Cloning not allowed.");
            return this;
        }
    }
}

class CloneDemo2 {
    public static void main(String args[]) {
        TestClone x1 = new TestClone();
        TestClone x2;
        x1.a = 10;
        x1.b = 20.98;
// here, clone() is called directly.
        x2 = (TestClone) x1.clone();
        System.out.println("x1: " + x1.a + " " + x1.b);
        System.out.println("x2: " + x2.a + " " + x2.b);
    }
}