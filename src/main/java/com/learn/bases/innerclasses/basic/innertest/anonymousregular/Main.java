package com.learn.bases.innerclasses.basic.innertest.anonymousregular;

/**
 * Created with IntelliJ IDEA.
 * User: Buh
 * Date: 18.08.12
 * Time: 19:09
 * To change this template use File | Settings | File Templates.
 */
public class Main {
    public static void main(String[] args){
        ClassForAnonymous classForAnonymous = new ClassForAnonymous(){
            public void print(){
                 System.out.println("child print");
            }
        };
        classForAnonymous.print();
    }
}
