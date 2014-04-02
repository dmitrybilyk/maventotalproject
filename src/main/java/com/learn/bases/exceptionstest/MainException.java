package com.learn.bases.exceptionstest;

import java.util.logging.Logger;

/**
 * Created by IntelliJ IDEA.
 * User: bilyk
 * Date: 25.06.12
 * Time: 15:47
 * To change this template use File | Settings | File Templates.
 */
public class MainException {
    private static Logger logger = Logger.getLogger(MainException.class.getName());
    public static void main(String[] args) {


//        try {
////            System.out.println("test");
        try {
            throwException();
        } catch (TestException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
//            System.exit(0);
//        }catch(Exception ex){
//            ex.printStackTrace();
//        } finally {
//            System.out.println("testFinally");
//        }
        throwNullPointerException();
    }
    
    static void throwException() throws TestException {
        throw new TestException();
    }

    static void throwNullPointerException(){
        try{
            String s = null;
            int i = s.length();
        }catch (NullPointerException ex){
            ex.printStackTrace();
        }
        System.out.println("after trycatch");
    }
}
