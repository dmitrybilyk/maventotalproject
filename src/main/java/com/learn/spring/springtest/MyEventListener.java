package com.learn.spring.springtest;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * Created by IntelliJ IDEA.
 * User: bilyk
 * Date: 04.07.12
 * Time: 14:28
 * To change this template use File | Settings | File Templates.
 */
@Component
public class MyEventListener implements ApplicationListener{
    public void onApplicationEvent(ApplicationEvent event) {
        System.out.println(event.toString());
    }
}
