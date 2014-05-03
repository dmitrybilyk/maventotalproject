package com.learn.spring.springinaction.parentchild;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by dmitry on 03.05.14.
 */
public class Main {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("/springinaction/parentChild.xml");
        Child2 child2 = (Child2) applicationContext.getBean("child2");
        System.out.println(child2);

    }
}