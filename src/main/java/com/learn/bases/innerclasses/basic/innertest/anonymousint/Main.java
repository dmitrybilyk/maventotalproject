package com.learn.bases.innerclasses.basic.innertest.anonymousint;

/**
 * Created with IntelliJ IDEA.
 * User: Buh
 * Date: 18.08.12
 * Time: 19:08
 * To change this template use File | Settings | File Templates.
 */
public class Main {
    public static void main(String[] args){
        IntForAnonymous intForAnonymous = new IntForAnonymous() {
            @Override
            public void print() {
                System.out.println("fdasfdsf");
            }
        };
        intForAnonymous.print();
    }
}
