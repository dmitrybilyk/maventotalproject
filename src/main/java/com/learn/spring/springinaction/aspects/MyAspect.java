package com.learn.spring.springinaction.aspects;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

/**
 * Created by dmitry on 05.05.14.
 */
@Aspect
public class MyAspect {
    @Pointcut(value = "execution(* com.learn.spring.springinaction.aspects.Human.act(..))")
    public void myPointcut(){
    }


    @Before("myPointcut()")
    public void printBefore(){
        System.out.println("before");
    }
    @Before("execution(public void act())")
    public void getNameAdvice(){
        System.out.println("Executing Advice on getName()");
    }


}
