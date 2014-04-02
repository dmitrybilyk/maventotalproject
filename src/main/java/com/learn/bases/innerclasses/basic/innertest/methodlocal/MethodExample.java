package com.learn.bases.innerclasses.basic.innertest.methodlocal;

/**
 * Created with IntelliJ IDEA.
 * User: Buh
 * Date: 18.08.12
 * Time: 18:53
 * To change this template use File | Settings | File Templates.
 */
public class MethodExample {
    private String name = "name";
    public void print(){
        class MethodLocalMethod{
            public void printSomeThing(){
                System.out.println(name);
            }
        }
        MethodLocalMethod methodLocalMethod = new MethodLocalMethod();
        methodLocalMethod.printSomeThing();
    }
}
