package com.learn.bases.innerclasses.basic.InnerClasses;

/**
 * Created by IntelliJ IDEA.
 * User: bilyk
 * Date: 10.05.12
 * Time: 17:00
 * To change this template use File | Settings | File Templates.
 */
public class InnerTest {

    int b = 10;
    
    void localInnerClassTest(){
        final int r = 10;
        class InnerInMethod{
            void printR(){
                System.out.println(r);
            }
        }
        InnerInMethod innerInMethod = new InnerInMethod();
        innerInMethod.printR();
    }
    
    
    public static void main(String[] args){
        InnerTest innerTest = new InnerTest();
        InnerClass innerClass = innerTest.new InnerClass();
        innerClass.printOutersB();

        StaticInnerClass staticInnerClass = new StaticInnerClass();
        staticInnerClass.print200();

        innerTest.localInnerClassTest();

        UseInner useInner = new UseInner();
        useInner.useInnerFromOtherClass();

    }
    public class InnerClass{
        int a = 100;
        void printOutersB(){
            System.out.println(b);
        }

        public String printTestVarFromOuter() {
            return String.valueOf("Var is " + a);
        }
    }
    
    static class StaticInnerClass{
        void print200(){
            System.out.println("200");
        }
    }
}
