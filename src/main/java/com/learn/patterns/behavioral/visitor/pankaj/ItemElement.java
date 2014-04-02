package com.learn.patterns.behavioral.visitor.pankaj;

/**
 * Created with IntelliJ IDEA.
 * User: dmitriy.bilyk
 * Date: 03.11.13
 * Time: 20:02
 * To change this template use File | Settings | File Templates.
 */

//Notice that accept method takes Visitor argument,
// we can have some other methods also specific for items but for simplicity
// I am not going into that much detail and focusing on visitor pattern only.
public interface ItemElement {

    public int accept(ShoppingCartVisitor visitor);
}