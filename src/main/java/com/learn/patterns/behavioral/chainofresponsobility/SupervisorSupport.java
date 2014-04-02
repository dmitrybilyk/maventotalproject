package com.learn.patterns.behavioral.chainofresponsobility;

/**
 * Created with IntelliJ IDEA.
 * User: dmitriy.bilyk
 * Date: 03.11.13
 * Time: 21:05
 * To change this template use File | Settings | File Templates.
 */
public class SupervisorSupport implements SupportServiceItf {

    private SupportServiceItf next = null;
    public SupportServiceItf getNext() {
        return next;
    }
    public void setNext(SupportServiceItf next) {
        this.next = next;
    }

    @Override
    public void handleRequest(ServiceRequest request) {
        if(request.getType() == ServiceLevel.LEVEL_TWO)
        {
            request.setConclusion("Supervisor solved level two reuqest !!");
        }
        else
        {
            if(next != null){
                next.handleRequest(request);
            }
            else
            {
                throw new IllegalArgumentException("No handler found for :: " + request.getType());
            }
        }
    }
}