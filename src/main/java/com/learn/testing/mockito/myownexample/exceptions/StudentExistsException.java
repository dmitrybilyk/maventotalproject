package com.learn.testing.mockito.myownexample.exceptions;

/**
 * Created by dmitry.bilyk on 4/7/14.
 */
public class StudentExistsException extends RuntimeException {
    @Override
    public String getMessage() {
        return "The student with such name is already in list";
    }
}
