package com.learn.patterns.behavioral.visitor.pankaj;

/**
 * Created with IntelliJ IDEA.
 * User: dmitriy.bilyk
 * Date: 03.11.13
 * Time: 20:06
 * To change this template use File | Settings | File Templates.
 */

//We have visit() method for different type of items in Visitor interface
// that will be implemented by concrete visitor class.
public interface ShoppingCartVisitor {

    int visit(Book book);
    int visit(Fruit fruit);
}
