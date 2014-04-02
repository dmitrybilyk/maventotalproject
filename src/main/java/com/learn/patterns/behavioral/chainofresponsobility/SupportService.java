package com.learn.patterns.behavioral.chainofresponsobility;

/**
 * Created with IntelliJ IDEA.
 * User: dmitriy.bilyk
 * Date: 03.11.13
 * Time: 21:03
 * To change this template use File | Settings | File Templates.
 */
public class SupportService implements SupportServiceItf {

    private SupportServiceItf handler = null;

    public SupportServiceItf getHandler() {
        return handler;
    }

    public void setHandler(SupportServiceItf handler) {
        this.handler = handler;
    }

    @Override
    public void handleRequest(ServiceRequest request) {
        handler.handleRequest(request);
    }
}
