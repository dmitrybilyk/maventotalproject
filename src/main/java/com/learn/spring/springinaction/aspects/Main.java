package com.learn.spring.springinaction.aspects;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by dmitry on 05.05.14.
 */
public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("/springinaction/aspects.xml");
        Human human = (Human) context.getBean("human");
        human.act();
    }

}
