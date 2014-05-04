package com.learn.spring.springinaction.propertyeditor;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by dmitry on 04.05.14.
 */
public class Main {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("/springinaction/propertyeditor.xml");
        Contact contact = (Contact) applicationContext.getBean("contact");
        System.out.println(contact);
    }
}
