package com.learn.spring.aop;

import com.learn.spring.aop.service.ShapeService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by IntelliJ IDEA.
 * User: bilyk
 * Date: 04.07.12
 * Time: 15:55
 * To change this template use File | Settings | File Templates.
 */
public class AopMain {
    public static void main(String[] args){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("/aop/Spring-aop.xml");
        ShapeService shapeService = applicationContext.getBean("shapeService", ShapeService.class);
        System.out.println(shapeService.getCircle().getName());
    }
}
