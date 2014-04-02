package com.learn.bases.innerclasses.basic.InnerClasses;

import org.apache.log4j.Logger;

/**
 * Created by IntelliJ IDEA.
 * User: bilyk
 * Date: 25.06.12
 * Time: 13:57
 * To change this template use File | Settings | File Templates.
 */
public class Outer {
    public static final Logger logger = Logger.getLogger(Outer.class.getName());
    private String name = "test";
    private static String staticName = "staticName";
    
    
    void printForAnonymInner(){
        System.out.println("Inner print");
    }
    
    class Inner{
        void printOuterName(){
            System.out.println(name);
        }
    }
    
    static class StaticInner{
        void staticPrintOuterName(){
            System.out.println(staticName);
        }
    }
    
    void testLocalInnerClass(){
        final int age = 10;
        class InnerLocalClass{
            void print(){
                System.out.println(age);
            }
        }
        InnerLocalClass innerLocalClass = new InnerLocalClass();
        innerLocalClass.print();
    }
    
    
    
    public static void main(String[] args){
        Outer outer = new Outer();
        Inner inner = outer.new Inner();
        
        Inner inner1 = new Outer().new Inner();
        inner1.printOuterName();
        
        StaticInner staticInner = new StaticInner();
        staticInner.staticPrintOuterName();

        outer.testLocalInnerClass();
        
        Outer anonymOuter = new Outer(){
           void printForAnonymInner(){
               System.out.println("Anonym inner test print");
           }
        };
        anonymOuter.printForAnonymInner();
        TestInterface testInterface = new TestInterface() {
            public void printInInterface() {
                logger.info("testFormInnerInterface");
            }
        };
        testInterface.printInInterface();
    }
    
}
