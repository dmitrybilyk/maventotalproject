package com.learn.bases.exceptionstest.complextesting.exception;

/**
 * Created with IntelliJ IDEA.
 * User: Buh
 * Date: 30.08.13
 * Time: 19:32
 * To change this template use File | Settings | File Templates.
 */
public class SaveException extends Exception {
    @Override
    public String getMessage() {
        return "Error occured whild saving the object!";
    }
}
