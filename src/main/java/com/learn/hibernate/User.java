package com.learn.hibernate;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by IntelliJ IDEA.
 * User: bilyk
 * Date: 20.06.12
 * Time: 10:48
 * To change this template use File | Settings | File Templates.
 */
@Entity
public class User {
    @Id
    @GeneratedValue
    private long id;
    private String name;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
//    public Address getUserAddress() {
//        return userAddress;
//    }
//
//    public void setUserAddress(Address userAddress) {
//        this.userAddress = userAddress;
//    }
//
//    @OneToOne
//    private Address userAddress;

    public Set<Address> getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(Set<Address> userAddress) {
        this.userAddress = userAddress;
    }

    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(name="USER_ADDRESS_MY", joinColumns = {@JoinColumn(name = "id")}, inverseJoinColumns = {@JoinColumn(name="address_id")})
    private Set<Address> userAddress = new HashSet<Address>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
