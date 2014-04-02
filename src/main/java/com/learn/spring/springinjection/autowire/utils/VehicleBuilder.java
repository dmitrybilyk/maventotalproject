package com.learn.spring.springinjection.autowire.utils;

import com.learn.spring.springinjection.autowire.model.Vehicle;
import org.apache.commons.lang.RandomStringUtils;

/**
 * Created with IntelliJ IDEA.
 * User: Buh
 * Date: 30.08.13
 * Time: 8:48
 * To change this template use File | Settings | File Templates.
 */
public class VehicleBuilder {
    public static Vehicle buildVehicle(){
       Vehicle vehicle = new Vehicle();
       vehicle.setName("Name_"+RandomStringUtils.random(5));
       return vehicle;
    }
}
