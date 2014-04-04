package com.mkyong.springdatamongodb.tutorial1;

import java.io.Serializable;

/**
 * Created by dmitry.bilyk on 4/4/14.
 */
public class Group implements Serializable{
    private String pid;

    private String name;

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
