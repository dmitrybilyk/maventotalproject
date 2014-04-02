package com.learn.spring.springtest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;

import static java.lang.System.out;

/**
 * Created by IntelliJ IDEA.
 * User: bilyk
 * Date: 04.07.12
 * Time: 10:50
 * To change this template use File | Settings | File Templates.
 */
@Controller
public class Circle implements Shape, ApplicationEventPublisherAware{

    private ApplicationEventPublisher publisher;

    @Autowired
    private MessageSource messageSource;

    public MessageSource getMessageSource() {
        return messageSource;
    }

    public void setMessageSource(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    private Point center;

    public Point getCenter() {
        return center;
    }

//    @Autowired
//    @Qualifier("circleRelated")
    @Resource
    public void setCenter(Point center) {
        this.center = center;
    }

    public void draw() {
       out.print(messageSource.getMessage("drawing.circle", null, "default circle", null));
       out.println(messageSource.getMessage("drawing.circle.parameters", new Object[]{center.getX(), center.getY()}, "default", null));
       out.println("test messages - "+messageSource.getMessage("greeting", null, "Default", null));
        DrawEvent drawEvent = new DrawEvent(this);
        publisher.publishEvent(drawEvent);
    }

    @PostConstruct
    public void initializeCircle(){
        System.out.println("circle initialized!");
    }

    @PreDestroy
    public void destroyCircle(){
        System.out.println("circle destroyed!");
    }

    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.publisher = applicationEventPublisher;
    }
}
