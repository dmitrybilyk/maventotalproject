package com.learn.bases.innerclasses.basic.innertest;

/**
 * Created by IntelliJ IDEA.
 * User: Flash
 * Date: 10.05.12
 * Time: 21:59
 * To change this template use File | Settings | File Templates.
 */
public class TestAnonymousInnerClass {
    private String x;

    void print(){
        System.out.println("in "+TestAnonymousInnerClass.class.getName());
    }
    
    void TestAnonymousInnerClass(){
        final String s = "fff";
        class InnerMethodClass{

          void printX(){
            x = "dfsfa";
            System.out.println(x+s);
        }
        }
        InnerMethodClass innerMethodClass = new InnerMethodClass();
        innerMethodClass.printX();
        
        
        Thread t=new Thread(
                //This is the Anonymous Inner Class.Which don't have any name for class.
                new Runnable() {
                    @Override
                    public void run() {
                        System.out.println("Hi,This is example of anonymous Inner Class.");
                    }
                });

        t.start();

    }
    
    interface TestInterface{
        void inerMethod();
    }
    
    public static void main(String[] args) {
        

        
        TestAnonymousInnerClass outerClass=new TestAnonymousInnerClass(){
             void print(){
                 System.out.println("in "+"inner");
             }
            public void test2(){
                System.out.println("test2");
            }
        };
        outerClass.print();

        
        TestInterface testInterface = new TestInterface() {
            @Override
            public void inerMethod() {
                System.out.println("in interface inner");
                test();
            }
            
            void test(){
                System.out.println("test");
            }
        };

        testInterface.inerMethod();

        
    }
}
