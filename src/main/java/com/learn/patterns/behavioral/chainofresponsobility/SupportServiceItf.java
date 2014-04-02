package com.learn.patterns.behavioral.chainofresponsobility;

/**
 * Created with IntelliJ IDEA.
 * User: dmitriy.bilyk
 * Date: 03.11.13
 * Time: 21:03
 * To change this template use File | Settings | File Templates.
 */
public interface SupportServiceItf {
    public void handleRequest(ServiceRequest request);
}