package com.learn.patterns.behavioral.chainofresponsobility;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created with IntelliJ IDEA.
 * User: dmitriy.bilyk
 * Date: 03.11.13
 * Time: 21:10
 * To change this template use File | Settings | File Templates.
 */
public class TestChainOfResponsibility {
    public static void main(String[] args)
    {
        ApplicationContext context = new ClassPathXmlApplicationContext("application-config-chain-pattern.xml");
        SupportService supportService = (SupportService) context.getBean("supportService");

        ServiceRequest request = new ServiceRequest();
        request.setType(ServiceLevel.LEVEL_ONE);
        supportService.handleRequest(request);
        System.out.println(request.getConclusion());

        request = new ServiceRequest();
        request.setType(ServiceLevel.LEVEL_THREE);
        supportService.handleRequest(request);
        System.out.println(request.getConclusion());

        request = new ServiceRequest();
        request.setType(ServiceLevel.INVALID_REQUEST);
        supportService.handleRequest(request);
        System.out.println(request.getConclusion());
    }
}
