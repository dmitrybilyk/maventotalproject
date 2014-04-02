package com.learn.spring.aop.anothertutorial;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created with IntelliJ IDEA.
 * User: Dmitry
 * Date: 18.08.13
 * Time: 23:00
 * To change this template use File | Settings | File Templates.
 */
public class AopTest {
    public static void main(String[] args) {
        ApplicationContext appContext = new ClassPathXmlApplicationContext(
//                new String[] { "Customer-Service.xml" });
                new String[] { "/aop/Customer-Service.xml" });

        CustomerService cust = (CustomerService) appContext.getBean("hijackBeforeMethodBean");
//        CustomerService custBefore = (CustomerService) appContext.getBean("hijackBeforeMethodBean");
//        CustomerService custAfter = (CustomerService) appContext.getBean("hijackAfterMethodBean");
//        CustomerService custThrowException = (CustomerService) appContext.getBean("hijackThrowExceptionMethodBean");


        System.out.println("*************************");
        cust.printName();
        System.out.println("*************************");
        cust.printURL();
        System.out.println("*************************");
        try {
            cust.printThrowException();
        } catch (Exception e) {

        }

    }
}
