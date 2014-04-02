package com.learn.hibernate.hql.model;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: dmitriy.bilyk
 * Date: 23.10.13
 * Time: 12:33
 * To change this template use File | Settings | File Templates.
 */
@Entity
public class Book {
    @Id
    @GeneratedValue
    private long id;

    private String name;

    private Date date;

    @ManyToOne
    @JoinColumn(name = "library_id")
    private Library library;



    public Book(String name, Date date, Library library) {
        this.name = name;
        this.date = date;
        this.library = library;
    }

    public Book() {
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date year) {
        this.date = year;
    }

    public Library getLibrary() {
        return library;
    }

    public void setLibrary(Library library) {
        this.library = library;
    }

}
