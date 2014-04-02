package com.learn.bases.innerclasses.basic.innertest.regularinner;

/**
 * Created with IntelliJ IDEA.
 * User: Buh
 * Date: 18.08.12
 * Time: 18:45
 * To change this template use File | Settings | File Templates.
 */
public class Outer {
    private String name;
    class Inner {
        public void printOuterVariable(){
            System.out.println(name);
        }
    }
}
