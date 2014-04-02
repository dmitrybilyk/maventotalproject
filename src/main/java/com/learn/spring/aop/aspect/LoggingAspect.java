package com.learn.spring.aop.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

/**
 * Created by IntelliJ IDEA.
 * User: bilyk
 * Date: 04.07.12
 * Time: 17:00
 * To change this template use File | Settings | File Templates.
 */

@Aspect
public class LoggingAspect {
    @Before("execution(public String getName())")
    public void LoggingAdvice(){
        System.out.println("Advice run. Get method called");
    }
}
