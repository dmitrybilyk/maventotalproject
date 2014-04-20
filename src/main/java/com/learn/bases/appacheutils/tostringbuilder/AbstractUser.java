package com.learn.bases.appacheutils.tostringbuilder;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;

public abstract class AbstractUser implements Serializable
{
    private static final long serialVersionUID = 1L;
     
    private int id;
    private String firstName;
    private String lastName;
    private String age;
    private Date dob;

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }


    @Override
    public String toString()
    {
//        return ToStringBuilder.reflectionToString(this);

//        Custom formatting of any field type like Date
        return ToStringBuilder.reflectionToString(this, new CustomToStringStyle());
    }
}