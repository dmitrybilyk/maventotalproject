package com.learn.mappings.onetomany.oneToManyUnidirectional;

import javax.persistence.*;

@Entity
@Table(name="BANK")
public class Bank {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="BANK_ID")
    private long id;

    private String name;
    private String mfo;


    public Bank() {
    }

    public Bank(String name, String mfo) {
        this.name = name;
        this.mfo = mfo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMfo() {
        return mfo;
    }

    public void setMfo(String mfo) {
        this.mfo = mfo;
    }
}
