package com.learn.mappings.onetomany.oneToManyUnidirectional;


import javax.persistence.*;

@Entity
@Table(name="USER_ADDRESS")
public class UserAddress {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;

    @Column(name="STREET")
    private String street;

    @Column(name="HOUSE_NUMBER")
    private long houseNumber;

    @Column(name="FLAT_NUMBER")
    private long flatNumber;

    @ManyToOne
    @JoinColumn(name="USER_ID",nullable=true)
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public long getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(long houseNumber) {
        this.houseNumber = houseNumber;
    }

    public long getFlatNumber() {
        return flatNumber;
    }

    public void setFlatNumber(long flatNumber) {
        this.flatNumber = flatNumber;
    }
}
