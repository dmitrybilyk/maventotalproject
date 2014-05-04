package com.learn.spring.springinaction.events;

import org.springframework.context.ApplicationEvent;

public class CourseFullEvent extends ApplicationEvent {
    private Course course;

    public CourseFullEvent(Object source, Course course) {
        super(source);
        this.course = course;
    }

    public Course getCourse() {
        return course;
    }
}