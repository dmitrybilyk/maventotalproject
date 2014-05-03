package com.learn.bases.polimorthism.overriding;

import com.learn.bases.polimorthism.overriding.returnclass.FirstChildReturn22;

/**
 * Created with IntelliJ IDEA.
 * User: dmitriy.bilyk
 * Date: 12.04.13
 * Time: 22:54
 * To change this template use File | Settings | File Templates.
 */
public class ParentClass2 {
    public void printSomething(){
        System.out.println("Printing something in parent class");
    }

    public FirstChildReturn22 testReturn(String s, int i) throws Exception{
        return new FirstChildReturn22();
    }
}
