package com.learn.spring.aop.anothertutorial;

import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA.
 * User: Dmitry
 * Date: 18.08.13
 * Time: 22:55
 * To change this template use File | Settings | File Templates.
 */
@Service
public class CustomerService {
    private String name = "dima";
    private String url = "aop test ";

    public void setName(String name) {
        this.name = name;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void printName() {
        System.out.println("Customer name : " + this.name);
    }

    public void printURL() {
        System.out.println("Customer website : " + this.url);
    }

    public void printThrowException() {
        throw new IllegalArgumentException();
    }

}