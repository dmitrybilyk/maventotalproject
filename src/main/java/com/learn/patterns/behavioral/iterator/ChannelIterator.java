package com.learn.patterns.behavioral.iterator;

/**
 * Created with IntelliJ IDEA.
 * User: dmitriy.bilyk
 * Date: 07.11.13
 * Time: 21:53
 * To change this template use File | Settings | File Templates.
 */
public interface ChannelIterator {

    public boolean hasNext();

    public Channel next();
}
