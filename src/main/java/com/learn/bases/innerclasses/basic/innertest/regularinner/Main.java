package com.learn.bases.innerclasses.basic.innertest.regularinner;

/**
 * Created with IntelliJ IDEA.
 * User: Buh
 * Date: 18.08.12
 * Time: 18:46
 * To change this template use File | Settings | File Templates.
 */
//The regular inner class is a full fledged member of the outer class, so it shares all the features of a member of a class.
// Like an Inner class has access to all the variable of the outer class including the private variables.
// There is a special way to instantiate an Inner class. For instantiating a regular Inner Class, you need to instantiate the Outer class first.

//        If you are invoking the regular inner class from inside the Outer class,
// then it could be instantiated like a regular class, like:-

//        InnerClass ic = new InnerClass();

public class Main {
    public static void main(String[] args){
        Outer.Inner inner1 = new Outer().new Inner();
        inner1.printOuterVariable();
    }
}
