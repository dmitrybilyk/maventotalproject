package com.learn.spring.aop.anothertutorial;

import org.springframework.aop.ThrowsAdvice;

/**
 * Created with IntelliJ IDEA.
 * User: Dmitry
 * Date: 18.08.13
 * Time: 23:57
 * To change this template use File | Settings | File Templates.
 */
public class HijackThrowException implements ThrowsAdvice {
    public void afterThrowing(IllegalArgumentException e) throws Throwable {
        System.out.println("HijackThrowException : Throw exception hijacked!");
    }
}
