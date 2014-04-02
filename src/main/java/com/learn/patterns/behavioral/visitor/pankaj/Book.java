package com.learn.patterns.behavioral.visitor.pankaj;

/**
 * Created with IntelliJ IDEA.
 * User: dmitriy.bilyk
 * Date: 03.11.13
 * Time: 20:04
 * To change this template use File | Settings | File Templates.
 */
public class Book implements ItemElement {

    private int price;
    private String isbnNumber;

    public Book(int cost, String isbn){
        this.price=cost;
        this.isbnNumber=isbn;
    }

    public int getPrice() {
        return price;
    }

    public String getIsbnNumber() {
        return isbnNumber;
    }

    @Override
    public int accept(ShoppingCartVisitor visitor) {
        return visitor.visit(this);
    }

}