package com.learn.hibernate.model.sparetime;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * Created by IntelliJ IDEA.
 * User: bilyk
 * Date: 07.07.12
 * Time: 16:38
 * To change this template use File | Settings | File Templates.
 */
@Entity
public class City{
    @Id
    private long id;

    @ManyToOne
    private User user;

    public City(User user) {
        this.user = user;
    }

    public City() {
    }

    public Long getId() {
        return id;
    }


    public void setId(Long id) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public void setId(long id) {
        this.id = id;
    }

}
