package com.learn.spring.resourceAnnotation;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by dmitry on 1/29/14.
 */
public class SpringDemo {

    public static void main(String... args) {
        AbstractApplicationContext context = new ClassPathXmlApplicationContext("resource-annotation-spring.xml");
        Student st=(Student) context.getBean("student");
//        String city=st.getCity().getCityName();
//        System.out.println(city);
    }
}