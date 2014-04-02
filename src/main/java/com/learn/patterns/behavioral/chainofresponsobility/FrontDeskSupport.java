package com.learn.patterns.behavioral.chainofresponsobility;

/**
 * Created with IntelliJ IDEA.
 * User: dmitriy.bilyk
 * Date: 03.11.13
 * Time: 21:05
 * To change this template use File | Settings | File Templates.
 */
public class FrontDeskSupport implements SupportServiceItf {

    private SupportServiceItf next = null;
    public SupportServiceItf getNext() {
        return next;
    }
    public void setNext(SupportServiceItf next) {
        this.next = next;
    }

    @Override
    public void handleRequest(ServiceRequest service) {
        if(service.getType() == ServiceLevel.LEVEL_ONE)
        {
            service.setConclusion("Front desk solved level one reuqest !!");
        }
        else
        {
            if(next != null){
                next.handleRequest(service);
            }
            else
            {
                throw new IllegalArgumentException("No handler found for :: " + service.getType());
            }
        }
    }
}
