package com.learn.model.cxfsoap;

import javax.persistence.*;

/**
 * Created by dmitry.bilyk on 3/28/14.
 */

@Entity
@Table(name = "systems")
public class System {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;

    @Column(name = "name")
    private String name;
    @Column(name = "country")
    private String country;
    @Column(name = "activity")
    private String activity;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }
}
