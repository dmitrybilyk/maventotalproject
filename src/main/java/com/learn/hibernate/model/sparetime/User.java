package com.learn.hibernate.model.sparetime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by IntelliJ IDEA.
 * User: bilyk
 * Date: 07.07.12
 * Time: 13:33
 * To change this template use File | Settings | File Templates.
 */
@Entity
public class User{
    @Id
    @GeneratedValue
    private long id;

    private String login;
    private String password;
    private int age;

    public User(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public User() {
    }

    public User(String login, String password, int age) {
        this.login = login;
        this.password = password;
        this.age = age;
    }

    public User(String login) {
        this.login = login;
    }

    public Long getId() {
        return id;
    }


    public void setId(Long id) {
        this.id = id;
    }


    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
