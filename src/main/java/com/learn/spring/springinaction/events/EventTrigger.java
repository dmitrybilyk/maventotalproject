package com.learn.spring.springinaction.events;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * Created by dmitry on 04.05.14.
 */
public class EventTrigger implements ApplicationContextAware {
    ApplicationContext applicationContext;
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
    public void eventTrigger(){
        Course course = new Course();
        CourseFullEvent courseFullEvent = new CourseFullEvent(this, course);
        applicationContext.publishEvent(courseFullEvent);
    }
}
