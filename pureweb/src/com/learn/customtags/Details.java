package com.learn.customtags;

import javax.servlet.jsp.tagext.*;
import javax.servlet.jsp.*;
import java.io.*;
public class Details extends SimpleTagSupport {
    private String userName;
    private int age;
    private String password;
    //StringWriter object
    StringWriter sw = new StringWriter();

    public void doTag() throws JspException, IOException
    {

        getJspBody().invoke(sw);
        JspWriter out = getJspContext().getOut();
        out.println(sw.toString()+"Appended Custom Tag Message");
    }


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}