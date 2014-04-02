package com.learn.hibernate.manytoone;

import javax.persistence.*;

/**
 * Created by IntelliJ IDEA.
 * User: bilyk
 * Date: 20.06.12
 * Time: 12:05
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "girl")
public class Girl {

    private Integer girl_id;
    private String name;
    private Boy boy;

    public Girl() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Integer getGirl_id() {
        return girl_id;
    }

    public void setGirl_id(Integer girl_id) {
        this.girl_id = girl_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    @ManyToOne(cascade=CascadeType.ALL,fetch=FetchType.LAZY,optional=false)
    @JoinColumn(name="boy_id")
    public Boy getBoy() {
        return boy;
    }

    public void setBoy(Boy boy) {
        this.boy = boy;
    }

}