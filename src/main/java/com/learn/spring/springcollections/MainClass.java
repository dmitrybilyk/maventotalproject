package com.learn.spring.springcollections;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: dmitriy.bilyk
 * Date: 18.03.13
 * Time: 21:01
 * To change this template use File | Settings | File Templates.
 */
public class MainClass {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-collections.xml");

        Customer cust = (Customer)context.getBean("CustomerBean");
        System.out.println(cust);

        List<Integer> listOfNums = (List<Integer>) context.getBean("listOfNums");
        for (Integer listOfNum : listOfNums) {
            System.out.println(listOfNum);
        }

        Map<String, PersonBean> map = (Map<String, PersonBean>) context.getBean("map");
        for (String s : map.keySet()) {
            System.out.println(map.get(s).getName());
        }
    }
}
