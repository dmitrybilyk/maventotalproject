package com.learn.spring.timer;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by bid on 5/28/14.
 */
public class Main {
    public static void main(String[] args) {
            new ClassPathXmlApplicationContext("/spring.timer/timer.xml");
    }
}
