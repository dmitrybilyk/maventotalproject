package com.learn.saveorupdatetest.model;

import javax.persistence.*;

/**
 * Created with IntelliJ IDEA.
 * AccountUser: Dmitry
 * Date: 26.05.13
 * Time: 10:41
 * To change this template use File | Settings | File Templates.
 */
@Entity(name="com.learn.saveorupdatetest.model.User")
@Table(name = "USERS")
public class User {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    private String name;

    public User() {
    }

    public User(String userName) {
        this.name = userName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
