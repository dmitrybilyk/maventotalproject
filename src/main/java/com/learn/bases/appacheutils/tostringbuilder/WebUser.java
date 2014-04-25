package com.learn.bases.appacheutils.tostringbuilder;

import java.util.Date;

public class WebUser extends AbstractUser
{
    private static final long serialVersionUID = 1L;
    private Date lastLoggedIn;
 
    public Date getLastLoggedIn() {
        return lastLoggedIn;
    }
 
    public void setLastLoggedIn(Date lastLoggedIn) {
        this.lastLoggedIn = lastLoggedIn;
    }
}