package com.learn.bases.appacheutils.tostringbuilder;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class GuestUser extends WebUser
{
    private static final long serialVersionUID = 1L;
    private String location;
 
    public String getLocation() {
        return location;
    }
 
    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public String toString()
    {
        return new ToStringBuilder(this)
                .appendSuper(super.toString())
                .append("lastLoggedIn", getLastLoggedIn()).toString();
//        Use information from super class in sub class with easy method call
    }
}