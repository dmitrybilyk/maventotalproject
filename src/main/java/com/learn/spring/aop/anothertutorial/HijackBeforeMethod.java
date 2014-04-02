package com.learn.spring.aop.anothertutorial;

import org.springframework.aop.MethodBeforeAdvice;

/**
 * Created with IntelliJ IDEA.
 * User: Dmitry
 * Date: 18.08.13
 * Time: 23:50
 * To change this template use File | Settings | File Templates.
 */
public class HijackBeforeMethod implements MethodBeforeAdvice
{
    @Override
    public void before(java.lang.reflect.Method method, Object[] objects, Object o) throws Throwable {
        System.out.println("HijackBeforeMethod : Before method hijacked!");
    }
}
