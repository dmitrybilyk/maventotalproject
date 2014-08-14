package com.learn.spring.springinaction.events;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

public class RefreshListener implements ApplicationListener {
public void onApplicationEvent(ApplicationEvent event) {
   ((ContextRefreshedEvent) event).getApplicationContext().getBean("eventTrigger").toString();
    System.out.println("fdsfsdf");
}
}