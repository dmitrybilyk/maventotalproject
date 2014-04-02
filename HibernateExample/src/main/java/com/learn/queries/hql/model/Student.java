package com.learn.queries.hql.model;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: dmitriy.bilyk
 * Date: 23.10.13
 * Time: 12:33
 * To change this template use File | Settings | File Templates.
 */
@Entity(name="com.learn.queries.hql.model.Student")
@Table(name="Students")
public class Student {
    @Id
    @GeneratedValue
    private long id;

    private String firstName;

    private String lastName;

//    @ManyToOne
//    @JoinColumn(name = "group_id")
//    private Group group;

    @ManyToMany
    @JoinTable(name = "STUDENT_LIBRARY", joinColumns = { @JoinColumn(name = "STUDENT_ID") }, inverseJoinColumns = { @JoinColumn(name = "LIBRARY_ID") })
    private Set<Library> library;

    @ManyToMany
    @JoinTable(name = "STUDENT_BOOK", joinColumns = { @JoinColumn(name = "STUDENT_ID") }, inverseJoinColumns = { @JoinColumn(name = "BOOK_ID") })
    private List<Book> books;

    public Student(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Student() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

//    public Group getGroup() {
//        return group;
//    }
//
//    public void setGroup(Group group) {
//        this.group = group;
//    }

    public Set<Library> getLibrary() {
        return library;
    }

    public void setLibrary(Set<Library> library) {
        this.library = library;
    }


    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }
}
