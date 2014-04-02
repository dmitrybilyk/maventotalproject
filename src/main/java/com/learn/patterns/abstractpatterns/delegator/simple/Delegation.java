package com.learn.patterns.abstractpatterns.delegator.simple;

/**
 * Created by IntelliJ IDEA.
 * User: bilyk
 * Date: 02.07.12
 * Time: 15:58
 * To change this template use File | Settings | File Templates.
 */
public class Delegation {
    public static void main(String[] args){
        C c = new C();
        c.f();
        c.g();
    }
}
