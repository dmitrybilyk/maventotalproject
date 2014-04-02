package com.learn.patterns.behavioral.strategypattern.kumar;

/**
 * Created with IntelliJ IDEA.
 * User: dmitriy.bilyk
 * Date: 04.11.13
 * Time: 22:22
 * To change this template use File | Settings | File Templates.
 */
public class PaypalStrategy implements PaymentStrategy {

    private String emailId;
    private String password;

    public PaypalStrategy(String email, String pwd){
        this.emailId=email;
        this.password=pwd;
    }

    @Override
    public void pay(int amount) {
        System.out.println(amount + " paid using Paypal.");
    }

}