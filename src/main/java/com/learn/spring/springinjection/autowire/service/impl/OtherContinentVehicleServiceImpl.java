package com.learn.spring.springinjection.autowire.service.impl;

import com.learn.spring.springinjection.autowire.service.api.VehicleService;

/**
 * Created with IntelliJ IDEA.
 * User: Buh
 * Date: 30.08.13
 * Time: 8:32
 * To change this template use File | Settings | File Templates.
 */
public class OtherContinentVehicleServiceImpl implements VehicleService {
//    @Autowire
//    private DaoInterface daoInterface;

    @Override
    public void save() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void moving() {
        System.out.println("other moving");
    }

    @Override
    public void stopping() {
        System.out.println("other stopping");
    }

    @Override
    public void speeding() {
        System.out.println("other speeding");
    }
}
