package com.learn.patterns.behavioral.visitor;

/**
 * Created with IntelliJ IDEA.
 * User: dmitriy.bilyk
 * Date: 03.11.13
 * Time: 19:48
 * To change this template use File | Settings | File Templates.
 */
public interface Router
{
    public void sendData(char[] data);
    public void acceptData(char[] data);

    public void accept(RouterVisitor v);
}
