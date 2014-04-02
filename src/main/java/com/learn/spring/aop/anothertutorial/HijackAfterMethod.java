package com.learn.spring.aop.anothertutorial;

import org.springframework.aop.AfterReturningAdvice;

/**
 * Created with IntelliJ IDEA.
 * User: Dmitry
 * Date: 18.08.13
 * Time: 23:54
 * To change this template use File | Settings | File Templates.
 */
public class HijackAfterMethod implements AfterReturningAdvice
{
    @Override
    public void afterReturning(Object o, java.lang.reflect.Method method, Object[] objects, Object o2) throws Throwable {
        System.out.println("HijackAfterMethod : After method hijacked!");
    }
}