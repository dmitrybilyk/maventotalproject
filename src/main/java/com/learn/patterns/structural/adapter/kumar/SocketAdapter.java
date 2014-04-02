package com.learn.patterns.structural.adapter.kumar;

/**
 * Created with IntelliJ IDEA.
 * User: dmitriy.bilyk
 * Date: 12.11.13
 * Time: 21:18
 * To change this template use File | Settings | File Templates.
 */

//Could also be an abstract class
public interface SocketAdapter {

    public Volt get120Volt();

    public Volt get12Volt();

    public Volt get3Volt();
}