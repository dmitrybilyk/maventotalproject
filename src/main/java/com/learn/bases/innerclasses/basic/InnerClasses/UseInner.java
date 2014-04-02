package com.learn.bases.innerclasses.basic.InnerClasses;

/**
 * Created by IntelliJ IDEA.
 * User: bilyk
 * Date: 24.05.12
 * Time: 10:46
 * To change this template use File | Settings | File Templates.
 */
public class UseInner {
//    InnerTest outer = new InnerTest();
    InnerTest.InnerClass innerClass = new InnerTest().new InnerClass();

    public void useInnerFromOtherClass(){
        System.out.println(innerClass.printTestVarFromOuter());
    }
            
}
