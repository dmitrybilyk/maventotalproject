package com.learn.bases.innerclasses.basic.innertest.staticinner;

/**
 * Created with IntelliJ IDEA.
 * User: Buh
 * Date: 18.08.12
 * Time: 18:57
 * To change this template use File | Settings | File Templates.
 */
public class StaticInnerExample {
     private static String name = "name";
    static class InnerStatic{
        public void print(){
            System.out.println("print "+name);
        }
    }

}
