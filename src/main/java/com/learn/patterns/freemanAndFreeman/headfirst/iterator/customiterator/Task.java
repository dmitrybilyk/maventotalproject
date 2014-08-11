package com.learn.patterns.freemanAndFreeman.headfirst.iterator.customiterator;

import java.util.Date;

/**
 * Created by dmitry on 8/11/14.
 */
public class Task {
    private String name;
    private Date createDate;

    public Task(String name, Date createDate) {
        this.name = name;
        this.createDate = createDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}
