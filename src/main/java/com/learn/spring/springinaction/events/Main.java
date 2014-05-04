package com.learn.spring.springinaction.events;

import com.learn.spring.springinaction.propertyeditor.Contact;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by dmitry on 04.05.14.
 */
public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("/springinaction/events.xml");
        EventTrigger eventTrigger = (EventTrigger)context.getBean("eventTrigger");
        eventTrigger.eventTrigger();
    }
}
