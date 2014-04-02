package com.learn.spring.springinjection.autowire.auto.impl;

import com.learn.spring.springinjection.autowire.auto.api.AutoServiceInterface;

/**
 * Created with IntelliJ IDEA.
 * User: Buh
 * Date: 30.08.13
 * Time: 10:31
 * To change this template use File | Settings | File Templates.
 */
public class AutoServiceMotoImpl implements AutoServiceInterface {
    @Override
    public void autotest() {
        System.out.println("from autoservice moto");
    }
}
