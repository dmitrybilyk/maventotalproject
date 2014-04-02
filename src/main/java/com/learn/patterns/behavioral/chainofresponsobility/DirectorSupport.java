package com.learn.patterns.behavioral.chainofresponsobility;

/**
 * Created with IntelliJ IDEA.
 * User: dmitriy.bilyk
 * Date: 03.11.13
 * Time: 21:06
 * To change this template use File | Settings | File Templates.
 */
public class DirectorSupport implements SupportServiceItf {

    private SupportServiceItf next = null;
    public SupportServiceItf getNext() {
        return next;
    }
    public void setNext(SupportServiceItf next) {
        this.next = next;
    }

    @Override
    public void handleRequest(ServiceRequest request) {
        if(request.getType() == ServiceLevel.LEVEL_FOUR)
        {
            request.setConclusion("Director solved level four reuqest !!");
        }
        else
        {
            if(next != null){
                next.handleRequest(request);
            }
            else
            {
                request.setConclusion("You problem is none of our business");
                throw new IllegalArgumentException("You problem is none of our business :: " + request.getType());
            }
        }
    }
}