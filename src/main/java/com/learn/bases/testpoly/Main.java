package com.learn.bases.testpoly;

/**
 * Created by IntelliJ IDEA.
 * User: Flash
 * Date: 05.05.12
 * Time: 15:55
 * To change this template use File | Settings | File Templates.
 */
public class Main {
    public static void main(String[] args){
        InterfaceMan oneMan = new SecondMan();
        oneMan.printInfo();
        oneMan.printAbstractInfo();
    }
}
