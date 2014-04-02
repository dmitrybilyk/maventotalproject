package com.learn.bases.innerclasses.basic.innertest;

/**
 * Created by IntelliJ IDEA.
 * User: Flash
 * Date: 26.05.12
 * Time: 8:18
 * To change this template use File | Settings | File Templates.
 */
public class ForTestParamInner {
    void iUseClassInParameter(TestMethodParameterInnerClass testMethodParameterInnerClass){
        System.out.println("I test here");
        testMethodParameterInnerClass.testFoo();
    }
    public static void main(String args[]){

        ForTestParamInner forTestParamInner = new ForTestParamInner();
        forTestParamInner.iUseClassInParameter(new TestMethodParameterInnerClass(){
//            public void testFoo(){
//                System.out.println("testFooInForTest");
//            }

        });


    }

}

