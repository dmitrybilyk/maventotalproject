package com.learn.hibernate.model.sparetime;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: bilyk
 * Date: 07.07.12
 * Time: 16:28
 * To change this template use File | Settings | File Templates.
 */

@Entity
public class DestinationCompany {
    private long id;
    private String name;

    @OneToMany
    private List<Requisite> requisiteList;
    
    @OneToOne
    private Service service;
    
    @Id
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
