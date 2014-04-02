package com.learn.spring.springinjection.autowire.mkyongtutorial.common;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext(
				"mkyongautowire/SpringBeans.xml");

		Customer cust = (Customer) context.getBean("customer");
		System.out.println(cust);
        cust.getSingleApprovalService().approve();
	}
}