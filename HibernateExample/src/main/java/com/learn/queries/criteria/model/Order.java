package com.learn.queries.criteria.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: bilyk
 * Date: 25.07.12
 * Time: 13:35
 * To change this template use File | Settings | File Templates.
 */
@Entity(name="com.learn.cacheusing.model.Order")
@Table(name="orders")
public class Order {
    @Id
    @GeneratedValue
    private long id;

    public Order(String orderNo, Person person) {
        this.orderNo = orderNo;
        this.person = person;
    }

    public Order() {

    }

    private String orderNo;

    public Order(String s, Person person, Date date, Double aDouble) {
        this.orderNo = s;
        this.person = person;
        this.orderDate = date;
        this.orderPrice = aDouble;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
    @ManyToOne
    @JoinColumn(name="P_id")
    private Person person;

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public double getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(double orderPrice) {
        this.orderPrice = orderPrice;
    }

    private Date orderDate;
    private double orderPrice;
}
