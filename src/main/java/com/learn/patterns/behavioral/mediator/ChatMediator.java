package com.learn.patterns.behavioral.mediator;

/**
 * Created with IntelliJ IDEA.
 * User: dmitriy.bilyk
 * Date: 11.11.13
 * Time: 20:51
 * To change this template use File | Settings | File Templates.
 */
public interface ChatMediator {

    public void sendMessage(String msg, User user);

    void addUser(User user);
}