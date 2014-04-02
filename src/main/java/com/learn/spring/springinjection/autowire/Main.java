package com.learn.spring.springinjection.autowire;

import com.learn.spring.springinjection.autowire.service.api.VehicleService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created with IntelliJ IDEA.
 * User: Buh
 * Date: 30.08.13
 * Time: 8:39
 * To change this template use File | Settings | File Templates.
 */
public class Main {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("autowire-context.xml");
        VehicleService vehicleService = (VehicleService) applicationContext.getBean("foreignVehicleServiceImpl");
        vehicleService.save();

//        autowiring within xml testing
//        AutoCustomer autoCustomer = (AutoCustomer) applicationContext.getBean("autoCustomer");
//        autoCustomer.callAutoService();
    }
}
