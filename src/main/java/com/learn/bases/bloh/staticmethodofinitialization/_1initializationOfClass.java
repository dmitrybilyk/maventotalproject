package com.learn.bases.bloh.staticmethodofinitialization;

/**
 * Created with IntelliJ IDEA.
 * User: flash
 * Date: 08.12.12
 * Time: 21:07
 * To change this template use File | Settings | File Templates.
 */

//Consider static inizialization methods instead of constractors

// advantages:
//    - they have names
//    - they are not required to create object each time they are invoked
//    - they may return any subtype of return type
//    - they reduce verbocity of creating paraterized type instances


//Static initialization method is more flexible. We can add some functionallity to the object.
//It says much more then constructor.
    //There are some naming convetions for these methods:
//    - valueOf - returns the copy with the same value as parameter;
//    - getInstance - returns the element that initialized with parameters;

public class _1initializationOfClass {
    public static void main(String[] args) {

            boolean b = true;
            Boolean b2 = Boolean.valueOf(b);
            System.out.println(b2);

        Parent toInitiolize = Parent.getInstance();
//        toInitiolize.setTest("test");
        System.out.println(toInitiolize.getMyParent());
    }
}

class Parent{
    private String myParent;

    static Parent getInstance(){
        Parent toInitiolize = new ToInitiolize();
        toInitiolize.setMyParent("test2");
        return toInitiolize;
    }
    public String getMyParent() {
        return myParent;
    }

    public void setMyParent(String myParent) {
        this.myParent = myParent;
    }
}

class ToInitiolize extends Parent{
    private String test;

    static ToInitiolize getInstance(){
        ToInitiolize toInitiolize = new ToInitiolize();
        toInitiolize.setTest("test2");
        return toInitiolize;
    }

    public String getTest() {
        return test;
    }

    public void setTest(String test) {
        this.test = test;
    }

    ToInitiolize() {

    }
}
