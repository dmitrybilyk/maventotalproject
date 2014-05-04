package com.learn.spring.springinaction.scriptsrunning;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by dmitry on 04.05.14.
 */
public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("/springinaction/scripts.xml");
        Lime lime = (Lime) context.getBean("lime");
        lime.drink();
    }
}
