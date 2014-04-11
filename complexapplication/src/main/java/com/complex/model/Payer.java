package com.complex.model;

import com.complex.model.security.User;

import javax.persistence.*;

/**
 * Created by dmitry.bilyk on 4/11/14.
 */
@Entity
public class Payer {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="PAYER_ID")
    private int id;

    private String nickName;

    @ManyToOne
    private User user;

    public Payer() {
    }

    public Payer(int id, String nickName, User user) {
        this.id = id;
        this.nickName = nickName;
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
