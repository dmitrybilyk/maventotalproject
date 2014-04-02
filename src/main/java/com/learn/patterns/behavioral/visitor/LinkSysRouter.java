package com.learn.patterns.behavioral.visitor;

/**
 * Created with IntelliJ IDEA.
 * User: dmitriy.bilyk
 * Date: 03.11.13
 * Time: 19:49
 * To change this template use File | Settings | File Templates.
 */
public class LinkSysRouter implements Router{

    @Override
    public void sendData(char[] data) {
    }

    @Override
    public void acceptData(char[] data) {
    }

    @Override
    public void accept(RouterVisitor v) {
        v.visit(this);
    }
}