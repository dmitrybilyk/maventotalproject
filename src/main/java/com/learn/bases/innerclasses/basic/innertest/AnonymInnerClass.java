package com.learn.bases.innerclasses.basic.innertest;

/**
 * Created by IntelliJ IDEA.
 * User: Flash
 * Date: 25.05.12
 * Time: 9:18
 * To change this template use File | Settings | File Templates.
 */
public class AnonymInnerClass {
    
    void print(){
        System.out.println("fuck");
    }
    
    AnonymInnerClass anonymInnerClass = new AnonymInnerClass(){
        void print(){
            System.out.println("you");
        }
    };
}
