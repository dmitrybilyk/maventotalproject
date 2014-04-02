package com.learn.hibernate.manytoone;

import javax.persistence.*;

/**
 * Created by IntelliJ IDEA.
 * User: bilyk
 * Date: 20.06.12
 * Time: 12:04
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "boy")
public class Boy {
    private Integer boy_id;
    private String boyName;

    public Boy() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Integer getBoy_id() {
        return boy_id;
    }

    public void setBoy_id(Integer boy_id) {
        this.boy_id = boy_id;
    }

    public String getBoyName() {
        return boyName;
    }

    public void setBoyName(String boyName) {
        this.boyName = boyName;
    }

}
