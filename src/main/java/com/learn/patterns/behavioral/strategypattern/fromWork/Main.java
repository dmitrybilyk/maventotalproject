package com.learn.patterns.behavioral.strategypattern.fromWork;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: dmitriy.bilyk
 * Date: 14.01.14
 * Time: 21:44
 * To change this template use File | Settings | File Templates.
 */
public class Main {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("/services.xml");
        DateCalculationServicePojo dateCalculationServicePojo = (DateCalculationServicePojo) applicationContext.getBean("dateCalculationService");
        dateCalculationServicePojo.calculateDates(new Date(), Warehousing.ON, ImportType.UPLOAD);

    }
}
