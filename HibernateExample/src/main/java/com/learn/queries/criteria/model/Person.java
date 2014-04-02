package com.learn.queries.criteria.model;


//import org.hibernate.envers.Audited;
//import org.hibernate.envers.NotAudited;
//import org.hibernate.envers.RelationTargetAuditMode;

import javax.persistence.*;

/**
 * Created by IntelliJ IDEA.
 * User: bilyk
 * Date: 25.07.12
 * Time: 13:35
 * To change this template use File | Settings | File Templates.
 */
@Entity(name="com.learn.cacheusing.model.Person")
@Table(name="persons")
public class Person {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;
    @Column(name = "FirstName")
    private String firstName;

    public Person(String firstName, String lastName, String address, String city) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.city = city;
    }

    public Person() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Column(name="LastName")
    private String lastName;
    @Column(name="Address")
//    @Audited(targetAuditMode= RelationTargetAuditMode.NOT_AUDITED)
    private String address;

    @Column(name="City")
//    @NotAudited
    private String city;

}
