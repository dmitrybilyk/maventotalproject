package com.learn.bases.testpoly;


import com.learn.bases.innerclasses.anotherpackage.AnotherClass;

/**
 * Created by IntelliJ IDEA.
 * User: Flash
 * Date: 05.05.12
 * Time: 16:09
 * To change this template use File | Settings | File Templates.
 */
public class ClassToCheckVisibility extends AnotherClass {
    public static void main(String[] args){
        ClassToCheckVisibility classToCheckVisibility = new ClassToCheckVisibility();
        classToCheckVisibility.printVars();
    }
    private void printVars(){
        AnotherClass anotherClass = new AnotherClass(3, 4, 5, 6);
        System.out.println(anotherClass.publicInt);
    }
}
