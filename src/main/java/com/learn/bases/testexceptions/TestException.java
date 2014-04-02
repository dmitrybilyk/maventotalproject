package com.learn.bases.testexceptions;

/**
 * Created by IntelliJ IDEA.
 * User: Flash
 * Date: 05.05.12
 * Time: 19:19
 * To change this template use File | Settings | File Templates.
 */
public class TestException extends Exception{
    @Override
    public String getMessage() {
        return "My own exception";
    }
}
