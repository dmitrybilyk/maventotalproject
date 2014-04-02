package com.learn.spring.springinjection.autowire.auto;

import com.learn.spring.springinjection.autowire.auto.api.AutoServiceInterface;

/**
 * Created with IntelliJ IDEA.
 * User: Buh
 * Date: 30.08.13
 * Time: 10:33
 * To change this template use File | Settings | File Templates.
 */
public class AutoCustomer {

    public AutoCustomer() {
    }

    public AutoCustomer(AutoServiceInterface autoMotoService) {
        this.autoMotoService = autoMotoService;
    }

    private AutoServiceInterface autoMotoService;

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void callAutoService(){
        autoMotoService.autotest();
    }

//    public void setAutoMotoService(AutoServiceInterface autoMotoService) {
//        this.autoMotoService = autoMotoService;
//    }

}
