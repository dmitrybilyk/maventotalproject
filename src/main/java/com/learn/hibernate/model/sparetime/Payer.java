package com.learn.hibernate.model.sparetime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * Created by IntelliJ IDEA.
 * User: bilyk
 * Date: 07.07.12
 * Time: 13:39
 * To change this template use File | Settings | File Templates.
 */

@Entity
public class Payer {
    @Id
    @GeneratedValue
    private long id;
    private String nickName;

    @ManyToOne
    private User user;

    public Payer(String payerName, User user) {
        this.nickName = payerName;
        this.user = user;
    }

    public Payer() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
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
