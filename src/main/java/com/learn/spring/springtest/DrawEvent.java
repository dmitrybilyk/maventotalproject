package com.learn.spring.springtest;

import org.springframework.context.ApplicationEvent;

/**
 * Created by IntelliJ IDEA.
 * User: bilyk
 * Date: 04.07.12
 * Time: 14:32
 * To change this template use File | Settings | File Templates.
 */
public class DrawEvent extends ApplicationEvent{
    /**
     * Create a new ApplicationEvent.
     *
     * @param source the component that published the event (never <code>null</code>)
     */
    public DrawEvent(Object source) {
        super(source);
    }

    public String toString(){
        return "draw event occured";
    }
}
