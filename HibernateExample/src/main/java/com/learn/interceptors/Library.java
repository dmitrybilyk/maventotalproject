package com.learn.interceptors;

import org.hibernate.CallbackException;
import org.hibernate.EmptyInterceptor;
import org.hibernate.Session;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.classic.Lifecycle;
import org.hibernate.type.Type;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: dmitriy.bilyk
 * Date: 23.10.13
 * Time: 12:33
 * To change this template use File | Settings | File Templates.
 */
@Entity
public class Library implements Lifecycle{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @OneToMany(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @Fetch(FetchMode.SUBSELECT)
    private List<Book> books;

    private String name;

    private int bookCount;


    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

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

    @Override
    public boolean onSave(Session s) throws CallbackException {
        return false;
    }

    @Override
    public boolean onUpdate(Session s) throws CallbackException {
        return false;
    }

    @Override
    public boolean onDelete(Session s) throws CallbackException {
        System.out.println("my own method ..........................................!!!!!!!!!!!!!");
        return false;
    }

    @Override
    public void onLoad(Session s, Serializable id) {

    }
}
