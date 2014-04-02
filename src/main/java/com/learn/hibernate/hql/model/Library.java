package com.learn.hibernate.hql.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created with IntelliJ IDEA.
 * User: dmitriy.bilyk
 * Date: 23.10.13
 * Time: 12:33
 * To change this template use File | Settings | File Templates.
 */
@Entity
public class Library {
    @Id
    @GeneratedValue
    private long id;

    private String name;

    private int bookCount;

    public Library(String name) {
        this.name = name;
    }

    public Library() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBookCount() {
        return bookCount;
    }

    public void setBookCount(int bookCount) {
        this.bookCount = bookCount;
    }
}
