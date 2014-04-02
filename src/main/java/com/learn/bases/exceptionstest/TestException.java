package com.learn.bases.exceptionstest;

/**
 * Created by IntelliJ IDEA.
 * User: bilyk
 * Date: 25.06.12
 * Time: 15:45
 * To change this template use File | Settings | File Templates.
 */


//Throwable > Exception >> RuntimeException, ... ; Error
//RuntimeException, Error и их наследников еще называют unchecked exception
    //Все остальные - checked exceptions
//Checked - это исключения которые должны либо обрабатываться либо объявлять в сигнатуру метода о том, что они передаются выше

//    Checked:  ClassNotFoundException, Instantiation Exception, Illegal Access Exception, Not Such Method Exception;
    // Unchecked: Arithmetic Exception, Class Cast Exception, Array Index Out Of Bounds Exception, Null Pointer Exception, Number Format Exception;

public class TestException extends Exception {

    public TestException() {
    }

    @Override
    public void printStackTrace() {
//        super.printStackTrace();
        System.out.println("test exception print stack trace");
    }

    public TestException(String message) {
        super(message);
    }


}
