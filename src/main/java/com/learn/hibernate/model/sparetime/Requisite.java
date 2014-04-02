package com.learn.hibernate.model.sparetime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by IntelliJ IDEA.
 * User: bilyk
 * Date: 07.07.12
 * Time: 16:37
 * To change this template use File | Settings | File Templates.
 */

@Entity
public class Requisite {
    @Id
    @GeneratedValue
    private long id;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
