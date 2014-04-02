package com.learn.bases.innerclasses.basic.innertest.methodlocal;

/**
 * Created with IntelliJ IDEA.
 * User: Buh
 * Date: 18.08.12
 * Time: 18:55
 * To change this template use File | Settings | File Templates.
 */
//The Method Local Inner class, as the name specifies is local to a method. It implies that the method local
// Inner class is defined inside a method of a class. As the class is defined inside a method, it needs to be
// instantiated from with in the same method, but after the class definition is finished. As a mater of fact
// this inner class cannot use the variables of the same method, unless they are declared final.

public class Main {
    public static void main(String[] args){
        MethodExample methodExample = new MethodExample();
        methodExample.print();
    }
}
