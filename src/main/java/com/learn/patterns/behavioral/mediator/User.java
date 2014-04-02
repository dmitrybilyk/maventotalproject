package com.learn.patterns.behavioral.mediator;

/**
 * Created with IntelliJ IDEA.
 * User: dmitriy.bilyk
 * Date: 11.11.13
 * Time: 20:52
 * To change this template use File | Settings | File Templates.
 */
//Notice that User has a reference to the mediator object,
// it’s required for the communication between different users.
public abstract class User {
    protected ChatMediator mediator;
    protected String name;

    public User(ChatMediator med, String name){
        this.mediator=med;
        this.name=name;
    }

    public abstract void send(String msg);

    public abstract void receive(String msg);
}