package com.learn.spring.springinaction.events;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;

/**
 * Created by dmitry on 04.05.14.
 */
public class EventTrigger implements ApplicationEventPublisherAware
{

   Course course;

   public Course getCourse()
   {
      return course;
   }

   public void setCourse(final Course course)
   {
      this.course = course;
   }

   ApplicationEventPublisher applicationEventPublisher;

   //    @Override
//    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
//        this.applicationContext = applicationContext;
//    }
   public void eventTrigger()
   {
      Course course = new Course();
      CourseFullEvent courseFullEvent = new CourseFullEvent(this, course);
      applicationEventPublisher.publishEvent(courseFullEvent);
   }

   @Override
   public void setApplicationEventPublisher(final ApplicationEventPublisher applicationEventPublisher)
   {
      this.applicationEventPublisher = applicationEventPublisher;
   }

}
