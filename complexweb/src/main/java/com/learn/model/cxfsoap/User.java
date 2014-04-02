package com.learn.model.cxfsoap;

import javax.persistence.*;

/**
 * Created by dmitry.bilyk on 3/28/14.
 */
@Entity
@Table(name = "users")
public class User {

    public User() {
    }

    public User(String firstName) {
        this.firstName = firstName;
    }

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;

    @Column(name = "firstName")
    private String firstName;
    @Column(name = "lastName")
    private String lastName;
    @Column(name = "age")
    private int age;

    @ManyToOne
    private System system;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public System getSystem() {
        return system;
    }

    public void setSystem(System system) {
        this.system = system;
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
