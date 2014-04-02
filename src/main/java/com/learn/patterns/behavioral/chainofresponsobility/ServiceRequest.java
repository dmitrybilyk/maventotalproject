package com.learn.patterns.behavioral.chainofresponsobility;

/**
 * Created with IntelliJ IDEA.
 * User: dmitriy.bilyk
 * Date: 03.11.13
 * Time: 21:01
 * To change this template use File | Settings | File Templates.
 */
public class ServiceRequest {

    private ServiceLevel type;
    private String conclusion = null;

    public ServiceLevel getType() {
        return type;
    }
    public void setType(ServiceLevel type) {
        this.type = type;
    }
    public String getConclusion() {
        return conclusion;
    }
    public void setConclusion(String conclusion) {
        this.conclusion = conclusion;
    }
}
