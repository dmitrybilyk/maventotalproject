package com.learn.patterns.structural.adapter.kumar;

/**
 * Created with IntelliJ IDEA.
 * User: dmitriy.bilyk
 * Date: 12.11.13
 * Time: 21:17
 * To change this template use File | Settings | File Templates.
 */
public class Socket {

    public Volt getVolt(){
        return new Volt(120);
    }
}
